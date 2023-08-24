package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Item.BlockGenerateable;
import net.minecraft.item.ItemConvertible;

import java.util.Map;

public class FromParent implements BlockGenerateable, BlockGenerationType {
    private final String parentBlock;

    public FromParent(String parentBlock) {
        this.parentBlock = parentBlock;
    }

    public FromParent(String[] parentBlock) {
        if (parentBlock.length != 1) {
            throw new IllegalArgumentException("FromParent only takes one argument");
        }
        this.parentBlock = parentBlock[0];
    }

    @Override
    public void generateModel(String name, ItemConvertible block, DatagenModContainer container) {
        generateBlockModel(container, name);
    }

    @Override
    public void generateState(String name, ItemConvertible block, DatagenModContainer container) {
        generateBlockState(container, name);
    }

    @Override
    public void generateBlockModel(DatagenModContainer container, String identifier) {
        container.MODEL_GENERATION_HELPER.generateBlockModel(
                identifier,
                Map.of(),
                parentBlock
        );
    }

    @Override
    public void generateBlockState(DatagenModContainer container, String identifier) {
        container.MODEL_GENERATION_HELPER.generateBlockState(identifier);
    }

    @Override
    public void generateItemModel(DatagenModContainer container, String identifier) {
        container.MODEL_GENERATION_HELPER.generateBlockItemModel(identifier);
    }

    @Override
    public void generateRecepie(DatagenModContainer container, String identifier) {

    }
}
