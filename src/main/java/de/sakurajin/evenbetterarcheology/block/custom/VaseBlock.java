package de.sakurajin.evenbetterarcheology.block.custom;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockGenerateable;
import de.sakurajin.evenbetterarcheology.util.ServerPlayerHelper;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.blockstate.JVariant;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class VaseBlock extends Block implements BlockGenerateable {
    private static final VoxelShape SHAPE = Block.createCuboidShape(3, 0, 3, 13, 14, 13);
    //advancement id for granting the advancement in onBreak, condition of advancement is "impossible" and needs to be executed here
    Identifier ADVANCEMENT_ID = new Identifier(EvenBetterArcheology.DATA.MOD_ID, "loot_vase_broken");
    String[] texture_variants;
    boolean isLootVase;
    public VaseBlock(String [] texture_variants, boolean isLootVase) {
        super(FabricBlockSettings.copy(Blocks.FLOWER_POT).sounds(BlockSoundGroup.DECORATED_POT));
        this.texture_variants = texture_variants;
        this.isLootVase = isLootVase;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (isLootVase && !world.isClient()) {
            //if the players is not in creative and doesn't silk-touch the vase
            if (!player.isCreative() && EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, player.getMainHandStack()) <= 0) {
                //spawn xpOrbs
                Entity xpOrb = new ExperienceOrbEntity(world, pos.getX(), pos.getY(), pos.getZ(), 4);
                world.spawnEntity(xpOrb);
            }
            ServerPlayerHelper.getServerPlayer(player).getAdvancementTracker().grantCriterion(world.getServer().getAdvancementLoader().get(ADVANCEMENT_ID), "criteria"); //gets the AdvancementLoader of the ServerPlayer and grants him the custom criteria called "criteria"
            // will not get executed when advancement is already granted
        }
        super.onBreak(world, pos, state, player);
    }

    //Similar code that also gets executed when InfestedBlock is broke to spawn a SilverFish
    private static void spawnSilverFish(ServerWorld world, BlockPos pos){
        SilverfishEntity silverfishEntity = EntityType.SILVERFISH.create(world);
        if (silverfishEntity != null) {
            silverfishEntity.refreshPositionAndAngles((double)pos.getX() + 0.5, (double)pos.getY(), (double)pos.getZ() + 0.5, 0.0F, 0.0F);
            world.spawnEntity(silverfishEntity);
            silverfishEntity.playSpawnEffects();
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void generateBlockModel(DatagenModContainer container, String identifier) {}

    @Override
    public void generateBlockState(DatagenModContainer container, String identifier) {
        JVariant variants = new JVariant();
        for (String texture_variant : texture_variants) {
            variants.put("", JState.model(EvenBetterArcheology.DATA.MOD_ID + ":block/vase/vase_" + texture_variant));
        }

        container.RESOURCE_PACK.addBlockState(
            JState.state(variants),
            new Identifier(EvenBetterArcheology.DATA.MOD_ID, identifier)
        );
    }

    @Override
    public void generateItemModel(DatagenModContainer container, String identifier) {
        String texture = container.DATA_GEN_HELPER.getStringID("vase/vase_"+texture_variants[0], "block");
        container.DATA_GEN_HELPER.generateItemModel(identifier,texture);
    }

    @Override
    public ItemConvertible generateBlockItem(DatagenModContainer container, String identifier) {
        return container.DATA_GEN_HELPER.generateBlockItem(this, container.settings(!isLootVase));
    }

    @Override
    public void generateRecepie(DatagenModContainer container, String identifier) {}
}
