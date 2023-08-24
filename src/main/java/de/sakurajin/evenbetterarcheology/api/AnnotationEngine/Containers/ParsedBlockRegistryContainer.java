package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Containers;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Parsers.BlockGenerationParser;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Parsers.BlockItemGenerator;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.minecraft.block.Block;

import java.lang.reflect.Field;

public abstract class ParsedBlockRegistryContainer extends ParsedContainerBase implements BlockRegistryContainer {

    protected ParsedBlockRegistryContainer(DatagenModContainer initializer) {
        super(initializer);
        addParser(new BlockItemGenerator());
        addParser(new BlockGenerationParser());
    }

    @Override
    public void postProcessField(String namespace, Block value, String identifier, Field field) {
        parseFields(namespace, value, identifier, field);
    }
}
