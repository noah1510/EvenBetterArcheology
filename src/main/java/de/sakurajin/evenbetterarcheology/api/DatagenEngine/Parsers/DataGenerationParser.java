package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Parsers;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.DataGenerateable;
import net.minecraft.item.ItemConvertible;

import java.lang.reflect.Field;

public class DataGenerationParser implements IDataGenerationParser {
    @Override
    public void parse(String namespace, ItemConvertible value, String identifier, Field field, DatagenModContainer container) {
        if(!(value instanceof DataGenerateable)){ return; }
        ((DataGenerateable) value).generateData(container, identifier);
    }
}
