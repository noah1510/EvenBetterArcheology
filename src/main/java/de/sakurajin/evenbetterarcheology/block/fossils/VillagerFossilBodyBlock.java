package de.sakurajin.evenbetterarcheology.block.fossils;

import com.google.common.collect.ImmutableMap;
import de.sakurajin.evenbetterarcheology.api.block.FossilBaseBodyBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

import java.util.Map;

public class VillagerFossilBodyBlock extends FossilBaseBodyBlock {
    //Map of hitboxes for every direction the model can be facing
    private static final Map<Direction, VoxelShape> SHAPES_FOR_DIRECTION = ImmutableMap.of(
            Direction.NORTH, Block.createCuboidShape(0, 0, 8, 16, 12, 15),
            Direction.SOUTH, Block.createCuboidShape(0, 0, 1, 16, 12, 8),
            Direction.EAST, Block.createCuboidShape(1, 0, 0, 8, 12, 16),
            Direction.WEST, Block.createCuboidShape(8, 0, 0, 15, 12, 16));

    public VillagerFossilBodyBlock() {
        super(
            FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE),
            new String[]{
                "fossils/villager_fossil_body_0",
                "fossils/villager_fossil_body_1",
                "fossils/villager_fossil_body_2",
            },
            0,
            SHAPES_FOR_DIRECTION
        );
    }
}
