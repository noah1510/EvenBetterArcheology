package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import net.devtech.arrp.json.loot.JCondition;
import net.devtech.arrp.json.loot.JEntry;

public interface LootTableGenerateable {
    public void generateLootTable(DatagenModContainer container, String identifier);
    public default JEntry addExtraConditions(DatagenModContainer container, String identifier, JEntry entry){
        return entry;
    }
}
