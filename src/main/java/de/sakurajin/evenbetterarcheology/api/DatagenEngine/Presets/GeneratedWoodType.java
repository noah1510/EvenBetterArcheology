package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Blocks.*;
import net.devtech.arrp.json.recipe.JIngredient;
import net.devtech.arrp.json.recipe.JIngredients;
import net.devtech.arrp.json.recipe.JRecipe;
import net.devtech.arrp.json.recipe.JResult;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeRegistry;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;

public class GeneratedWoodType {
    @FunctionalInterface
    public interface SettingsOverride{
        FabricBlockSettings override(FabricBlockSettings settings);
    }

    private final String name;

    private final String baseWoodType;

    private final String textureFolder;
    public final WoodType woodType;
    public final BlockSetType blockSetType;
    public final ArrayList<SettingsOverride> settingsOverrides = new ArrayList<>();


    //generate blocks to prevent generation of the same block multiple times
    private CubeColumn log = null;
    private CubeAll planks = null;
    private Slab slabs = null;
    private Stairs stairs = null;
    private Fence fence = null;
    private FenceGate fenceGate = null;
    private Door door = null;
    private Trapdoor trapdoor = null;

    public static WoodType registerWoodType(String id, DatagenModContainer container) {
        return WoodTypeRegistry.register(container.getSimpleID(id), new BlockSetType(id));
    }

    public static BlockSetType registerBlockSetType(String id, DatagenModContainer container) {
        return BlockSetTypeRegistry.registerWood(container.getSimpleID(id));
    }

    public GeneratedWoodType(String name, DatagenModContainer container){
        this(name, container, "minecraft:oak");
    }

    public GeneratedWoodType(String name, DatagenModContainer container, String baseWoodType){
        this(name, container, baseWoodType, null);
    }

    public GeneratedWoodType(String name, DatagenModContainer container, String baseWoodType, SettingsOverride[] settingsOverrides){
        this.name = name;
        this.woodType = registerWoodType(name, container);
        this.blockSetType = registerBlockSetType(name, container);
        this.textureFolder = container.getStringID(name, "block");
        this.baseWoodType = baseWoodType;
        this.settingsOverrides.addAll(Arrays.asList(settingsOverrides));
    }

    private String getTextureName(String suffix){
        return textureFolder + "/" + suffix;
    }

    private FabricBlockSettings getSettingsOf(String suffix, FabricBlockSettings settings){
        if(settings == null){
            settings = FabricBlockSettings.copyOf(Registries.BLOCK.get(new Identifier(baseWoodType+"_"+suffix)));
        }
        for (SettingsOverride override : settingsOverrides) {
            settings = override.override(settings);
        }
        return settings;
    }

    public CubeColumn getLog(FabricBlockSettings settings){
        if(log == null){
            settings = getSettingsOf("log", settings);
            log = new CubeColumn(settings, getTextureName("log_top"), getTextureName("log"));
        }
        return log;
    }

    public CubeAll getPlanks(FabricBlockSettings settings){
        if (planks == null) {
            settings = getSettingsOf("planks", settings);
            planks = new CubeAll(settings, getTextureName("planks")) {
                @Override
                public void generateRecepie(DatagenModContainer container, String identifier) {
                    container.RESOURCE_PACK.addRecipe(
                            container.getSimpleID(identifier),
                            JRecipe.shapeless(
                                    JIngredients.ingredients().add(JIngredient.ingredient().item(container.getStringID(name + "_log"))),
                                    JResult.item(this.asItem())
                            )
                    );
                }
            };
        }
        return planks;
    }

    public Slab getSlabs(FabricBlockSettings settings){
        if (slabs == null){
            settings = getSettingsOf("slab", settings);
            slabs = new Slab(settings, name+"_planks", false, new String[]{getTextureName("planks")});
        }
        return slabs;
    }

    public Stairs getStairs(FabricBlockSettings settings){
        if (stairs == null){
            if(planks == null){
                throw new IllegalStateException("Stairs can only be generated after planks have been generated");
            }
            settings = getSettingsOf("stairs", settings);
            stairs = new Stairs(settings, planks, new String[]{getTextureName("planks")}, false);
        }
        return stairs;
    }

    public Fence getFence(FabricBlockSettings settings){
        if (fence == null){
            if(planks == null){
                throw new IllegalStateException("Fence can only be generated after planks have been generated");
            }
            settings = getSettingsOf("fence", settings);
            fence = new Fence(settings, getTextureName("planks"), name+"_planks");
        }
        return fence;
    }

    public FenceGate getFenceGate(FabricBlockSettings settings){
        if (fenceGate == null){
            if(planks == null){
                throw new IllegalStateException("FenceGate can only be generated after planks have been generated");
            }
            settings = getSettingsOf("fence_gate", settings);
            fenceGate = new FenceGate(settings, this.woodType, getTextureName("planks"), name+"_planks");
        }
        return fenceGate;
    }

    public Door getDoor(FabricBlockSettings settings){
        if (door == null){
            if(planks == null){
                throw new IllegalStateException("Door can only be generated after planks have been generated");
            }
            settings = getSettingsOf("door", settings);
            door = new Door(settings, this.blockSetType, getTextureName("door"), name+"_planks");
        }
        return door;
    }

    public Trapdoor getTrapdoor(FabricBlockSettings settings){
        if (trapdoor == null){
            if(planks == null){
                throw new IllegalStateException("Trapdoor can only be generated after planks have been generated");
            }
            settings = getSettingsOf("trapdoor", settings);
            trapdoor = new Trapdoor(settings, this.blockSetType, getTextureName("trapdoor"), name+"_planks");
        }
        return trapdoor;
    }

}
