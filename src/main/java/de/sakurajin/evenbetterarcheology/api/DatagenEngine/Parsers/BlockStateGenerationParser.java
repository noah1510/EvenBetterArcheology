package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Parsers;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockStateGenerateable;
import net.minecraft.item.ItemConvertible;

import java.lang.reflect.Field;


/**
 * This parser is used to generate block states for blocks that implement the BlockStateGenerateable interface
 */
public class BlockStateGenerationParser implements DataGenerationParser {
    @Override
    public void parse(String namespace, ItemConvertible value, String identifier, Field field, DatagenModContainer container) {
        if (!(value instanceof BlockStateGenerateable)) return;

        //generate the block state
        ((BlockStateGenerateable) value).generateBlockState(container, identifier);
    }
}
