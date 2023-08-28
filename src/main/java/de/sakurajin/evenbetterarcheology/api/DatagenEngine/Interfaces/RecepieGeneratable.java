package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;

public interface RecepieGeneratable {
    public default void generateRecepie(DatagenModContainer container, String identifier){};
}
