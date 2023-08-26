package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Containers;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Parsers.*;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.minecraft.block.Block;

import java.lang.reflect.Field;

public abstract class ParsedBlockRegistryContainer extends ParsedContainerBase implements BlockRegistryContainer {

    protected ParsedBlockRegistryContainer(DatagenModContainer initializer) {
        super(initializer);
        addParser(new BlockModelGenerationParser());
        addParser(new BlockStateGenerationParser());
        addParser(new BlockItemGenerationParser());
        addParser(new ItemModelGenerationParser());
        addParser(new RecepieGenerationParser());
    }

    @Override
    public void postProcessField(String namespace, Block value, String identifier, Field field) {
        parseFields(namespace, value, identifier, field);
    }
}
