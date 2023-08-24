package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;

public interface BlockGenerationType {
    public void generate(String name, DatagenModContainer container, String[] textures);
}
