package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;

public class Slab implements BlockGenerationType {
    @Override
    public void generate(String name, DatagenModContainer container, String[] textures) {
        switch (textures.length) {
            case 1 -> container.RESOURCE_GENERATION_HELPER.generateSlab(name, textures[0]);
            case 2 -> container.RESOURCE_GENERATION_HELPER.generateSlab(name, textures[0], textures[0], textures[1], textures[1]);
            case 4 -> container.RESOURCE_GENERATION_HELPER.generateSlab(name, textures[0], textures[1], textures[2], textures[3]);
            default -> throw new IllegalArgumentException("Stairs need 1, 2 or 4 textures");
        }
    }
}
