package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Item.BlockGenerateable;
import net.devtech.arrp.json.blockstate.JState;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;

import java.util.Map;

public class Slab implements BlockGenerateable, BlockGenerationType {
    private final String texture_bottom;
    private final String texture_top;
    private final String texture_side;
    private final String texture_double;

    public Slab(String texture_bottom, String texture_top, String texture_side, String texture_double) {
        this (new String[]{texture_bottom, texture_top, texture_side, texture_double});
    }
    public Slab(String texture_bottom, String texture_side) {
        this (new String[]{texture_bottom, texture_side});
    }

    public Slab(String texture_all) {
        this(new String[]{texture_all});
    }

    public Slab(String[] textures){
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
        container.MODEL_GENERATION_HELPER.generateBlockModel(
                identifier,
                textures,
                "minecraft:block/slab"
        );

        container.MODEL_GENERATION_HELPER.generateBlockModel(
                identifier+"_double",
                Map.of(
                        "all", texture_double
                ),
                "minecraft:block/cube_all"
        );

        container.MODEL_GENERATION_HELPER.generateBlockModel(
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
        container.MODEL_GENERATION_HELPER.generateBlockItemModel(identifier);
    }

    @Override
    public void generateRecepie(DatagenModContainer container, String identifier) {

    }

    @Override
    public void generateModel(String name, ItemConvertible block, DatagenModContainer container) {
        this.generateBlockModel(container, name);
    }

    @Override
    public void generateState(String name, ItemConvertible block, DatagenModContainer container) {
        this.generateBlockState(container, name);
    }
}
