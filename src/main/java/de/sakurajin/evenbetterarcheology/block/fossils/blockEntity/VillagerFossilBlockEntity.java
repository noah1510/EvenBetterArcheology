package de.sakurajin.evenbetterarcheology.block.fossils.blockEntity;

import de.sakurajin.evenbetterarcheology.api.block.entity.BlockEntityWithInventory;
import de.sakurajin.evenbetterarcheology.registry.ModBlockEntities;
import de.sakurajin.evenbetterarcheology.block.fossils.VillagerFossilFull;
import de.sakurajin.evenbetterarcheology.registry.ModNetworking;
import de.sakurajin.evenbetterarcheology.screen.FossilInventoryScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class VillagerFossilBlockEntity extends BlockEntityWithInventory implements NamedScreenHandlerFactory  {
    public VillagerFossilBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.VILLAGER_FOSSIL, pos, state, DefaultedList.ofSize(1, ItemStack.EMPTY));
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
    }

    @Override
    public Text getDisplayName(){
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    //update luminance of block based on the luminance of the item given when it would be in its placed state
    @Override
    public void onClose(PlayerEntity player) {
        super.onClose(player);
        int luminance = Block.getBlockFromItem(this.getInventoryContents().get(0).getItem()).getDefaultState().getLuminance();
        player.getWorld().setBlockState(this.getPos(), Objects.requireNonNull(world).getBlockState(this.getPos()).with(VillagerFossilFull.INVENTORY_LUMINANCE, luminance));
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player){
        return new FossilInventoryScreenHandler(syncId, inv, this);
    }

    @Override
    public void markDirty() {
        if(world != null && !world.isClient()) {
            int luminance = Block.getBlockFromItem(this.getInventoryContents().get(0).getItem()).getDefaultState().getLuminance();
            world.setBlockState(this.getPos(), getCachedState().with(VillagerFossilFull.INVENTORY_LUMINANCE, luminance));
        }

        super.markDirty();
    }
}
