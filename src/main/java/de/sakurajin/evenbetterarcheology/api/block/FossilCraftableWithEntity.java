package de.sakurajin.evenbetterarcheology.api.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public abstract class FossilCraftableWithEntity extends FossilCraftable implements BlockEntityProvider {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    protected FossilCraftableWithEntity(Settings settings, String[] textureVariants, int blockItemIndex, VoxelShape SHAPE) {
        super(settings, textureVariants, blockItemIndex, SHAPE);
    }

    protected FossilCraftableWithEntity(Settings settings, String[] textureVariants, int blockItemIndex, Map<Direction, VoxelShape> SHAPE_DIRECTED) {
        super(settings, textureVariants, blockItemIndex, SHAPE_DIRECTED);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> checkType(
            BlockEntityType<A> givenType, BlockEntityType<E> expectedType, BlockEntityTicker<? super E> ticker
    ) {
        if(expectedType != givenType) return null;
        return (BlockEntityTicker<A>) ticker;
    }
}
