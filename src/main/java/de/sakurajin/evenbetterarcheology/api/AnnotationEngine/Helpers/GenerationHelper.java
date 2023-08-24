package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Helpers;

import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JTextures;
import net.minecraft.util.Identifier;

import java.util.Map;

public class GenerationHelper {

    protected final String modID;
    protected final RuntimeResourcePack RESOURCE_PACK;

    public GenerationHelper(String modID, RuntimeResourcePack RESOURCE_PACK){
        this.modID = modID;
        this.RESOURCE_PACK = RESOURCE_PACK;
    }

    public void generateBlockItemModel(String name){
        String parent = modID+":block/"+name;
        generateItemModel(name, parent, null);
    }

    public void generateItemModel(String name, String parent){
        generateItemModel(name, parent, null);
    }

    public void generateItemModel(String name, String parent, String texture){
        Identifier ItemID = new Identifier(modID, "item/"+name);

        JTextures textures = null;
        if(texture != null){
            textures = new JTextures().var("layer0", modID+":item/"+texture);
        }

        RESOURCE_PACK.addModel(new JModel().parent(parent).textures(textures), ItemID);
    }

    public void generateBlockModel(String name, Map<String, String> textures, String parent){
        Identifier BlockID = new Identifier(modID, "block/"+name);
        String basePath = modID + ":block";
        JTextures texture = new JTextures();
        for (Map.Entry<String, String> entry : textures.entrySet()) {
            String textureLocation = entry.getValue();
            if(!textureLocation.contains(":")){
                textureLocation = basePath+"/"+textureLocation;
            }
            texture.var(entry.getKey(), textureLocation);
        }

        RESOURCE_PACK.addModel((new JModel().parent(parent).textures(texture)), BlockID);
    }

    public void generateBlockState(String name){
        RESOURCE_PACK.addBlockState(JState.state(JState.variant()
                .put("", JState.model(modID+":block/"+name))
        ), new Identifier(modID, name));
    }

    public void generateBlockStateOrientable(String name){
        String model = modID + ":block/" + name;
        RESOURCE_PACK.addBlockState(JState.state(JState.variant()
                .put("facing=east", JState.model(model).y(90))
                .put("facing=west", JState.model(model).y(270))
                .put("facing=south", JState.model(model).y(180))
                .put("facing=north", JState.model(model))
        ), new Identifier(modID, name));
    }


}
