package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;

public interface TagGenerateable {
    public default void generateTags(DatagenModContainer container, String identifier){}
}
