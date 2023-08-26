package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Parsers;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Annotations.ItemOptions.NoItemModel;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.ItemModelGeneratateable;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;

import java.lang.reflect.Field;

public class ItemModelGenerationParser implements AnnotationParser{
    @Override
    public void parse(String namespace, ItemConvertible value, String identifier, Field field, DatagenModContainer container) {
        if (field.isAnnotationPresent(BlockRegistryContainer.NoBlockItem.class)) return;

        if(value instanceof ItemModelGeneratateable){
            ((ItemModelGeneratateable) value).generateItemModel(container, identifier);
        }else{
            // generate the item model if wanted
            if (!field.isAnnotationPresent(NoItemModel.class)) {
                if (value instanceof Block){
                    String parentTexture = container.MOD_ID + ":block/" + identifier;
                    container.DATA_GEN_HELPER.generateItemModel(identifier, parentTexture);
                }else{
                    container.DATA_GEN_HELPER.generateItemModel(identifier, "minecraft:item/generated", identifier);
                }

            }
        }
    }
}
