package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Item.BlockModelGenerateable;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Item.BlockStateGenerateable;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;

public class AutoDetectBlock implements BlockGenerationType{
    private final String[] textures;
    private BlockGenerationType findType(ItemConvertible block, DatagenModContainer container){
        if(!(block instanceof Block)){return null;}

        BlockGenerationType type = null;
        try{
            var detectedClass = container.autoDetectBlockGenerationType(((Block)block).getClass());
            if(detectedClass != null && detectedClass != AutoDetectBlock.class) {
                type = detectedClass.getConstructor(String[].class).newInstance((Object) textures);
            }
        }catch (Exception e){
            container.LOGGER.error("Error auto detecting block type ", e);
        }

        return type;
    }

    public AutoDetectBlock(String[] textures){
        this.textures = textures;
    }

    public AutoDetectBlock(){
        this.textures = null;
    }

    @Override
    public void generateModel(String name, ItemConvertible block, DatagenModContainer container) {
        if(block instanceof BlockModelGenerateable){
            ((BlockModelGenerateable) block).generateBlockModel(container, name);
        }else{
            var type = findType(block, container);
            if(type != null) {
                type.generateModel(name, block, container);
            }
        }
    }

    @Override
    public void generateState(String name, ItemConvertible block, DatagenModContainer container) {
        if(block instanceof BlockStateGenerateable){
            ((BlockStateGenerateable) block).generateBlockState(container, name);
        }else{
            var type = findType(block, container);
            if(type != null) {
                type.generateState(name, block, container);
            }
        }
    }
}
