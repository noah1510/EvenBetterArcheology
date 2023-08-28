package de.sakurajin.evenbetterarcheology.block.fossils;

import com.google.common.collect.ImmutableMap;
import de.sakurajin.evenbetterarcheology.api.block.FossilBase;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

import java.util.Map;

public class SheepFossilBodyBlock extends FossilBase {
    //Map of hitboxes for every direction the model can be facing
    private static final Map<Direction, VoxelShape> SHAPES_FOR_DIRECTION = ImmutableMap.of(
            Direction.NORTH, Block.createCuboidShape(0, 0, 2, 16, 8, 16),
            Direction.SOUTH, Block.createCuboidShape(0, 0, 0, 16, 8, 14),
            Direction.EAST, Block.createCuboidShape(2, 0, 0, 16, 8, 16),
            Direction.WEST, Block.createCuboidShape(0, 0, 0, 14, 8, 16));
    public SheepFossilBodyBlock() {
        super(
            FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE),
            new String[]{
                "fossils/sheep_fossil_body_0",
                "fossils/sheep_fossil_body_1",
            },
            1,
            SHAPES_FOR_DIRECTION,
            "block.evenbetterarcheology.fossil_body_set"
        );
    }

}
