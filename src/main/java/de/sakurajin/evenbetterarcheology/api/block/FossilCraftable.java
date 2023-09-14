package de.sakurajin.evenbetterarcheology.api.block;

import de.sakurajin.sakuralib.util.DatagenModContainer;
import net.devtech.arrp.json.recipe.JIngredient;
import net.devtech.arrp.json.recipe.JIngredients;
import net.devtech.arrp.json.recipe.JRecipe;
import net.devtech.arrp.json.recipe.JResult;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

import java.util.Map;

public abstract class FossilCraftable extends FossilBase {

    protected String[] craftingParts;

    protected FossilCraftable(Settings settings, String[] textureVariants, int blockItemIndex, VoxelShape SHAPE) {
        this(settings, textureVariants, blockItemIndex, SHAPE, null);
    }

    protected FossilCraftable(Settings settings, String[] textureVariants, int blockItemIndex, VoxelShape SHAPE, String translationSuffixKey) {
        super(settings, textureVariants, blockItemIndex, SHAPE, translationSuffixKey, false);
    }

    protected FossilCraftable(Settings settings, String[] textureVariants, int blockItemIndex, Map<Direction, VoxelShape> SHAPE_DIRECTED) {
        this(settings, textureVariants, blockItemIndex, SHAPE_DIRECTED, null);
    }

    protected FossilCraftable(Settings settings, String[] textureVariants, int blockItemIndex, Map<Direction, VoxelShape> SHAPE_DIRECTED, String translationSuffixKey) {
        super(settings, textureVariants, blockItemIndex, SHAPE_DIRECTED, translationSuffixKey, false);
    }

    public ItemConvertible generateData(DatagenModContainer container, String identifier) {
        if(craftingParts != null && craftingParts.length > 0){
            JIngredients ingredients = JIngredients.ingredients();
            for (String craftingPart : craftingParts) {
                ingredients.add(JIngredient.ingredient().item(container.getStringID(craftingPart)));
            }
            container.RESOURCE_PACK.addRecipe(container.getSimpleID(identifier),
                    JRecipe.shapeless(ingredients, JResult.result(container.getStringID(identifier)))
            );
        }

        return super.generateData(container, identifier);
    }
}
