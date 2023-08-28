package de.sakurajin.evenbetterarcheology.block.custom;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Blocks.CubeAll;
import de.sakurajin.evenbetterarcheology.block.entity.SusBlockEntity;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.blockstate.JVariant;
import net.devtech.arrp.json.models.JModel;
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
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockGenerateable;

public class SusBlock extends BrushableBlock implements BlockGenerateable{

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
    public ItemConvertible generateBlockItem(DatagenModContainer container, String identifier) {
        return container.generateBlockItem(this, container.settings());
    }

    private void initTextures(String identifier){
        if(textures == null || textures.length == 0){
            textures = new String[4];
            for (int i = 0; i < 4; i++) {
                textures[i] = identifier+"/"+identifier + "_" + i;
            }
        }
    }

    @Override
    public void generateBlockModel(DatagenModContainer container, String identifier) {
        initTextures(identifier);

        for (int i = 0; i < textures.length; i++) {
            CubeAll.eGenerateBlockModel(container, identifier + "_" + i, container.MOD_ID + ":block/"+textures[i]);
        }
    }

    @Override
    public void generateBlockState(DatagenModContainer container, String identifier) {
        initTextures(identifier);

        JVariant variants = new JVariant();
        for (int i = 0; i < textures.length; i++) {
            variants.put("dusted="+i, JState.model(container.MOD_ID + ":block/"+identifier+"_"+i));
        }

        container.RESOURCE_PACK.addBlockState(JState.state(variants), new Identifier(container.MOD_ID, identifier));
    }

    @Override
    public void generateItemModel(DatagenModContainer container, String identifier) {
        container.generateItemModel(identifier, container.MOD_ID+":block/"+identifier+"_0");
    }

    @Override
    public void generateRecepie(DatagenModContainer container, String identifier) {

    }


}
