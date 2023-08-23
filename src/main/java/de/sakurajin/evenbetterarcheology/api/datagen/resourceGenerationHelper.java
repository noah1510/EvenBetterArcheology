package de.sakurajin.evenbetterarcheology.api.datagen;

import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JTextures;
import net.minecraft.util.Identifier;

import java.util.Map;

public class resourceGenerationHelper {
    private final String modID;
    private final RuntimeResourcePack RESOURCE_PACK;

    public resourceGenerationHelper(String modID, RuntimeResourcePack RESOURCE_PACK){
        this.modID = modID;
        this.RESOURCE_PACK = RESOURCE_PACK;
    }

    private void generateBlock_Impl(String basePath, String name, Map<String, String> textures, String parent){
        Identifier BlockID = new Identifier(modID, "block/"+name);
        JTextures texture = new JTextures();
        for (Map.Entry<String, String> entry : textures.entrySet()) {
            texture.var(entry.getKey(), basePath+"/"+entry.getValue());
        }

        RESOURCE_PACK.addModel((new JModel().parent(parent).textures(texture)), BlockID);
    }

    public void generateItem(String name, String parent){
        Identifier ItemID = new Identifier(modID, "item/"+name);
        RESOURCE_PACK.addModel(new JModel().parent(parent), ItemID);
    }

    /**
     * @apiNote This does not generate block models
     */
    public void generateBlockAndItem(String name, Map<String, String> textures, String parent){
        String basePath = modID + ":block";
        generateBlock_Impl(basePath, name, textures, parent);
        generateItem(name, modID+":block/"+name);
    }

    /**
     * @apiNote This does not generate block models
     */
    public void generateBlock(String name, Map<String, String> textures, String parent){
        String basePath = modID + ":block";
        generateBlock_Impl(basePath, name, textures, parent);
    }

    public void generateStairs(String name, String texture_all){
        generateStairs(name, texture_all, texture_all, texture_all);
    }
    public void generateStairs(String name, String texture_bottom, String texture_top, String texture_side){
        Map<String, String> textures = Map.of(
                "bottom", texture_bottom,
                "top", texture_top,
                "side", texture_side
        );
        generateBlockAndItem(
                name,
                textures,
                "minecraft:block/stairs"
        );

        generateBlock(
                name+"_inner",
                textures,
                "minecraft:block/inner_stairs"
        );

        generateBlock(
                name+"_outer",
                textures,
                "minecraft:block/outer_stairs"
        );

        String modelBaseID = modID + ":block/" + name;
        String modelInnerID = modID + ":block/" + name + "_inner";
        String modelOuterID = modID + ":block/" + name + "_outer";

        RESOURCE_PACK.addBlockState(JState.state(JState.variant()
            .put("facing=east,half=bottom,shape=inner_left", JState.model(modelInnerID).uvlock().y(270))
            .put("facing=east,half=bottom,shape=inner_right", JState.model(modelInnerID))
            .put("facing=east,half=bottom,shape=outer_left", JState.model(modelOuterID).uvlock().y(270))
            .put("facing=east,half=bottom,shape=outer_right", JState.model(modelOuterID))
            .put("facing=east,half=bottom,shape=straight", JState.model(modelBaseID))
            .put("facing=east,half=top,shape=inner_left", JState.model(modelInnerID).uvlock().x(180))
            .put("facing=east,half=top,shape=inner_right", JState.model(modelInnerID).uvlock().x(180).y(90))
            .put("facing=east,half=top,shape=outer_left", JState.model(modelOuterID).uvlock().x(180))
            .put("facing=east,half=top,shape=outer_right", JState.model(modelOuterID).uvlock().x(180).y(90))
            .put("facing=east,half=top,shape=straight", JState.model(modelBaseID).uvlock().x(180))
            .put("facing=north,half=bottom,shape=inner_left", JState.model(modelInnerID).uvlock().y(180))
            .put("facing=north,half=bottom,shape=inner_right", JState.model(modelInnerID).uvlock().y(270))
            .put("facing=north,half=bottom,shape=outer_left", JState.model(modelOuterID).uvlock().y(180))
            .put("facing=north,half=bottom,shape=outer_right", JState.model(modelOuterID).uvlock().y(270))
            .put("facing=north,half=bottom,shape=straight", JState.model(modelBaseID).uvlock().y(270))
            .put("facing=north,half=top,shape=inner_left", JState.model(modelInnerID).uvlock().x(180).y(180))
            .put("facing=north,half=top,shape=inner_right", JState.model(modelInnerID).uvlock().x(180))
            .put("facing=north,half=top,shape=outer_left", JState.model(modelOuterID).uvlock().x(180).y(270))
            .put("facing=north,half=top,shape=outer_right", JState.model(modelOuterID).uvlock().x(180))
            .put("facing=north,half=top,shape=straight", JState.model(modelBaseID).uvlock().x(180).y(270))
            .put("facing=south,half=bottom,shape=inner_left", JState.model(modelInnerID))
            .put("facing=south,half=bottom,shape=inner_right", JState.model(modelInnerID).uvlock().y(90))
            .put("facing=south,half=bottom,shape=outer_left", JState.model(modelOuterID))
            .put("facing=south,half=bottom,shape=outer_right", JState.model(modelOuterID).uvlock().y(90))
            .put("facing=south,half=bottom,shape=straight", JState.model(modelBaseID).uvlock().y(90))
            .put("facing=south,half=top,shape=inner_left", JState.model(modelInnerID).uvlock().x(180).y(90))
            .put("facing=south,half=top,shape=inner_right", JState.model(modelInnerID).uvlock().x(180).y(180))
            .put("facing=south,half=top,shape=outer_left", JState.model(modelOuterID).uvlock().x(180).y(90))
            .put("facing=south,half=top,shape=outer_right", JState.model(modelOuterID).uvlock().x(180).y(180))
            .put("facing=south,half=top,shape=straight", JState.model(modelBaseID).uvlock().x(180).y(90))
            .put("facing=west,half=bottom,shape=inner_left", JState.model(modelInnerID).uvlock().y(90))
            .put("facing=west,half=bottom,shape=inner_right", JState.model(modelInnerID).uvlock().y(180))
            .put("facing=west,half=bottom,shape=outer_left", JState.model(modelOuterID).uvlock().y(90))
            .put("facing=west,half=bottom,shape=outer_right", JState.model(modelOuterID).uvlock().y(180))
            .put("facing=west,half=bottom,shape=straight", JState.model(modelBaseID).uvlock().y(180))
            .put("facing=west,half=top,shape=inner_left", JState.model(modelInnerID).uvlock().x(180).y(180))
            .put("facing=west,half=top,shape=inner_right", JState.model(modelInnerID).uvlock().x(180).y(270))
            .put("facing=west,half=top,shape=outer_left", JState.model(modelOuterID).uvlock().x(180).y(180))
            .put("facing=west,half=top,shape=outer_right", JState.model(modelOuterID).uvlock().x(180).y(270))
            .put("facing=west,half=top,shape=straight", JState.model(modelBaseID).uvlock().x(180).y(180))
        ), new Identifier(modID, name));
    }

