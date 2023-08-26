package de.sakurajin.evenbetterarcheology.block.fossils;

import de.sakurajin.evenbetterarcheology.api.block.FossilBaseHeadBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class OcelotFossilHeadBlock extends FossilBaseHeadBlock {
    private static final VoxelShape OCELOT_HEAD_SHAPE = Block.createCuboidShape(4, 0, 4, 12, 4, 12);

    public OcelotFossilHeadBlock() {
        super(
            FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE),
            new String[]{
                "fossils/ocelot_fossil_head_0",
                "fossils/ocelot_fossil_head_1",
                "fossils/ocelot_fossil_head_2",
            },
            0,
            OCELOT_HEAD_SHAPE
        );
    }
}
