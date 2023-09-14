package de.sakurajin.evenbetterarcheology.block.custom;

import de.sakurajin.sakuralib.util.DatagenModContainer;
import de.sakurajin.sakuralib.Interfaces.DataGenerateable;
import de.sakurajin.sakuralib.Presets.Blocks.CubeAll;
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
