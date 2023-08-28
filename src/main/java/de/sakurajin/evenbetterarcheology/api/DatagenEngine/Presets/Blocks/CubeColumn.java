package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Blocks;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockItemGenerateable;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockStateGenerateable;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockModelGenerateable;
import net.devtech.arrp.json.blockstate.JState;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;

import java.util.Map;

public class CubeColumn extends PillarBlock implements BlockModelGenerateable, BlockStateGenerateable, BlockItemGenerateable {
    private final String texture_end;
    private final String texture_side;

    public CubeColumn(FabricBlockSettings settings, String texture_end, String texture_side) {
        super(settings);
        this.texture_end = texture_end;
        this.texture_side = texture_side;
    }

    @Override
    public void generateBlockModel(DatagenModContainer container, String identifier) {
        Map<String, String> textures = Map.of(
                "end", texture_end,
                "side", texture_side
        );
        container.generateBlockModel(
                identifier,
                textures,
                "minecraft:block/cube_column"
        );

        container.generateBlockModel(
                identifier+"_horizontal",
                textures,
                "minecraft:block/cube_column_horizontal"
        );
    }

    @Override
    public void generateBlockState(DatagenModContainer container, String identifier) {
        String modelBasePath = container.MOD_ID + ":block/";
        container.RESOURCE_PACK.addBlockState(JState.state(JState.variant()
                .put("axis=x", JState.model(modelBasePath+identifier+"_horizontal").x(90).y(90))
                .put("axis=y", JState.model(modelBasePath+identifier))
                .put("axis=z", JState.model(modelBasePath+identifier+"_horizontal").x(90))
        ), new Identifier(container.MOD_ID, identifier));
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
