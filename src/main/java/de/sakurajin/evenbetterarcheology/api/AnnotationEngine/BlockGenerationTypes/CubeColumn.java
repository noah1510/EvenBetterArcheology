package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;

public class CubeColumn implements BlockGenerationType {
    @Override
    public void generate(String name, DatagenModContainer container, String[] textures) {
        if (textures.length != 2) throw new IllegalArgumentException("textures must have a length of 2");
        container.MODEL_GENERATION_HELPER.generateCubeColumn(name, textures[0], textures[1]);
    }
}
