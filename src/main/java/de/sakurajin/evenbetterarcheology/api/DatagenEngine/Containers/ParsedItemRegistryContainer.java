package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Containers;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Parsers.ItemModelGenerationParser;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Parsers.RecepieGenerationParser;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.minecraft.item.Item;

import java.lang.reflect.Field;


public abstract class ParsedItemRegistryContainer extends ParsedContainerBase implements ItemRegistryContainer {
    protected ParsedItemRegistryContainer(DatagenModContainer initializer) {
        super(initializer);
        addParser(new ItemModelGenerationParser());
        addParser(new RecepieGenerationParser());
    }

    @Override
    public void postProcessField(String namespace, Item value, String identifier, Field field) {
        parseFields(namespace, value, identifier, field);
    }

}
