package de.sakurajin.evenbetterarcheology.api.datagen;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.function.Consumer;

public class BetterBrushItemGenerator {
    private final Item brushItem;
    private final Item material;
    static final Model GENERATED_BRUSH_MODEL = new Model(Optional.of(new Identifier("minecraft", "item/brush")), Optional.empty(), TextureKey.LAYER0);

    public BetterBrushItemGenerator(Item brushItem, Item material) {
        this.brushItem = brushItem;
        this.material = material;
    }

    public void generateCraftingRecepie(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, brushItem, 1)
                .pattern("x").pattern("y").pattern("z")
                .input('x', material).input('y', Items.STICK).input('z', Items.FEATHER)
                .criterion(FabricRecipeProvider.hasItem(material),
                        FabricRecipeProvider.conditionsFromItem(material))
                .criterion(FabricRecipeProvider.hasItem(Items.FEATHER),
                        FabricRecipeProvider.conditionsFromItem(Items.FEATHER))
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter);
    }

    public void generateModel(ItemModelGenerator itemModelGenerator){
        itemModelGenerator.register(brushItem, GENERATED_BRUSH_MODEL);
    }
}
