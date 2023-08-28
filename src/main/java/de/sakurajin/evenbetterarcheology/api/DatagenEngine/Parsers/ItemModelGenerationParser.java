package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Parsers;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.ItemModelGenerateable;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.minecraft.item.ItemConvertible;

import java.lang.reflect.Field;

/**
 * This parser is used to generate item models for items that implement the ItemModelGeneratateable interface
 * If no block item should be generated this parsed exits early
 */
public class ItemModelGenerationParser implements DataGenerationParser {
    @Override
    public void parse(String namespace, ItemConvertible value, String identifier, Field field, DatagenModContainer container) {
        if (field.isAnnotationPresent(BlockRegistryContainer.NoBlockItem.class)) return;
        if (!(value instanceof ItemModelGenerateable)) return;

        //generate the item model
        ((ItemModelGenerateable) value).generateItemModel(container, identifier);
    }
}
