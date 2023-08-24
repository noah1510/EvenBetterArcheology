package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Item.BlockGenerateable;
import net.minecraft.item.ItemConvertible;

import java.util.Map;

public class CubeAll implements BlockGenerateable ,BlockGenerationType {
    private final String texture;

    public CubeAll(String texture) {
        this.texture = texture;
    }

    public CubeAll(String[] textures) {
        if (textures.length != 1) throw new IllegalArgumentException("textures must have a length of 1");
        this.texture = textures[0];
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
        container.MODEL_GENERATION_HELPER.generateBlockModel(
                identifier,
                Map.of(
                        "all", texture
                ),
                "minecraft:block/cube_all"
        );
    }

    @Override
    public void generateBlockState(DatagenModContainer container, String identifier) {
        container.MODEL_GENERATION_HELPER.generateBlockState(identifier);
    }

    @Override
    public void generateItemModel(DatagenModContainer container, String identifier) {
        container.MODEL_GENERATION_HELPER.generateBlockItemModel(identifier);
    }

    @Override
    public void generateRecepie(DatagenModContainer container, String identifier) {

    }
}
