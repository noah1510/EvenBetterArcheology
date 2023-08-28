package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Parsers;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockItemGenerateable;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;

/**
 * This parser is used to generate block items for blocks that implement the BlockItemGenerateable interface
 */
public class BlockItemGenerationParser implements DataGenerationParser {
    @Override
    public void parse(String namespace, ItemConvertible value, String identifier, Field field, DatagenModContainer container) {
        if (!(value instanceof Block)) return;
        if (!(value instanceof BlockItemGenerateable)) return;

        //generate the block item
        Item blockItem = (Item) ((BlockItemGenerateable) value).generateBlockItem(container, identifier);

        // register the block item
        Registry.register(Registries.ITEM, new Identifier(namespace, identifier), blockItem);

    }
}
