package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Blocks;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockGenerateable;
import net.devtech.arrp.json.blockstate.JBlockModel;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.blockstate.JVariant;
import net.devtech.arrp.json.blockstate.JWhen;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JTextures;
import net.devtech.arrp.json.recipe.*;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.WoodType;

public class FenceGate extends FenceGateBlock implements BlockGenerateable {

    private final String texture;
    private final String plankName;
    public FenceGate(Settings settings, WoodType type, String texture, String plankName) {
        super(settings, type);
        this.texture = texture;
        this.plankName = plankName;
    }

    public static void generateBlockModel(DatagenModContainer container, String identifier, String texture){
        JTextures textures = JModel.textures().var("texture", container.getStringID(texture,"block"));

        for (String model: new String[]{"", "_open", "_wall", "_wall_open"}){
            container.RESOURCE_PACK.addModel(
                JModel.model("minecraft:block/template_fence_gate" + model).textures(textures),
                container.getSimpleID("block/"+identifier+model)
            );
        }
    }

    private static String createProperty(String direction, boolean wall, boolean open){
        String property = "facing=" + direction;
        property += ",in_wall=" + wall;
        property += ",open=" + open;
        return property;
    }

    private static JBlockModel createModel(DatagenModContainer container, String identifier, String direction, boolean wall, boolean open){
        int y;
        switch (direction){
            case "east" -> y = 270;
            case "north" -> y = 180;
            case "south" -> y = 0;
            case "west" -> y = 90;
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }

        String model = identifier;
        if(wall) model += "_wall";
        if(open) model += "_open";

        return JState.model(container.getStringID(model,"block")).y(y).uvlock();
    }

    public static void eGenerateBlockState(DatagenModContainer container, String identifier){
        JVariant variants = JState.variant();
        for(String direction: new String[]{"east", "north", "south", "west"}){
            variants.put(createProperty(direction, false, false), createModel(container, identifier, direction, false, false));
            variants.put(createProperty(direction, false, true), createModel(container, identifier, direction, false, true));
            variants.put(createProperty(direction, true, false), createModel(container, identifier, direction, true, false));
            variants.put(createProperty(direction, true, true), createModel(container, identifier, direction, true, true));
        }
        container.RESOURCE_PACK.addBlockState(JState.state(variants), container.getSimpleID(identifier));
    }

    @Override
    public void generateBlockModel(DatagenModContainer container, String identifier) {
        generateBlockModel(container, identifier, this.texture);
    }

    @Override
    public void generateBlockState(DatagenModContainer container, String identifier) {
        eGenerateBlockState(container, identifier);
    }

    @Override
    public void generateItemModel(DatagenModContainer container, String identifier) {
        container.generateItemModel(identifier, container.getStringID(identifier, "block"));
    }

    @Override
    public void generateRecepie(DatagenModContainer container, String identifier) {
        container.RESOURCE_PACK.addRecipe(container.getSimpleID(identifier),
            JRecipe.shaped(
                JPattern.pattern("#W#", "#W#"),
                JKeys.keys().key("#", JIngredient.ingredient().item(container.getStringID(this.plankName))).key("W", JIngredient.ingredient().item("stick")),
                JResult.item(this.asItem())
            )
        );
    }
}
