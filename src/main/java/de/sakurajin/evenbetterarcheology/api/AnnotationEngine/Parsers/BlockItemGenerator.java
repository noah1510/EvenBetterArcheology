package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Parsers;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Annotations.ItemOptions.ItemRarity;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Annotations.BlockItemOptions.BlockItemTexture;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Annotations.ItemOptions.NoItemGroup;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Annotations.ItemOptions.NoItemModel;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.lang.reflect.Field;

public class BlockItemGenerator implements AnnotationParser{
    @Override
    public void parse(String namespace, ItemConvertible value, String identifier, Field field, DatagenModContainer container) {
        if (field.isAnnotationPresent(BlockRegistryContainer.NoBlockItem.class)) return;
        if (!(value instanceof Block)) return;

        // create basic item settings
        var settings = new OwoItemSettings();

        // check if our annotation is present and only
        // assign the itemgroup if it is not
        if (!field.isAnnotationPresent(NoItemGroup.class)){
            settings.group(container.GROUP);
        }

        if (field.isAnnotationPresent(ItemRarity.class)){
            Rarity rarity = switch (field.getAnnotation(ItemRarity.class).value()) {
                case UNCOMMON -> Rarity.UNCOMMON;
                case RARE -> Rarity.RARE;
                case EPIC -> Rarity.EPIC;
                default -> Rarity.COMMON;
            };
            settings.rarity(rarity);
        }

        // register the block item
        Registry.register(Registries.ITEM, new Identifier(namespace, identifier), new BlockItem((Block)value, settings));

        // generate the item model if wanted
        if(!field.isAnnotationPresent(NoItemModel.class)) {
            String parentTexture = container.MOD_ID + ":block/" + identifier;
            if (field.isAnnotationPresent(BlockItemTexture.class)) {
                parentTexture = field.getAnnotation(BlockItemTexture.class).value();
            }

            container.MODEL_GENERATION_HELPER.generateItemModel(identifier, parentTexture);
        }
    }
}
