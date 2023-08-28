package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public interface ItemModelGeneratateable {
    public default void generateItemModel(DatagenModContainer container, String identifier){
        if (this instanceof Block){
            container.generateBlockItemModel(identifier);
        }else if (this instanceof Item){
            container.generateItemModel(identifier);
        }else{
            throw new RuntimeException("ItemModelGeneratateable is not implemented for this class");
        }
    }
}
