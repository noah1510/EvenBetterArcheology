package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Item;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.ItemGenerateable;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.RecepieGeneratable;
import net.minecraft.item.Item;

public abstract class CraftableItem extends Item implements ItemGenerateable, RecepieGeneratable{

    public CraftableItem(Settings settings) {
        super(settings);
    }
}
