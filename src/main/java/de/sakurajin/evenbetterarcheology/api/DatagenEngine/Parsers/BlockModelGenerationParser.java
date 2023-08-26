package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Parsers;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockModelGenerateable;
import net.minecraft.item.ItemConvertible;

import java.lang.reflect.Field;

public class BlockModelGenerationParser implements AnnotationParser{

    @Override
    public void parse(String namespace, ItemConvertible value, String identifier, Field field, DatagenModContainer container) {
        if (value instanceof BlockModelGenerateable){
            ((BlockModelGenerateable) value).generateBlockModel(container, identifier);
        }
    }
}
