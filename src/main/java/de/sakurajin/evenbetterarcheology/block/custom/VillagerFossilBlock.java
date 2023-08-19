package de.sakurajin.evenbetterarcheology.block.custom;

import com.google.common.collect.ImmutableMap;
import de.sakurajin.evenbetterarcheology.block.entity.VillagerFossilBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.stream.Stream;

public class VillagerFossilBlock extends FossilBaseWithEntityBlock implements BlockEntityProvider {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final IntProperty INVENTORY_LUMINANCE = IntProperty.of("inventory_luminance", 0, 15); //used to store the amount of light that the item in its inventory would emit and to emit that luminance itself

    //Map of hitboxes for every direction the model can be facing
    private static final Map<Direction, VoxelShape> VILLAGER_SHAPES_FOR_DIRECTION = ImmutableMap.of(
            Direction.NORTH, Stream.of(
                    createCuboidShape(4.75, 0, 9, 11, 10, 12),
                    createCuboidShape(4, 10, 7, 12, 20, 12.5),
                    createCuboidShape(3, 20, 2, 11, 29, 7.5)).reduce(VoxelShapes::union).get(),
            Direction.SOUTH, Stream.of(
                    createCuboidShape(5, 0, 4, 11.25, 10, 7),
                    createCuboidShape(4, 10, 3.5, 12, 20, 9),
                    createCuboidShape(5, 20, 8.5, 13, 29, 14)).reduce(VoxelShapes::union).get(),
            Direction.EAST, Stream.of(
                    createCuboidShape(4, 0, 4.75, 7, 10, 11),
                    createCuboidShape(3.5, 10, 4, 9, 20, 12),
                    createCuboidShape(8.5, 20, 3, 14, 29, 11)).reduce(VoxelShapes::union).get(),
            Direction.WEST, Stream.of(
                    createCuboidShape(9, 0, 5, 12, 10, 11.25),
                    createCuboidShape(7, 10, 4, 12.5, 20, 12),
                    createCuboidShape(2, 20, 5, 7.5, 29, 13)).reduce(VoxelShapes::union).get());

    public VillagerFossilBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(INVENTORY_LUMINANCE, 0));
    }

    //Drops Items present in the table at the time of destruction//
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof VillagerFossilBlockEntity) {
                ItemScatterer.spawn(world, pos, (VillagerFossilBlockEntity) blockEntity);
                world.updateComparators(pos, this);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new VillagerFossilBlockEntity(pos, state);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VILLAGER_SHAPES_FOR_DIRECTION.get(state.get(FACING));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(INVENTORY_LUMINANCE);
    }
}
