package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Item.BlockGenerateable;
import net.devtech.arrp.json.blockstate.JState;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;

import java.util.Map;

public class CubeColumn implements BlockGenerateable, BlockGenerationType {
    private final String texture_end;
    private final String texture_side;

    public CubeColumn(String texture_end, String texture_side) {
        this.texture_end = texture_end;
        this.texture_side = texture_side;
    }

    public CubeColumn(String[] textures) {
        if (textures.length != 2) throw new IllegalArgumentException("textures must have a length of 2");
        this.texture_end = textures[0];
        this.texture_side = textures[1];
    }

    @Override
    public void generateModel(String name, ItemConvertible block, DatagenModContainer container) {
        generateBlockModel(container, name);
    }

    @Override
    public void generateState(String name, ItemConvertible block, DatagenModContainer container) {
        generateBlockState(container, name);
    }

    @Override
    public void generateBlockModel(DatagenModContainer container, String identifier) {
        Map<String, String> textures = Map.of(
                "end", texture_end,
                "side", texture_side
        );
        container.MODEL_GENERATION_HELPER.generateBlockModel(
                identifier,
                textures,
                "minecraft:block/cube_column"
        );

        container.MODEL_GENERATION_HELPER.generateBlockModel(
                identifier+"_horizontal",
                textures,
                "minecraft:block/cube_column_horizontal"
        );
    }

    @Override
    public void generateBlockState(DatagenModContainer container, String identifier) {
        String modelBasePath = container.MOD_ID + ":block/";
        container.RESOURCE_PACK.addBlockState(JState.state(JState.variant()
                .put("axis=x", JState.model(modelBasePath+identifier+"_horizontal").x(90).y(90))
                .put("axis=y", JState.model(modelBasePath+identifier))
                .put("axis=z", JState.model(modelBasePath+identifier+"_horizontal").x(90))
        ), new Identifier(container.MOD_ID, identifier));
    }

    @Override
    public void generateItemModel(DatagenModContainer container, String identifier) {

    }

    @Override
    public void generateRecepie(DatagenModContainer container, String identifier) {

    }
}
