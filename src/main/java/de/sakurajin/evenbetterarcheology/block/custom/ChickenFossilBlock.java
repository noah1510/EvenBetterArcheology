package de.sakurajin.evenbetterarcheology.block.custom;

import com.google.common.collect.ImmutableMap;
import de.sakurajin.evenbetterarcheology.block.entity.ChickenFossilBlockEntity;
import de.sakurajin.evenbetterarcheology.block.entity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.stream.Stream;

public class ChickenFossilBlock extends FossilBaseWithEntityBlock {
    //Map of hitboxes for every direction the model can be facing
    private static final Map<Direction, VoxelShape> CHICKEN_SHAPES_FOR_DIRECTION = ImmutableMap.of(
            Direction.NORTH, Stream.of(
                    createCuboidShape(5, 0, 1.5, 11, 11.25, 12.25),
                    createCuboidShape(6.5, 11.25, -4, 9.5, 16, 3),
                    createCuboidShape(7, 8.25, 12.25, 9, 10, 22.25)).reduce(VoxelShapes::union).get(),
            Direction.SOUTH, Stream.of(
                    createCuboidShape(5, 0, 3.75, 11, 11.25, 14.5),
                    createCuboidShape(6.5, 11.25, 13, 9.5, 16, 20),
                    createCuboidShape(7, 8.25, -6.25, 9, 10, 3.75)).reduce(VoxelShapes::union).get(),
            Direction.WEST, Stream.of(
                    createCuboidShape(1.5, 0, 5, 12.25, 11.25, 11),
                    createCuboidShape(-4, 11.25, 6.5, 3, 16, 9.5),
                    createCuboidShape(12.25, 8.25, 7, 22.25, 10, 9)).reduce(VoxelShapes::union).get(),
            Direction.EAST, Stream.of(
                    createCuboidShape(3.75, 0, 5, 14.5, 11.25, 11),
                    createCuboidShape(13, 11.25, 6.5, 20, 16, 9.5),
                    createCuboidShape(-6.25, 8.25, 7, 3.75, 10, 9)).reduce(VoxelShapes::union).get());

    public ChickenFossilBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.CHICKEN_FOSSIL, ChickenFossilBlockEntity::tick);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return CHICKEN_SHAPES_FOR_DIRECTION.get(state.get(FACING));
    }
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ChickenFossilBlockEntity(pos, state);
    }
}
