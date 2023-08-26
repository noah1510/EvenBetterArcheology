package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;

public interface BlockModelGenerateable {
    public void generateBlockModel(DatagenModContainer container, String identifier);
}
