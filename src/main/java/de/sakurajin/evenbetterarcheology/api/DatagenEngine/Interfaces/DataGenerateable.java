package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;

public interface DataGenerateable {
    default ItemConvertible generateData(DatagenModContainer container, String identifier){
        if(this instanceof Block){
            return container.generateBlockItem((Block)this, container.settings());
        }
        if(this instanceof ItemConvertible){
            return (ItemConvertible)this;
        }
        return null;
    }
}
