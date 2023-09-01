package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Item;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.DataGenerateable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;

/**
 * @name CraftableItem
 */
public abstract class CraftableItem extends Item implements DataGenerateable {

    public CraftableItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemConvertible generateData(DatagenModContainer container, String identifier) {
        container.generateItemModel(identifier);
        return this;
    }
}
