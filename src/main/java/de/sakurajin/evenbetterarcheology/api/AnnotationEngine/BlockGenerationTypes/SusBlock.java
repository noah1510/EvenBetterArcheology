package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Item.BlockGenerateable;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.blockstate.JVariant;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;

public class SusBlock implements BlockGenerateable, BlockGenerationType {
    private String[] textures;
    public SusBlock() {textures = null;}

    public SusBlock(String[] textures) {
        this.textures = textures;
    }

    @Override
    public void generateModel(String name, ItemConvertible block, DatagenModContainer container) {
        generateBlockModel(container, name);
    }

    @Override
    public void generateState(String name, ItemConvertible block, DatagenModContainer container) {
        generateBlockState(container, name);
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
            new CubeAll(textures[i]).generateBlockModel(container, identifier + "_" + i);
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
        container.MODEL_GENERATION_HELPER.generateItemModel(identifier, container.MOD_ID+":block/"+identifier+"_0");
    }

    @Override
    public void generateRecepie(DatagenModContainer container, String identifier) {

    }
}
