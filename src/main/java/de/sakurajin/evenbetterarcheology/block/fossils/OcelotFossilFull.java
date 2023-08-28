package de.sakurajin.evenbetterarcheology.block.fossils;

import com.google.common.collect.ImmutableMap;
import de.sakurajin.evenbetterarcheology.api.block.FossilCraftableWithEntity;
import de.sakurajin.evenbetterarcheology.block.fossils.blockEntity.FleeFromBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.stream.Stream;

public class OcelotFossilFull extends FossilCraftableWithEntity {
    //Map of hitboxes for every direction the model can be facing
    private static final Map<Direction, VoxelShape> OCELOT_SHAPES_FOR_DIRECTION = ImmutableMap.of(
            Direction.NORTH, Stream.of(
                    createCuboidShape(5.5, 0, 0, 11.5, 9.5, 17.75),
                    createCuboidShape(6, 5, -7, 11, 10, 1)).reduce(VoxelShapes::union).get(),
            Direction.SOUTH, Stream.of(
                    createCuboidShape(5.5, 0, -1.75, 11.5, 9.5, 16),
                    createCuboidShape(6, 5, 15, 11, 10, 23)).reduce(VoxelShapes::union).get(),
            Direction.EAST, Stream.of(
                    createCuboidShape(-1.25, 0, 5, 16.5, 9.5, 11),
                    createCuboidShape(15.5, 5, 5.5, 23.5, 10, 10.5)).reduce(VoxelShapes::union).get(),
            Direction.WEST, Stream.of(
                    createCuboidShape(0.5, 0, 5, 18.25, 9.5, 11),
                    createCuboidShape(-6.5, 5, 5.5, 1.5, 10, 10.5)).reduce(VoxelShapes::union).get());

    public OcelotFossilFull() {
        super(
            FabricBlockSettings.copyOf(Blocks.BONE_BLOCK),
            new String[]{
                "fossils/ocelot_fossil_0",
                "fossils/ocelot_fossil_1",
                "fossils/ocelot_fossil_2"
            },
            0,
            OCELOT_SHAPES_FOR_DIRECTION
        );

        this.craftingParts = new String[]{"ocelot_fossil_head", "ocelot_fossil_body"};
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FleeFromBlockEntity(pos, state);
    }

}
