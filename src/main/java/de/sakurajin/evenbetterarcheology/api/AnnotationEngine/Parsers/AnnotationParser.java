package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Parsers;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;
import net.minecraft.item.ItemConvertible;

import java.lang.reflect.Field;

public interface AnnotationParser {
    void parse(String namespace, ItemConvertible value, String identifier, Field field, DatagenModContainer container);
}
