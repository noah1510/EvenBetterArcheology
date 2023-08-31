package de.sakurajin.evenbetterarcheology.block.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockGenerateable;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.LootDistributionHelper;
import de.sakurajin.evenbetterarcheology.block.entity.ArcheologyTableBlockEntity;
import de.sakurajin.evenbetterarcheology.block.entity.ModBlockEntities;
import net.devtech.arrp.json.loot.JEntry;
import net.devtech.arrp.json.loot.JLootTable;
import net.devtech.arrp.json.loot.JPool;
import net.devtech.arrp.json.recipe.*;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ArchelogyTable extends BlockWithEntity implements BlockGenerateable {
    //indicates if the table is currently "crafting" the identified artifact
    //triggers particle creation
    public static final BooleanProperty DUSTING = BooleanProperty.of("dusting");

    public ArchelogyTable(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState) this.stateManager.getDefaultState().with(DUSTING, false));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DUSTING);
    }

    /* BLOCK ENTITY STUFF */

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    //Drops Items present in the table at the time of destruction
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ArcheologyTableBlockEntity) {
                ItemScatterer.spawn(world, pos, (ArcheologyTableBlockEntity) blockEntity);
                world.updateComparators(pos, this);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    //Creates the Screen-Handler belonging to the BlockEntity
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory handledScreen = state.createScreenHandlerFactory(world, pos);

            if (handledScreen != null) {
                player.openHandledScreen(handledScreen);
            }
        }

        return ActionResult.SUCCESS;

    }

    // creates ArcheologyTableBlockEntity for each ArcheologyTable
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ArcheologyTableBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.ARCHEOLOGY_TABLE, ArcheologyTableBlockEntity::tick);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (world.isClient() && state.get(DUSTING)) {
            addDustParticles(world, pos, random);
        }
        super.randomDisplayTick(state, world, pos, random);
    }

    public void addDustParticles(World world, BlockPos pos, Random random) {
        if (random.nextBoolean()) {
            return;
        } //create particles half of the time
        int i = random.nextBetweenExclusive(1, 3); //number of particles to be created

        BlockStateParticleEffect blockStateParticleEffect = new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.SAND.getDefaultState());

        for (int j = 0; j < i; ++j) {
            //centering Block position
            //setting base velocity to 3 and multiplying it with rand double with random sign
            //that way particles can spread in every direction by chance
            world.addParticle(blockStateParticleEffect,
                    pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
                    3.0 * random.nextDouble() * (random.nextBoolean() ? 1 : -1),
                    0.0,
                    3.0 * random.nextDouble() * (random.nextBoolean() ? 1 : -1));
        }
    }

    @Override
    public void generateBlockState(DatagenModContainer container, String identifier) {
        container.generateBlockState(identifier);
    }

    @Override
    public void generateRecepie(DatagenModContainer container, String identifier) {
        container.RESOURCE_PACK.addRecipe(container.getSimpleID(identifier),
            JRecipe.shaped(
                JPattern.pattern(
                    "bS",
                    "OO",
                    "OO"
                ),
                JKeys.keys()
                    .key("b", JIngredient.ingredient().tag("c:brushitems"))
                    .key("S", JIngredient.ingredient().item("evenbetterarcheology:artifact_shards"))
                    .key("O", JIngredient.ingredient().tag("minecraft:planks")),
                JResult.item(this.asItem())
            )
        );
    }

    @Override
    public void generateBlockModel(DatagenModContainer container, String identifier) {}

    @Override
    public void generateTags(DatagenModContainer container, String identifier) {
        container.addTag("minecraft:blocks/mineable/axe", identifier);
        container.addTag("minecraft:point_of_interest_type/acquirable_job_site", identifier+"_poi");
    }

    private JEntry createModEnchantmentBook(
            String enchantmentID,
            String translationKey,
            int level,
            int weight
    ){
        JsonObject enchantment = new JsonObject();
        enchantment.addProperty(enchantmentID, level);

        JsonArray loreArray = new JsonArray();
        JsonObject lore = new JsonObject();
        lore.addProperty("translate", translationKey);
        lore.addProperty("color", "dark_gray");
        loreArray.add(lore);

        JsonObject name = new JsonObject();
        name.addProperty("translate", "item.evenbetterarcheology.identified_artifact");

        return JLootTable.entry()
            .type("minecraft:item")
            .name("minecraft:enchanted_book")
            .weight(weight)
            .function(JLootTable.function("minecraft:set_enchantments").parameter("enchantments", enchantment))
            .function(JLootTable.function("minecraft:set_lore").parameter("lore", loreArray))
            .function(JLootTable.function("minecraft:set_name").parameter("name", name));
    }

    @Override
    public void generateLootTable(DatagenModContainer container, String identifier){
        container.createBlockLootTable(identifier, null);

        JPool identifyPool = JLootTable.pool().rolls(1);
        identifyPool.entry(createModEnchantmentBook("evenbetterarcheology:penetrating_strike", "item.evenbetterarcheology.penetrating_strike", 1, 80));

        int soaringWindsMaxLevel = EvenBetterArcheology.CONFIG.SOARING_WINDS_MAXLEVEL();
        var scaleWeigths = LootDistributionHelper.getDistribution(3, soaringWindsMaxLevel);
        for(int i = 0; i < soaringWindsMaxLevel; i++){
            int weight = (int) Math.ceil(scaleWeigths.get(i) * 90);
            identifyPool.entry(createModEnchantmentBook("evenbetterarcheology:soaring_winds", "item.evenbetterarcheology.soaring_winds", i+1, weight));
        }

        identifyPool.entry(createModEnchantmentBook("evenbetterarcheology:tunneling", "item.evenbetterarcheology.tunneling_tooltip", 1, 80));

        if(FabricLoader.getInstance().isModLoaded("artifacts")){
            identifyPool.entry(
                JLootTable.entry()
                    .type("minecraft:loot_table")
                    .name("artifacts:artifact")
                    .weight(20)
            );
        }

        container.RESOURCE_PACK.addLootTable(
            container.getSimpleID("identifying_loot"),
            JLootTable.loot("minecraft:generic").pool(identifyPool)
        );
    }
}
