package de.sakurajin.evenbetterarcheology.block.custom;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.DataGenerateable;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Blocks.CubeAll;
import net.minecraft.block.Block;
import net.minecraft.block.InfestedBlock;
import net.minecraft.item.ItemConvertible;

public class InfestedMudBricks extends InfestedBlock implements DataGenerateable {
    public InfestedMudBricks(Block regularBlock, Settings settings) {
        super(regularBlock, settings);
    }

    @Override
    public ItemConvertible generateData(DatagenModContainer container, String identifier) {
        container.generateBlockState(identifier);
        CubeAll.generateBlockModel(container, identifier, "minecraft:block/mud_bricks");
        container.addTag("minecraft:blocks/mineable/pickaxe", identifier);
        container.createBlockLootTable(identifier, null);

        container.generateBlockItemModel(identifier);
        return container.generateBlockItem(this, container.settings());
    }
}
