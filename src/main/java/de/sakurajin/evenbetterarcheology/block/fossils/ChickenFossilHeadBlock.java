package de.sakurajin.evenbetterarcheology.block.fossils;

import de.sakurajin.evenbetterarcheology.api.block.FossilBaseHeadBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.shape.VoxelShape;

public class ChickenFossilHeadBlock extends FossilBaseHeadBlock {
    private static final VoxelShape CHICKEN_HEAD_SHAPE = Block.createCuboidShape(4, 0, 4, 12, 4, 12);

    public ChickenFossilHeadBlock() {
        super(
            FabricBlockSettings.copyOf(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE),
            new String[]{
                "fossils/chicken_fossil_head_0",
                "fossils/chicken_fossil_head_1",
                "fossils/chicken_fossil_head_2"},
            0,
            CHICKEN_HEAD_SHAPE
        );
    }
}
