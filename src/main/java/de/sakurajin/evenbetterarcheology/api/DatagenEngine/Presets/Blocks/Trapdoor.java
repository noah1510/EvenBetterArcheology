package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Blocks;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockGenerateable;
import net.devtech.arrp.json.blockstate.JBlockModel;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.blockstate.JVariant;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JTextures;
import net.devtech.arrp.json.recipe.*;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.TrapdoorBlock;

public class Trapdoor extends TrapdoorBlock implements BlockGenerateable {
    private final String plankName;
    private final String textureBaseName;
    public Trapdoor(Settings settings, BlockSetType blockSetType, String textureBaseName, String plankName) {
        super(settings, blockSetType);
        this.plankName = plankName;
        this.textureBaseName = textureBaseName;
    }

    public static void eGenerateBlockModel(DatagenModContainer container, String identifier, String textureBaseName){
        JTextures textures = JModel.textures().var("texture", container.getStringID(textureBaseName, "block"));
        String[] parts = new String[]{"bottom", "open", "top"};

        for (String part : parts) {
            container.RESOURCE_PACK.addModel(
                JModel.model().parent("minecraft:block/template_trapdoor_" + part).textures(textures),
                container.getSimpleID("block/"+identifier + "_" + part)
            );
        }
    }

    private static JBlockModel getModelName(String identifier, String direction, boolean isTop, boolean open){
        if(!open){return JState.model(identifier + (isTop ? "_top" : "_bottom"));}

        int angle;
        switch (direction){
            case("east") -> angle = 90;
            case("south") -> angle = 180;
            case("west") -> angle = 270;
            case("north") -> angle = 0;
            default -> throw new IllegalArgumentException("Invalid direction: " + direction);
        }

        return JState.model(identifier + "_open").y(angle);
    }

    private static String getPropertyString(String direction, boolean isUpper, boolean open){
        return "facing=" + direction + ",half=" + (isUpper ? "top" : "bottom") + ",open=" + open;
    }

    public static void eGenerateBlockState(DatagenModContainer container, String identifier){
        String[] directions = new String[]{"north", "south", "east", "west"};
        JVariant variant = JState.variant();
        String modelBaseId = container.getStringID(identifier, "block");
        for(String direction : directions){
            variant.put(getPropertyString(direction, false, false), getModelName(modelBaseId, direction, false, false));
            variant.put(getPropertyString(direction, false, true), getModelName(modelBaseId, direction, false, true));
            variant.put(getPropertyString(direction, true, false), getModelName(modelBaseId, direction, true, false));
            variant.put(getPropertyString(direction, true, true), getModelName(modelBaseId, direction, true, true));
        }
        container.RESOURCE_PACK.addBlockState(JState.state(variant), container.getSimpleID(identifier));
    }

    @Override
    public void generateBlockModel(DatagenModContainer container, String identifier) {
        eGenerateBlockModel(container, identifier, this.textureBaseName);
    }

    @Override
    public void generateBlockState(DatagenModContainer container, String identifier) {
        eGenerateBlockState(container, identifier);
    }

    @Override
    public void generateItemModel(DatagenModContainer container, String identifier) {
        container.generateItemModel(identifier, container.getStringID(identifier+"_bottom", "block"));
    }

    @Override
    public void generateRecepie(DatagenModContainer container, String identifier) {
        container.RESOURCE_PACK.addRecipe(container.getSimpleID(identifier),
            JRecipe.shaped(
                JPattern.pattern("###", "###"),
                JKeys.keys().key("#", JIngredient.ingredient().item(container.getStringID(this.plankName))),
                JResult.itemStack(this.asItem(), 2)
            )
        );
    }
}
