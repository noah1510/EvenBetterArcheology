package de.sakurajin.evenbetterarcheology.api.block;

import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

import java.util.Map;

public abstract class FossilBaseBodyBlock extends FossilBaseCommon {

    public FossilBaseBodyBlock(Settings settings, String[] textureVariants, int blockItemIndex, VoxelShape SHAPE) {
        this(settings, textureVariants, blockItemIndex, SHAPE, "block.evenbetterarcheology.fossil_body_set");
    }

    public FossilBaseBodyBlock(Settings settings, String[] textureVariants, int blockItemIndex, VoxelShape SHAPE, String translationSuffixKey){
        super(settings, textureVariants, blockItemIndex, SHAPE, translationSuffixKey);
    }

    public FossilBaseBodyBlock(Settings settings, String[] textureVariants, int blockItemIndex, Map<Direction, VoxelShape> SHAPE_DIRECTED) {
        this(settings, textureVariants, blockItemIndex, SHAPE_DIRECTED, "block.evenbetterarcheology.fossil_body_set");
    }

    public FossilBaseBodyBlock(Settings settings, String[] textureVariants, int blockItemIndex, Map<Direction, VoxelShape> SHAPE_DIRECTED, String translationSuffixKey){
        super(settings, textureVariants, blockItemIndex, SHAPE_DIRECTED, translationSuffixKey);
    }

}
