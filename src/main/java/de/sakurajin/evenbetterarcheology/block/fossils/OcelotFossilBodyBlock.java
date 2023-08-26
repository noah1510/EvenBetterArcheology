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

public class OcelotFossilBodyBlock extends FossilBaseBodyBlock {
    //Map of hitboxes for every direction the model can be facing
    private static final Map<Direction, VoxelShape> SHAPES_FOR_DIRECTION = ImmutableMap.of(
            Direction.NORTH, Block.createCuboidShape(4, 0, 0, 12, 7, 16),
            Direction.SOUTH, Block.createCuboidShape(4, 0, 0, 12, 7, 16),
            Direction.EAST, Block.createCuboidShape(0, 0, 4, 16, 7, 12),
            Direction.WEST, Block.createCuboidShape(0, 0, 4, 16, 7, 12));

    public OcelotFossilBodyBlock() {
        super(
            FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE),
            new String[]{
                "fossils/ocelot_fossil_body_0",
                "fossils/ocelot_fossil_body_1"
            },
            0,
            SHAPES_FOR_DIRECTION
        );
    }
}
