package de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Blocks;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Interfaces.BlockGenerateable;
import net.devtech.arrp.json.blockstate.JBlockModel;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.blockstate.JWhen;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JTextures;
import net.devtech.arrp.json.recipe.*;
import net.minecraft.block.FenceBlock;

public class Fence extends FenceBlock implements BlockGenerateable {

    private final String texture;
    private final String plankName;
    public Fence(Settings settings, String texture, String plankName) {
        super(settings);
        this.texture = texture;
        this.plankName = plankName;
    }

    public static void generateBlockModel(DatagenModContainer container, String identifier, String texture){
        JTextures textures = JModel.textures().var("texture", container.getStringID(texture,"block"));

        for (String model: new String[]{"inventory", "post", "side"}){
            container.RESOURCE_PACK.addModel(
                JModel.model("minecraft:block/fence_" + model).textures(textures),
                container.getSimpleID("block/"+identifier+"_"+model)
            );
        }
    }

    public static void eGenerateBlockState(DatagenModContainer container, String identifier){
        JBlockModel sideModel = JState.model(container.getStringID(identifier+"_side", "block")).uvlock();
        container.RESOURCE_PACK.addBlockState(
            JState.state()
                .add(JState.multipart().addModel(JState.model(container.getStringID(identifier+"_post", "block"))))
                .add(JState.multipart().addModel(sideModel).when(new JWhen().add("north", "true")))
                .add(JState.multipart().addModel(sideModel.clone().y(90)).when(new JWhen().add("east", "true")))
                .add(JState.multipart().addModel(sideModel.clone().y(180)).when(new JWhen().add("south", "true")))
                .add(JState.multipart().addModel(sideModel.clone().y(270)).when(new JWhen().add("west", "true"))),
            container.getSimpleID(identifier)
        );
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
        container.generateItemModel(identifier, container.getStringID(identifier + "_inventory", "block"));
    }

    @Override
    public void generateRecepie(DatagenModContainer container, String identifier) {
        container.RESOURCE_PACK.addRecipe(container.getSimpleID(identifier),
                JRecipe.shaped(
                        JPattern.pattern("#W#", "#W#"),
                        JKeys.keys().key("W", JIngredient.ingredient().item(container.getStringID(this.plankName))).key("#", JIngredient.ingredient().item("stick")),
                        JResult.itemStack(this.asItem(),3)
                )
        );
    }
}
