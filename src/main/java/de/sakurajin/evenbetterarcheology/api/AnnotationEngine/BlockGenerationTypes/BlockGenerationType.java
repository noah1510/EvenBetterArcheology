package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;
import net.minecraft.item.ItemConvertible;

public interface BlockGenerationType {

    public default void generateAll(String name, ItemConvertible block, DatagenModContainer container) {
        generateModel(name, block, container);
        generateState(name, block, container);
    }

    public void generateModel(String name, ItemConvertible block, DatagenModContainer container);
    public void generateState(String name, ItemConvertible block, DatagenModContainer container);
}
