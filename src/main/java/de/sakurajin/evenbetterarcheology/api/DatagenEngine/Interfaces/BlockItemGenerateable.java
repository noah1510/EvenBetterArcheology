package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;

public interface BlockItemGenerateable extends ItemModelGenerateable, TagGenerateable {
    public default ItemConvertible generateBlockItem(DatagenModContainer container, String identifier){
        if(this instanceof Block) {
            return container.generateBlockItem((Block)this, container.settings());
        }else{
            throw new RuntimeException("BlockItemGenerateable must be implemented by a Block");
        }
    }
}
