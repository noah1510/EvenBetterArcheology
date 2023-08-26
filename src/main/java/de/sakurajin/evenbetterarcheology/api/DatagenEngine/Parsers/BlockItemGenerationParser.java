package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Parsers;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Annotations.ItemOptions.ItemRarity;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Annotations.ItemOptions.NoItemGroup;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockItemGenerateable;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.lang.reflect.Field;

public class BlockItemGenerationParser implements AnnotationParser{
    @Override
    public void parse(String namespace, ItemConvertible value, String identifier, Field field, DatagenModContainer container) {
        if (!(value instanceof Block)) return;

        Item blockItem;
        if (value instanceof BlockItemGenerateable){
            blockItem = (Item) ((BlockItemGenerateable) value).generateBlockItem(container, identifier);
        }else {
            // create basic item settings
            if (field.isAnnotationPresent(BlockRegistryContainer.NoBlockItem.class)) return;

            var settings = container.settings(!field.isAnnotationPresent(NoItemGroup.class));

            if (field.isAnnotationPresent(ItemRarity.class)) {
                Rarity rarity = switch (field.getAnnotation(ItemRarity.class).value()) {
                    case UNCOMMON -> Rarity.UNCOMMON;
                    case RARE -> Rarity.RARE;
                    case EPIC -> Rarity.EPIC;
                    default -> Rarity.COMMON;
                };
                settings.rarity(rarity);
            }
            blockItem = new BlockItem((Block) value, settings);
        }
        // register the block item
        Registry.register(Registries.ITEM, new Identifier(namespace, identifier), blockItem);
    }
}
