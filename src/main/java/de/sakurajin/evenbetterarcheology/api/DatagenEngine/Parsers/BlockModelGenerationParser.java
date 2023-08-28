package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Parsers;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockModelGenerateable;
import net.minecraft.item.ItemConvertible;

import java.lang.reflect.Field;

/**
 * This parser is used to generate block models for blocks that implement the BlockModelGenerateable interface
 */
public class BlockModelGenerationParser implements DataGenerationParser {
    @Override
    public void parse(String namespace, ItemConvertible value, String identifier, Field field, DatagenModContainer container) {
        if(!(value instanceof BlockModelGenerateable)) return;

        //generate the block model
        ((BlockModelGenerateable) value).generateBlockModel(container, identifier);
    }
}
