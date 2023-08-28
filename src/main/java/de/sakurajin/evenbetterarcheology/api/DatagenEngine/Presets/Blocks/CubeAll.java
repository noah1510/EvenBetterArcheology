package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Blocks;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockGenerateable;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;

import java.util.Map;

public class CubeAll extends Block implements BlockGenerateable {
    private final String texture;

    public CubeAll(FabricBlockSettings settings, String texture) {
        super(settings);
        this.texture = texture;
    }

    public CubeAll(Block parentBlock, String texture) {
        this(FabricBlockSettings.copyOf(parentBlock), texture);
    }

    public static void eGenerateBlockModel(DatagenModContainer container, String identifier, String texture) {
        container.generateBlockModel(
            identifier,
            Map.of(
                    "all", texture
            ),
            "minecraft:block/cube_all"
        );
    }

    @Override
    public void generateBlockModel(DatagenModContainer container, String identifier) {
        eGenerateBlockModel(container, identifier, texture);
    }

    @Override
    public void generateBlockState(DatagenModContainer container, String identifier) {
        container.generateBlockState(identifier);
    }

    @Override
    public void generateItemModel(DatagenModContainer container, String identifier) {
        container.generateBlockItemModel(identifier);
    }

    @Override
    public ItemConvertible generateBlockItem(DatagenModContainer container, String identifier) {
        return container.generateBlockItem(this, container.settings());
    }
}
