package de.sakurajin.evenbetterarcheology.util;

import de.sakurajin.evenbetterarcheology.api.datagen.BetterBrushItemGenerator;
import de.sakurajin.evenbetterarcheology.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Items;

import java.util.function.Consumer;

public class DataGenerator implements DataGeneratorEntrypoint{
    static final BetterBrushItemGenerator IRON_BRUSH_GENERATOR = new BetterBrushItemGenerator(ModItems.IRON_BRUSH, Items.IRON_INGOT);
    static final BetterBrushItemGenerator DIAMOND_BRUSH_GENERATOR = new BetterBrushItemGenerator(ModItems.DIAMOND_BRUSH, Items.DIAMOND);

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(CraftingProvider::new);
        pack.addProvider(ModelProvider::new);
    }

    private static class CraftingProvider extends FabricRecipeProvider {
        public CraftingProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generate(Consumer<RecipeJsonProvider> exporter) {
            IRON_BRUSH_GENERATOR.generateCraftingRecepie(exporter);
            DIAMOND_BRUSH_GENERATOR.generateCraftingRecepie(exporter);
        }
    }

    private static class ModelProvider extends FabricModelProvider {
        public ModelProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {
            IRON_BRUSH_GENERATOR.generateModel(itemModelGenerator);
            DIAMOND_BRUSH_GENERATOR.generateModel(itemModelGenerator);
        }
    }

}
