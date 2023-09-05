package de.sakurajin.evenbetterarcheology.block.entity;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.registry.ModBlockEntities;
import de.sakurajin.evenbetterarcheology.registry.ModMessages;
import de.sakurajin.evenbetterarcheology.screen.IdentifyingScreenHandler;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import de.sakurajin.evenbetterarcheology.registry.ModItems;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.BrushItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

import static de.sakurajin.evenbetterarcheology.block.custom.ArchelogyTable.DUSTING;

public class ArcheologyTableBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    //default inventory size of the archeology table,
    public static final int INV_SIZE = 3;
    //default number of Properties of ArcheologyTableBlockEntity
    public static final int PROPERTY_DELEGATES = 2;

    //count of custom slots inside the table
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(INV_SIZE, ItemStack.EMPTY);

    private final String translationKey = "archeology_table";   //used in getDisplayName using getTranslationKey

    //synchronises Ints between server and client
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    //loottable for crafting results
    private static final Identifier CRAFTING_LOOT = new Identifier(EvenBetterArcheology.DATA.MOD_ID, "identifying_loot");

    public ArcheologyTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ARCHEOLOGY_TABLE, pos, state);

        //getter und setter für PropertyDelegate based on index (progress, maxProgress)
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> ArcheologyTableBlockEntity.this.progress;
                    case 1 -> ArcheologyTableBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ArcheologyTableBlockEntity.this.progress = value;
                    case 1 -> ArcheologyTableBlockEntity.this.maxProgress = value;
                }
            }

            public int size() {
                return PROPERTY_DELEGATES;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new IdentifyingScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("archeology_table.progress", progress);          //saves the block inventory upon closing the world
    }

    private void resetProgress() {
        this.progress = 0;
    }

    @Override
    public void readNbt(NbtCompound nbt) {                          //reads saved inventory upon opening the world
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("archeology_table");
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, ArcheologyTableBlockEntity entity) {
        //don't do anything clientside
        if (world.isClient()) {
            return;
        }

        //if the entity has a recipe inside:
        if (hasRecipe(entity)) {
            //play sound every 10th tick
            if (entity.progress % 10 == 0) {
                world.playSound(null, entity.pos, SoundEvents.ITEM_BRUSH_BRUSHING_GENERIC, SoundCategory.BLOCKS, 0.25f, 1f);
            }

            //if the recipe is inside, et self state to dusting
            world.setBlockState(blockPos, blockState.with(DUSTING, true));
            entity.progress++; //increase progress
            markDirty(world, blockPos, blockState);
            //if crafting progress is bigger or as big as the maxProgress, then craft the Item, else reset the timer
            if (entity.progress >= entity.maxProgress) {
                entity.craftItem();
            }
        } else {
            world.setBlockState(blockPos, blockState.with(DUSTING, false));
            entity.resetProgress();
            markDirty(world, blockPos, blockState);
        }
    }

    private void craftItem() {
        SimpleInventory inventory = new SimpleInventory(this.size());
        for (int i = 0; i < this.size(); i++) {
            inventory.setStack(i, this.getStack(i));
        }

        //if there is an unidentified artifact in the input slot and the output slot is empty:
        if (hasRecipe(this) && this.getStack(2).isEmpty()) {
            //remove input from slot
            this.removeStack(1, 1);
            ItemStack brush = this.getStack(0);
            int newDamage = brush.getDamage() + 1; //calculate new Damage Value the item would have

            //if the item is supposed to break or the durability is smaller than zero
            if (newDamage > brush.getMaxDamage()) {
                this.removeStack(0, 1);   //remove the Item
                assert this.world != null;
                if (!this.world.isClient()) {
                    //play break sound
                    this.world.playSound(null, this.pos, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 0.25f, 1f);
                }
            } else {
                //if not, set the damage to the calculated damage above
                brush.setDamage(newDamage);
            }

            //if on server
            if (!this.world.isClient()) {
                //play sound after crafting
                this.world.playSound(null, this.pos, SoundEvents.ITEM_BRUSH_BRUSHING_SAND_COMPLETE, SoundCategory.BLOCKS, 0.5f, 1f);
            }
            this.setStack(2, generateCraftingLoot(this, this.world)); //set crafted output in the output slot
            this.resetProgress(); //resets crafting progress
            this.markDirty();
        }

    }

    private ItemStack generateCraftingLoot(BlockEntity entity, World world) {
        //if on server and there is a Server(World)
        if (world != null && !world.isClient() && world.getServer() != null) {
            //gets loot-table based on .json loot
            LootTable lootTable = world.getServer().getLootManager().getLootTable(CRAFTING_LOOT);
            //parameters for determining loot such as luck, origin and position
            LootContextParameterSet lootContextParameterSet = (new LootContextParameterSet.Builder((ServerWorld) world)).add(LootContextParameters.ORIGIN, Vec3d.ofCenter(entity.getPos())).luck(0).build(LootContextTypes.CHEST);

            //returns ArrayList of ItemStacks that get generated by the LootTable
            ObjectArrayList<ItemStack> objectArrayList = lootTable.generateLoot(lootContextParameterSet, world.random.nextLong());

            //return first LootTable entry as crafting output
            if (objectArrayList.isEmpty()) {
                return ItemStack.EMPTY;
            }
            if (objectArrayList.size() == 1) {
                return objectArrayList.get(0);
            }
        }
        return ItemStack.EMPTY;
    }

    private static boolean hasRecipe(ArcheologyTableBlockEntity entity) {
        //recipe type can be configured here
        SimpleInventory inventory = new SimpleInventory(entity.size());

        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        boolean hasShardInFirstSlot = entity.getStack(1).getItem() == ModItems.UNIDENTIFIED_ARTIFACT;                     //Input
        boolean hasBrushInSlot = entity.getStack(0).getItem() == ModItems.IRON_BRUSH ||
                entity.getStack(0).getItem() == ModItems.DIAMOND_BRUSH ||
                entity.getStack(0).getItem() == Items.BRUSH;
        return hasShardInFirstSlot && hasBrushInSlot && canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, entity.getStack(2).getItem());
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        //only extract on the bottom
        return side == Direction.DOWN && slot == 2;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        //no insertion into the output slot
        if (side == Direction.DOWN) {
            return false;
        }
        //if the top is targeted and the item is a Brush, insert
        if (side == Direction.UP) {
            return slot == 0 && stack.getItem() instanceof BrushItem;
        }
        //for the sides: if it is an unidentified artifact
        return slot == 1 && stack.isOf(ModItems.UNIDENTIFIED_ARTIFACT);
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, Item output) {
        return inventory.getStack(2).getItem() == output || inventory.getStack(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(2).getMaxCount() > inventory.getStack(2).getCount();
    }

    public List<ItemStack> getInventoryContents() {
        return Arrays.asList(this.getStack(0), this.getStack(1), this.getStack(2));
    }

    public void setInventory(DefaultedList<ItemStack> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            this.inventory.set(i, inventory.get(i));
        }
    }

    @Override
    public void markDirty() {
        if (world != null && !world.isClient()) {
            PacketByteBuf data = PacketByteBufs.create();
            data.writeInt(inventory.size());
            for (ItemStack itemStack : inventory) {
                data.writeItemStack(itemStack);
            }
            data.writeBlockPos(getPos());

            for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
                ServerPlayNetworking.send(player, ModMessages.ITEM_SYNC, data);
            }
        }

        super.markDirty();
    }
}
