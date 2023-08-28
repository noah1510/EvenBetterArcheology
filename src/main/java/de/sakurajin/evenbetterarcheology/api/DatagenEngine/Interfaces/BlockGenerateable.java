package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import net.devtech.arrp.json.loot.JEntry;

public interface BlockGenerateable extends RecepieGeneratable, BlockModelGenerateable, BlockStateGenerateable, BlockItemGenerateable, LootTableGenerateable {
    default void generateLootTable(DatagenModContainer container, String identifier) {
        DatagenModContainer.BlockLootOptions options = new DatagenModContainer.BlockLootOptions();
        options.conditionAdder = (JEntry entry) -> addExtraConditions(container, identifier, entry);

        container.createBlockLootTable(identifier, options);
    }
}
