package de.sakurajin.evenbetterarcheology.block.custom;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockGenerateable;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Blocks.CubeAll;
import net.minecraft.block.Block;
import net.minecraft.block.InfestedBlock;

public class InfestedMudBricks extends InfestedBlock implements BlockGenerateable {
    public InfestedMudBricks(Block regularBlock, Settings settings) {
        super(regularBlock, settings);
    }

    @Override
    public void generateBlockModel(DatagenModContainer container, String identifier) {
        CubeAll.eGenerateBlockModel(container, identifier, "minecraft:block/mud_bricks");
    }

    @Override
    public void generateBlockState(DatagenModContainer container, String identifier) {
        container.generateBlockState(identifier);
    }

    @Override
    public void generateTags(DatagenModContainer container, String identifier) {
        container.addTag("minecraft:blocks/mineable/pickaxe", identifier);
    }
}
