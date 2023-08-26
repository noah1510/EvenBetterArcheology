package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public interface ItemModelGeneratateable {
    public default void generateItemModel(DatagenModContainer container, String identifier){
        if (this instanceof Block){
            container.DATA_GEN_HELPER.generateBlockItemModel(identifier);
        }else if (this instanceof Item){
            container.DATA_GEN_HELPER.generateItemModel(identifier);
        }else{
            throw new RuntimeException("ItemModelGeneratateable is not implemented for this class");
        }
    }
}
