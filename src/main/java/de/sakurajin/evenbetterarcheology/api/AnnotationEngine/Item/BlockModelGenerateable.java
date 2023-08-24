package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Item;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;

public interface BlockModelGenerateable {
    public void generateBlockModel(DatagenModContainer container, String identifier);
}
