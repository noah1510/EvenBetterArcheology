package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;

public class SusBlock implements BlockGenerationType {
    @Override
    public void generate(String name, DatagenModContainer container, String[] textures) {
        if (textures.length != 0) throw new IllegalArgumentException("textures must have a length of 0");
        container.MODEL_GENERATION_HELPER.generateSusBlock(name);
    }
}
