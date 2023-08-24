package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Containers;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Annotations.ItemOptions.NoItemGroup;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Annotations.ItemOptions.NoItemModel;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Generation.ItemModelGeneratateable;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Generation.RecepieGeneratable;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import io.wispforest.owo.util.pond.OwoItemExtensions;
import net.minecraft.item.Item;

import java.lang.reflect.Field;


public abstract class ParsedItemRegistryContainer extends ParsedContainerBase implements ItemRegistryContainer {
    protected ParsedItemRegistryContainer(DatagenModContainer initializer) {
        super(initializer);
    }

    @Override
    public void postProcessField(String namespace, Item value, String identifier, Field field) {
        if (!field.isAnnotationPresent(NoItemGroup.class)){
            //ignore @ApiStatus.Internal
            ((OwoItemExtensions) value).owo$setGroup(initializer.GROUP);
        }

        if (!field.isAnnotationPresent(NoItemModel.class)){
            if (value instanceof ItemModelGeneratateable){
                ((ItemModelGeneratateable) value).generateItemModel(initializer, identifier);
            }else{
                initializer.MODEL_GENERATION_HELPER.generateItem(identifier, "minecraft:item/generated", identifier);
            }
        }

        if (value instanceof RecepieGeneratable) {
            ((RecepieGeneratable) value).generateRecepie(initializer, identifier);
        }
    }

}
