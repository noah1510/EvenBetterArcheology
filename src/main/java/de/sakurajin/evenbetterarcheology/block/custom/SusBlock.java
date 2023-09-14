package de.sakurajin.evenbetterarcheology.block.custom;

import de.sakurajin.sakuralib.util.DatagenModContainer;
import de.sakurajin.sakuralib.Interfaces.DataGenerateable;
import de.sakurajin.sakuralib.Presets.Blocks.CubeAll;
import de.sakurajin.evenbetterarcheology.block.entity.SusBlockEntity;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.blockstate.JVariant;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BrushableBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class SusBlock extends BrushableBlock implements DataGenerateable {

    String[] textures;

    public SusBlock(
            Block baseBlock,
            SoundEvent brushingSound,
            SoundEvent brushingCompleteSound
    ) {
        super(baseBlock, FabricBlockSettings.copyOf(baseBlock), brushingSound, brushingCompleteSound);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SusBlockEntity(pos, state);
    }

    @Override
    public ItemConvertible generateData(DatagenModContainer container, String identifier) {
        container.generateItemModel(identifier, container.MOD_ID+":block/"+identifier+"_0");
        generateBlockModel(container, identifier, this);
        generateBlockState(container, identifier, this);
        container.createBlockLootTable(identifier, null);

        return container.generateBlockItem(this, container.settings());
    }

    private static void initTextures(String identifier, SusBlock block){
        if(block.textures == null || block.textures.length == 0){
            block.textures = new String[4];
            for (int i = 0; i < 4; i++) {
                block.textures[i] = identifier+"/"+identifier + "_" + i;
            }
        }
    }

    public static void generateBlockModel(DatagenModContainer container, String identifier, SusBlock block) {
        initTextures(identifier, block);

        for (int i = 0; i < block.textures.length; i++) {
            CubeAll.generateBlockModel(container, identifier + "_" + i, container.MOD_ID + ":block/"+block.textures[i]);
        }
    }

    public static void generateBlockState(DatagenModContainer container, String identifier, SusBlock block) {
        initTextures(identifier, block);

        JVariant variants = new JVariant();
        for (int i = 0; i < block.textures.length; i++) {
            variants.put("dusted="+i, JState.model(container.MOD_ID + ":block/"+identifier+"_"+i));
        }

        container.RESOURCE_PACK.addBlockState(JState.state(variants), new Identifier(container.MOD_ID, identifier));
    }
}
