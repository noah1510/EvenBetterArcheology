package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;

public interface BlockStateGenerateable {
    public void generateBlockState(DatagenModContainer container, String identifier);
}