    public void generateCubeColumn(String name, String texture_end, String texture_side){
        Map<String, String> textures = Map.of(
                "end", texture_end,
                "side", texture_side
        );
        generateBlockAndItem(
                name,
                textures,
                "minecraft:block/cube_column"
        );

        generateBlock(
                name+"_horizontal",
                textures,
                "minecraft:block/cube_column_horizontal"
        );

        String modelBasePath = modID + ":block/";
        RESOURCE_PACK.addBlockState(JState.state(JState.variant()
                .put("axis=x", JState.model(modelBasePath+name+"_horizontal").x(90).y(90))
                .put("axis=y", JState.model(modelBasePath+name))
                .put("axis=z", JState.model(modelBasePath+name+"_horizontal").x(90))
        ), new Identifier(modID, name));

    }

    public void generateCube(String name, String texture){
        generateBlockAndItem(
                name,
                Map.of(
                        "all", texture
                ),
                "minecraft:block/cube_all"
        );

        RESOURCE_PACK.addBlockState(JState.state(JState.variant()
                .put("", JState.model(modID+":block/"+name))
        ), new Identifier(modID, name));
    }

    public void generateSlab(String name, String texture_all){
        generateSlab(name, texture_all, texture_all, texture_all, texture_all);
    }

    public void generateSlab(String name, String texture_top, String texture_bottom, String texture_side, String texture_double){
        Map<String, String> textures = Map.of(
                "top", texture_top,
                "bottom", texture_bottom,
                "side", texture_side
        );
        generateBlockAndItem(
                name,
                textures,
                "minecraft:block/slab"
        );

        generateBlock(
            name+"_double",
            Map.of(
                "all", texture_double
            ),
            "minecraft:block/cube_all"
        );

        generateBlock(
                name+"_top",
                textures,
                "minecraft:block/slab_top"
        );

        String modelBasePath = modID + ":block/";
        RESOURCE_PACK.addBlockState(JState.state(JState.variant()
                .put("type=bottom", JState.model(modelBasePath+name))
                .put("type=double", JState.model(modelBasePath+name+"_double"))
                .put("type=top", JState.model(modelBasePath+name+"_top"))
        ), new Identifier(modID, name));
    }

    public void generateBlockAndItemFromParent(String name, String parent){
        generateBlockAndItem(
                name,
                Map.of(),
                parent
        );

        RESOURCE_PACK.addBlockState(JState.state(JState.variant()
                .put("", JState.model(parent))
        ), new Identifier(modID, name));
    }

}
