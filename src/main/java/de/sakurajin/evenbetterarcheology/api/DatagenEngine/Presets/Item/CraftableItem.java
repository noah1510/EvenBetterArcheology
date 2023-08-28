package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Item;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.ItemGenerateable;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.RecepieGeneratable;
import net.minecraft.item.Item;

/**
 * @name CraftableItem
 */
public abstract class CraftableItem extends Item implements ItemGenerateable{

    public CraftableItem(Settings settings) {
        super(settings);
    }
}
