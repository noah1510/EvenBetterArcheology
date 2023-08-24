package de.sakurajin.evenbetterarcheology.api.AnnotationEngine;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.BlockGenerationType;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Helpers.GenerationHelper;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.minecraft.block.*;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DatagenModContainer{
    public final String MOD_ID;
    public final RuntimeResourcePack RESOURCE_PACK;
    public final GenerationHelper MODEL_GENERATION_HELPER;
    public final Logger LOGGER;

    @Nullable
    public final OwoItemGroup GROUP;

    private HashMap<Class<? extends Block>, Class<? extends BlockGenerationType>> blockGenerationDetectionMap;

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
        MODEL_GENERATION_HELPER = new GenerationHelper(MOD_ID, RESOURCE_PACK);
        LOGGER = LoggerFactory.getLogger(modid);

        if(groupIconSupplier == null){
            GROUP = null;
        }else{
            GROUP = OwoItemGroup.builder(new Identifier(MOD_ID, MOD_ID), groupIconSupplier).build();
        }

        event.register(a -> a.add(RESOURCE_PACK));

        blockGenerationDetectionMap = new HashMap<>();
        blockGenerationDetectionMap.putAll(Map.of(
                StairsBlock.class, de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.Stairs.class,
                SlabBlock.class, de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.Slab.class,
                PillarBlock.class, de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.CubeColumn.class,
                BrushableBlock.class, de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.SusBlock.class
        ));
    }

    public <T> void registerContainer(
            @NotNull Class<? extends io.wispforest.owo.registration.reflect.AutoRegistryContainer<T>> clazz,
            boolean recurseIntoInnerClasses
    ){
        FieldRegistrationHandler.register(clazz, MOD_ID, recurseIntoInnerClasses);
    }

    public Class<? extends BlockGenerationType> autoDetectBlockGenerationType(Class<? extends Block> blockClass){
        return blockGenerationDetectionMap.get(blockClass);
    }

    public void registerBlockGenerationType(Class<? extends Block> blockClass, Class<? extends BlockGenerationType> generationType){
        blockGenerationDetectionMap.merge(
            blockClass,
            generationType,
            (oldValue, newValue) -> {
                LOGGER.warn("Overwriting block generation type for block class {} from {} to {}", blockClass, oldValue, newValue);
                return newValue;
            }
        );
    }

}
