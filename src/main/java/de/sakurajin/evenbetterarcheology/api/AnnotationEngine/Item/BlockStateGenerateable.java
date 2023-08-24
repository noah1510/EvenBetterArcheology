package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Item;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;

public interface BlockStateGenerateable {
    public void generateBlockState(DatagenModContainer container, String identifier);
}
