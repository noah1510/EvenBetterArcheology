package de.sakurajin.evenbetterarcheology.api.DatagenEngine;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Helpers.GenerationHelper;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class DatagenModContainer{
    public final String MOD_ID;
    public final RuntimeResourcePack RESOURCE_PACK;
    public final GenerationHelper DATA_GEN_HELPER;
    public final Logger LOGGER;

    @Nullable
    public final OwoItemGroup GROUP;

    public DatagenModContainer(String modid){this(modid, null, RRPCallback.AFTER_VANILLA);}

    public DatagenModContainer(String modid, Supplier<io.wispforest.owo.itemgroup.Icon> groupIconSupplier){this(modid, groupIconSupplier, RRPCallback.AFTER_VANILLA);}

    public DatagenModContainer(
            String modid,
            @NotNull
            net.fabricmc.fabric.api.event.Event<RRPCallback> event
    ){
        this(modid, null, event);
    }

    public DatagenModContainer(
            String modid,
            Supplier<io.wispforest.owo.itemgroup.Icon> groupIconSupplier,
            @NotNull
            net.fabricmc.fabric.api.event.Event<RRPCallback> event
    ){
        MOD_ID = modid;
        RESOURCE_PACK = RuntimeResourcePack.create(MOD_ID+":resources");
        DATA_GEN_HELPER = new GenerationHelper(MOD_ID, RESOURCE_PACK);
        LOGGER = LoggerFactory.getLogger(modid);

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

}
