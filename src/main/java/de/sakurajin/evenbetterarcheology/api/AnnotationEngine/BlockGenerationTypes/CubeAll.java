package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;

public class CubeAll implements BlockGenerationType {
    @Override
    public void generate(String name, DatagenModContainer container, String[] textures) {
        if (textures.length != 1) throw new IllegalArgumentException("textures must have a length of 1");
        container.RESOURCE_GENERATION_HELPER.generateCube(name, textures[0]);
    }
}
