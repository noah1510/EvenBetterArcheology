package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Parsers;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.LootTableGenerateable;
import net.minecraft.item.ItemConvertible;

import java.lang.reflect.Field;

public class LootTableParser implements DataGenerationParser{

    @Override
    public void parse(String namespace, ItemConvertible value, String identifier, Field field, DatagenModContainer container) {
        if(!(value instanceof LootTableGenerateable)){return;}
        ((LootTableGenerateable) value).generateLootTable(container, identifier);
    }
}
