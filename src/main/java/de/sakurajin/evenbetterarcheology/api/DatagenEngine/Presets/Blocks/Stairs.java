package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Blocks;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockGenerateable;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.recipe.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.Identifier;

import java.util.Map;

public class Stairs extends StairsBlock implements BlockGenerateable {
    private final String texture_bottom;
    private final String texture_top;
    private final String texture_side;
    private final Block baseBlock;
    private final boolean stonecuttable;

    public Stairs(FabricBlockSettings settings, Block BaseBlock, String[] textures){
        this(settings, BaseBlock, textures, true);
    }

    public Stairs(FabricBlockSettings settings, Block BaseBlock, String[] textures, boolean stonecuttable){
        super(BaseBlock.getDefaultState(), settings);
        this.baseBlock = BaseBlock;
        this.stonecuttable = stonecuttable;
        if (textures.length == 1) {
            this.texture_bottom = textures[0];
            this.texture_top = textures[0];
            this.texture_side = textures[0];
        }else if (textures.length == 3){
            this.texture_bottom = textures[0];
            this.texture_top = textures[1];
            this.texture_side = textures[2];
        }else{
            throw new IllegalArgumentException("Stairs need 1 or 3 textures");
        }
    }

    @Override
    public void generateBlockModel(DatagenModContainer container, String identifier) {
        Map<String, String> textures = Map.of(
                "bottom", texture_bottom,
                "top", texture_top,
                "side", texture_side
        );
        container.generateBlockModel(
                identifier,
                textures,
                "minecraft:block/stairs"
        );

        container.generateBlockModel(
                identifier+"_inner",
                textures,
                "minecraft:block/inner_stairs"
        );

        container.generateBlockModel(
                identifier+"_outer",
                textures,
                "minecraft:block/outer_stairs"
        );
    }

    @Override
    public void generateBlockState(DatagenModContainer container, String identifier) {
        String modelBaseID = container.MOD_ID + ":block/" + identifier;
        String modelInnerID = container.MOD_ID + ":block/" + identifier + "_inner";
        String modelOuterID = container.MOD_ID + ":block/" + identifier + "_outer";

        container.RESOURCE_PACK.addBlockState(JState.state(JState.variant()
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
        ), new Identifier(container.MOD_ID, identifier));
    }

    @Override
    public void generateItemModel(DatagenModContainer container, String identifier) {
        container.generateBlockItemModel(identifier);
    }

    @Override
    public void generateRecepie(DatagenModContainer container, String identifier) {
        Identifier blockItemID = container.getSimpleID(identifier);

        container.RESOURCE_PACK.addRecipe(
                new Identifier(container.MOD_ID, identifier+"_from_blocks"),
                JRecipe.shaped(
                        JPattern.pattern("#  ","## ","###"),
                        JKeys.keys().key("#", JIngredient.ingredient().item(baseBlock.asItem())),
                        JResult.itemStack(this.asItem(), 6)
                ));

        if(stonecuttable){
            container.RESOURCE_PACK.addRecipe(
                    new Identifier(container.MOD_ID, identifier+"_cut_from_block"),
                    JRecipe.stonecutting(
                            JIngredient.ingredient().item(baseBlock.asItem()),
                            JResult.itemStack(this.asItem(), 1)
                    ));
        }
    }

}
