package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Blocks;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockGenerateable;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.recipe.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;

import java.util.Map;

public class Slab extends SlabBlock implements BlockGenerateable {
    private final String texture_bottom;
    private final String texture_top;
    private final String texture_side;
    private final String texture_double;
    private final String baseBlock;
    private final boolean stonecuttable;

    public Slab(FabricBlockSettings settings, String baseBlock, String[] textures){
        this(settings, baseBlock, true, textures);
    }

    public Slab(FabricBlockSettings settings, String baseBlock, boolean stonecuttable, String[] textures){
        super(settings);
        this.baseBlock = baseBlock;
        this.stonecuttable = stonecuttable;

        if (textures.length == 1) {
            this.texture_bottom = textures[0];
            this.texture_top = textures[0];
            this.texture_side = textures[0];
            this.texture_double = textures[0];
        }else if (textures.length == 2) {
            this.texture_bottom = textures[0];
            this.texture_top = textures[0];
            this.texture_side = textures[1];
            this.texture_double = textures[1];
        }else if (textures.length == 4){
            this.texture_bottom = textures[0];
            this.texture_top = textures[1];
            this.texture_side = textures[2];
            this.texture_double = textures[3];
        }else{
            throw new IllegalArgumentException("Slabs need 1, 2 or 4 textures");
        }
    }

    @Override
    public void generateBlockModel(DatagenModContainer container, String identifier) {
        Map<String, String> textures = Map.of(
                "top", texture_top,
                "bottom", texture_bottom,
                "side", texture_side
        );
        container.generateBlockModel(
                identifier,
                textures,
                "minecraft:block/slab"
        );

        container.generateBlockModel(
                identifier+"_double",
                Map.of(
                        "all", texture_double
                ),
                "minecraft:block/cube_all"
        );

        container.generateBlockModel(
                identifier+"_top",
                textures,
                "minecraft:block/slab_top"
        );
    }

    @Override
    public void generateBlockState(DatagenModContainer container, String identifier) {
        String modelBasePath = container.MOD_ID + ":block/";
        container.RESOURCE_PACK.addBlockState(JState.state(JState.variant()
                .put("type=bottom", JState.model(modelBasePath+identifier))
                .put("type=double", JState.model(modelBasePath+identifier+"_double"))
                .put("type=top", JState.model(modelBasePath+identifier+"_top"))
        ), new Identifier(container.MOD_ID, identifier));
    }

    @Override
    public void generateItemModel(DatagenModContainer container, String identifier) {
        container.generateBlockItemModel(identifier);
    }

    @Override
    public void generateRecepie(DatagenModContainer container, String identifier) {
        if(baseBlock == null) return;
        String baseBlockID = container.getStringID(baseBlock);

        Identifier blockItemID = container.getSimpleID(identifier);

        container.RESOURCE_PACK.addRecipe(
            new Identifier(container.MOD_ID, identifier+"_from_blocks"),
            JRecipe.shaped(
                JPattern.pattern("###"),
                JKeys.keys().key("#", JIngredient.ingredient().item(baseBlockID)),
                JResult.itemStack(this.asItem(), 6)
        ));

        container.RESOURCE_PACK.addRecipe(
            new Identifier(container.MOD_ID, identifier+"_from_slab"),
            JRecipe.shaped(
                JPattern.pattern("#", "#"),
                JKeys.keys().key("#", JIngredient.ingredient().item(blockItemID.toString())),
                JResult.result(baseBlockID)
        ));

        if(stonecuttable){
            container.RESOURCE_PACK.addRecipe(
                new Identifier(container.MOD_ID, identifier+"_cut"),
                JRecipe.stonecutting(
                    JIngredient.ingredient().item(baseBlockID),
                    JResult.itemStack(this.asItem(), 2)
            ));
        }
    }

    @Override
    public ItemConvertible generateBlockItem(DatagenModContainer container, String identifier) {
        return container.generateBlockItem(this, container.settings());
    }

    @Override
    public void generateTags(DatagenModContainer container, String identifier) {
        container.addTag("minecraft:blocks/slabs", identifier);
        container.addTag("minecraft:items/slabs", identifier);
    }
}
