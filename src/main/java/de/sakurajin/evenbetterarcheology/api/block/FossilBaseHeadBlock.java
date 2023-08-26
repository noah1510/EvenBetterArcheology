package de.sakurajin.evenbetterarcheology.api.block;

import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

import java.util.Map;

public abstract class FossilBaseHeadBlock extends FossilBaseCommon {

    public FossilBaseHeadBlock(Settings settings, String[] textureVariants, int blockItemIndex, VoxelShape SHAPE) {
        this(settings, textureVariants, blockItemIndex, SHAPE, "block.evenbetterarcheology.fossil_head_set");
    }

    public FossilBaseHeadBlock(Settings settings, String[] textureVariants, int blockItemIndex, VoxelShape SHAPE, String translationSuffixKey) {
        super(settings, textureVariants, blockItemIndex, SHAPE, translationSuffixKey);
    }


    public FossilBaseHeadBlock(Settings settings, String[] textureVariants, int blockItemIndex, Map<Direction, VoxelShape> SHAPE_DIRECTED) {
        this(settings, textureVariants, blockItemIndex, SHAPE_DIRECTED, "block.evenbetterarcheology.fossil_head_set");
    }

    public FossilBaseHeadBlock(Settings settings, String[] textureVariants, int blockItemIndex, Map<Direction, VoxelShape> SHAPE_DIRECTED, String translationSuffixKey) {
        super(settings, textureVariants, blockItemIndex, SHAPE_DIRECTED, translationSuffixKey);
    }
}
