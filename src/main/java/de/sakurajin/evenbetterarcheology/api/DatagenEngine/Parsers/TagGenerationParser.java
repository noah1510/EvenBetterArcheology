package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Parsers;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.TagGenerateable;
import net.minecraft.item.ItemConvertible;

import java.lang.reflect.Field;

public class TagGenerationParser implements DataGenerationParser {
    @Override
    public void parse(String namespace, ItemConvertible value, String identifier, Field field, DatagenModContainer container) {
        if(!(value instanceof TagGenerateable)){ return; }
        ((TagGenerateable) value).generateTags(container, identifier);
    }
}
