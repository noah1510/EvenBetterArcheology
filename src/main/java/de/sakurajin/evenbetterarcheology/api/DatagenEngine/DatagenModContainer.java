package de.sakurajin.evenbetterarcheology.api.DatagenEngine;

import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.blockstate.JVariant;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JTextures;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.function.Supplier;

public class DatagenModContainer{
    public final String MOD_ID;
    public final RuntimeResourcePack RESOURCE_PACK;
    public final Logger LOGGER;

    @Nullable
    public final OwoItemGroup GROUP;

    public DatagenModContainer(String MOD_ID){this(MOD_ID, null, RRPCallback.AFTER_VANILLA);}

    public DatagenModContainer(String MOD_ID, Supplier<io.wispforest.owo.itemgroup.Icon> groupIconSupplier){this(MOD_ID, groupIconSupplier, RRPCallback.AFTER_VANILLA);}

    public DatagenModContainer(
            String MOD_ID,
            @NotNull
            net.fabricmc.fabric.api.event.Event<RRPCallback> event
    ){
        this(MOD_ID, null, event);
    }

    public DatagenModContainer(
            String MOD_ID,
            Supplier<io.wispforest.owo.itemgroup.Icon> groupIconSupplier,
            @NotNull
            net.fabricmc.fabric.api.event.Event<RRPCallback> event
    ){
        this.MOD_ID = MOD_ID;
        RESOURCE_PACK = RuntimeResourcePack.create(MOD_ID+":resources");
        LOGGER = LoggerFactory.getLogger(MOD_ID);

        if(groupIconSupplier == null){
            GROUP = null;
        }else{
            GROUP = OwoItemGroup.builder(new Identifier(MOD_ID, MOD_ID), groupIconSupplier).build();
        }

        event.register(a -> a.add(RESOURCE_PACK));
    }

    public OwoItemSettings settings(){
        return settings(true);
    }

    public OwoItemSettings settings(boolean hasGroup){
        OwoItemSettings settings = new OwoItemSettings();
        if (hasGroup){
            settings.group(GROUP);
        }
        return settings;
    }

    public <T> void registerContainer(
            @NotNull Class<? extends io.wispforest.owo.registration.reflect.AutoRegistryContainer<T>> clazz,
            boolean recurseIntoInnerClasses
    ){
        FieldRegistrationHandler.register(clazz, MOD_ID, recurseIntoInnerClasses);
    }

    public String getStringID(String name){
        return getStringID(name, null);
    }

    public String getStringID(String name, String type){
        if(name.contains(":")){return name;}
        if(type == null){return MOD_ID+":"+name;}
        return MOD_ID+":"+type+"/"+name;
    }

    public Identifier getSimpleID(String name){
        return new Identifier(MOD_ID, name);
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
