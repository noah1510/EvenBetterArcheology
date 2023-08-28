package de.sakurajin.evenbetterarcheology.api.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
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

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> checkType(
            BlockEntityType<A> givenType, BlockEntityType<E> expectedType, BlockEntityTicker<? super E> ticker
    ) {
        if(expectedType != givenType) return null;
        //noinspection unchecked
        return (BlockEntityTicker<A>) ticker;
    }

    //Creates the Screen-Handler belonging to the BlockEntity
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory handledScreen = state.createScreenHandlerFactory(world, pos);

            if (handledScreen != null) {
                player.openHandledScreen(handledScreen);
            }
        }

        return ActionResult.SUCCESS;
    }

}
