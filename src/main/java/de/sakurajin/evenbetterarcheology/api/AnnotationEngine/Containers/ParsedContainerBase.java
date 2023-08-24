package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Containers;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Parsers.AnnotationParser;
import net.minecraft.item.ItemConvertible;

import java.lang.reflect.Field;
import java.util.ArrayList;

public abstract class ParsedContainerBase {
    protected final DatagenModContainer initializer;
    private final ArrayList<AnnotationParser> parsers = new ArrayList<>();
    protected ParsedContainerBase(DatagenModContainer initializer) {
        super();
        this.initializer = initializer;
    }

    protected void addParser(AnnotationParser parser){
        parsers.add(parser);
    }

    public void parseFields(String namespace, ItemConvertible value, String identifier, Field field) {
        //call all registered parsers
        for (AnnotationParser parser : parsers) {
            parser.parse(namespace, value, identifier, field, initializer);
        }

    }

}
