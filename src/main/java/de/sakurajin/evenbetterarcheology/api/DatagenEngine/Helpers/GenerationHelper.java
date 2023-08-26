package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Helpers;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.blockstate.JVariant;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JTextures;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;

import java.util.Map;

public class GenerationHelper {

    protected final String modID;
    protected final RuntimeResourcePack RESOURCE_PACK;

    public GenerationHelper(String modID, RuntimeResourcePack RESOURCE_PACK){
        this.modID = modID;
        this.RESOURCE_PACK = RESOURCE_PACK;
    }

    public String getStringID(String name, String type){
        if(name.contains(":")){
            return name;
        }

        return modID+":"+type+"/"+name;
    }

    public Identifier getSimpleID(String name){
        return new Identifier(modID, name);
    }

    public void generateBlockItemModel(String name){
        String parent = getStringID(name, "block");
        generateItemModel(name, parent, null);
    }

    public void generateBlockItemModel(String name, String texture){
        String parent = getStringID(name, "block");
        generateItemModel(name, parent, getStringID(texture, "block"));
    }

    public void generateItemModel(String name){
        generateItemModel(name, "minecraft:item/generated", getStringID(name, "item"));
    }

    public void generateItemModel(String name, String parent){
        generateItemModel(name, parent, null);
    }

    public void generateItemModel(String name, String parent, String texture){
        Identifier ItemID = getSimpleID("item/"+name);

        JTextures textures = null;
        if(texture != null){
            textures = new JTextures().var("layer0", getStringID(texture, "item"));
        }

        RESOURCE_PACK.addModel(new JModel().parent(parent).textures(textures), ItemID);
    }

    public void generateBlockModel(String name, Map<String, String> textures, String parent){
        Identifier BlockID = getSimpleID("block/"+name);
        JTextures texture = new JTextures();
        for (Map.Entry<String, String> entry : textures.entrySet()) {
            texture.var(entry.getKey(), getStringID(entry.getValue(), "block"));
        }

        RESOURCE_PACK.addModel((new JModel().parent(parent).textures(texture)), BlockID);
    }

    public void generateBlockState(String name){
        RESOURCE_PACK.addBlockState(JState.state(JState.variant()
                .put("", JState.model(getStringID(name, "block")))
        ), getSimpleID(name));
    }

    public void generateBlockStateOrientable(String name){
        generateBlockStateOrientable(name, new String[]{name});
    }
    public void generateBlockStateOrientable(String name, String[] alternatives){
        JVariant variants = JState.variant();

        for(String alternative : alternatives){
            Identifier model = getSimpleID("block/" + alternative);
            variants.put("facing=east", JState.model(model).y(90));
            variants.put("facing=west", JState.model(model).y(270));
            variants.put("facing=south", JState.model(model).y(180));
            variants.put("facing=north", JState.model(model));
        }

        RESOURCE_PACK.addBlockState(JState.state(variants), getSimpleID(name));
    }

    public ItemConvertible generateBlockItem(Block block, OwoItemSettings settings){
        return new BlockItem(block, settings);
    }

}
