package de.sakurajin.evenbetterarcheology.api.AnnotationEngine;

import de.sakurajin.evenbetterarcheology.item.ModItems;
import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class DatagenModContainer{
    public final String MOD_ID;
    public final RuntimeResourcePack RESOURCE_PACK;
    public final resourceGenerationHelper RESOURCE_GENERATION_HELPER;
    public final Logger LOGGER;

    @Nullable
    public final OwoItemGroup GROUP;

    public DatagenModContainer(String modid){
        MOD_ID = modid;
        RESOURCE_PACK = RuntimeResourcePack.create(MOD_ID+":resources");
        RESOURCE_GENERATION_HELPER = new resourceGenerationHelper(MOD_ID, RESOURCE_PACK);
        LOGGER = LoggerFactory.getLogger(modid);
        GROUP = null;
    }

    public DatagenModContainer(String modid, Supplier<io.wispforest.owo.itemgroup.Icon> groupIconSupplier){
        MOD_ID = modid;
        RESOURCE_PACK = RuntimeResourcePack.create(MOD_ID+":resources");
        RESOURCE_GENERATION_HELPER = new resourceGenerationHelper(MOD_ID, RESOURCE_PACK);
        LOGGER = LoggerFactory.getLogger(modid);
        GROUP = OwoItemGroup.builder(new Identifier(MOD_ID, MOD_ID), groupIconSupplier).build();
    }

    public <T> void registerContainer(
            @NotNull Class<? extends io.wispforest.owo.registration.reflect.AutoRegistryContainer<T>> clazz,
            boolean recurseIntoInnerClasses
    ){
        FieldRegistrationHandler.register(clazz, MOD_ID, recurseIntoInnerClasses);
    }

}
