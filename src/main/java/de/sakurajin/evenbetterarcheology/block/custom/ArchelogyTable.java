package de.sakurajin.evenbetterarcheology.block.custom;

import de.sakurajin.evenbetterarcheology.api.enchantment.ArtifactEnchantment;
import de.sakurajin.evenbetterarcheology.api.enchantment.ArtifactEnchantmentRegistry;
import de.sakurajin.sakuralib.datagen.v1.DatagenModContainer;
import de.sakurajin.sakuralib.datagen.v1.DataGenerateable;
import de.sakurajin.evenbetterarcheology.block.entity.ArcheologyTableBlockEntity;
import de.sakurajin.evenbetterarcheology.registry.ModBlockEntities;
import net.devtech.arrp.json.loot.JLootTable;
import net.devtech.arrp.json.recipe.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ArchelogyTable extends BlockWithEntity implements DataGenerateable {
    //indicates if the table is currently "crafting" the identified artifact
    //triggers particle creation
    public static final BooleanProperty DUSTING = BooleanProperty.of("dusting");
    public static final Identifier IDENTIFY_LOOT_TABLE = new Identifier("evenbetterarcheology", "identifying_loot");

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
    public ItemConvertible generateData(DatagenModContainer container, String identifier) {
        container.generateBlockState(identifier);

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
                        JResult.result(container.getStringID(identifier))
                )
        );

        container.createBlockLootTable(identifier, null);

        //create the loot table for the identifying process
        //by default, the loot table only contains the artifact enchantments
        container.RESOURCE_PACK.addLootTable(
            IDENTIFY_LOOT_TABLE,
            JLootTable.loot("minecraft:generic").pool(
                JLootTable
                    .pool()
                    .rolls(1)
                    .entry(ArtifactEnchantmentRegistry.getLootTableEntry().weight(300))
            )
        );

        container.addTag("minecraft:blocks/mineable/axe", identifier);
        container.addTag("minecraft:point_of_interest_type/acquirable_job_site", identifier+"_poi");

        container.generateBlockItemModel(identifier);
        return container.generateBlockItem(this, container.settings());
    }
}
