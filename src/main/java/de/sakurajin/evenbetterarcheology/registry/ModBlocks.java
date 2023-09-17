package de.sakurajin.evenbetterarcheology.registry;

import de.sakurajin.sakuralib.datagen.v1.Containers.ParsedBlockRegistryContainer;
import de.sakurajin.sakuralib.datagen.v1.DatagenModContainer;
import de.sakurajin.sakuralib.datagen.v1.Presets.Blocks.*;
import de.sakurajin.sakuralib.datagen.v1.Presets.GeneratedWoodType;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.block.custom.*;
import de.sakurajin.evenbetterarcheology.block.fossils.*;

import net.devtech.arrp.json.recipe.JIngredient;
import net.devtech.arrp.json.recipe.JRecipe;
import net.devtech.arrp.json.recipe.JResult;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

public class ModBlocks extends ParsedBlockRegistryContainer {
    public ModBlocks() {super(EvenBetterArcheology.DATA);}

    //-----------SUS VARIANTS-------------//
    public static final Block SUSPICIOUS_RED_SAND = new SusBlock (Blocks.RED_SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND_COMPLETE);

    public static final Block SUSPICIOUS_DIRT = new SusBlock(Blocks.DIRT, SoundEvents.ITEM_BRUSH_BRUSHING_GRAVEL, SoundEvents.ITEM_BRUSH_BRUSHING_GRAVEL_COMPLETE);

    //---------FOSSILIFEROUS BLOCKS-----------//

    public static final Block FOSSILIFEROUS_DIRT = new SusBlock(Blocks.DIRT, SoundEvents.BLOCK_GRAVEL_HIT, SoundEvents.BLOCK_GRAVEL_BREAK){
        @Override
        public ItemConvertible generateData(DatagenModContainer container, String identifier) {
            container.addTag("minecraft:blocks/mineable/shovel", identifier);
            container.addTag("minecraft:blocks/bamboo_plantable", identifier);
            container.addTag("minecraft:blocks/dirt", identifier);
            container.addTag("minecraft:blocks/sand", identifier);
            container.addTag("minecraft:blocks/enderman_holdable", identifier);
            container.addTag("minecraft:blocks/lush_ground_replaceable", identifier);

            return super.generateData(container, identifier);
        }
    };

    //-------------FOSSILS---------------//
    //Villager
    public static final Block VILLAGER_FOSSIL = new VillagerFossilFull();

    public static final Block VILLAGER_FOSSIL_HEAD = new VillagerFossilHeadBlock();

    public static final Block VILLAGER_FOSSIL_BODY = new VillagerFossilBodyBlock();

    //Ocelot
    public static final Block OCELOT_FOSSIL = new OcelotFossilFull();

    public static final Block OCELOT_FOSSIL_HEAD = new OcelotFossilHeadBlock();

    public static final Block OCELOT_FOSSIL_BODY = new OcelotFossilBodyBlock();

    //Sheep
    public static final Block SHEEP_FOSSIL = new SheepFossilFull();

    public static final Block SHEEP_FOSSIL_HEAD = new SheepFossilHeadBlock();

    public static final Block SHEEP_FOSSIL_BODY = new SheepFossilBodyBlock();

    //Chicken
    public static final Block CHICKEN_FOSSIL = new ChickenFossilFull();

    public static final Block CHICKEN_FOSSIL_HEAD = new ChickenFossilHeadBlock();

    public static final Block CHICKEN_FOSSIL_BODY = new ChickenFossilBodyBlock();

    //Creeper
    public static final Block CREEPER_FOSSIL = new CreeperFossilFull();
    public static final Block CREEPER_FOSSIL_HEAD = new CreeperFossilHeadBlock();

    public static final Block CREEPER_FOSSIL_BODY = new CreeperFossilBodyBlock();


    //-----------ROTTEN WOOD-------------//
    public static final GeneratedWoodType ROTTEN_WOOD_TYPE = GeneratedWoodType.GeneratedWoodTypeBuilder
        .create("rotten_wood")
        .baseWoodTypeString("minecraft:oak")
        .addSettingsOverride(settings -> settings.sounds(BlockSoundGroup.NETHER_STEM))
        .build(EvenBetterArcheology.DATA);

    public static final Block ROTTEN_WOOD_LOG = ROTTEN_WOOD_TYPE.getLog(new GeneratedWoodType.GenerationSettings(
            new DatagenModContainer.BlockLootOptions(true, "minecraft:stick", 8)));

    public static final Block ROTTEN_WOOD_PLANKS = ROTTEN_WOOD_TYPE.getPlanks(new GeneratedWoodType.GenerationSettings(
            new DatagenModContainer.BlockLootOptions(true, "minecraft:stick", 2)));

    public static final Block ROTTEN_WOOD_SLAB = ROTTEN_WOOD_TYPE.getSlabs(new GeneratedWoodType.GenerationSettings(
            new DatagenModContainer.BlockLootOptions(true, "minecraft:stick", 1)));

    public static final Block ROTTEN_WOOD_STAIRS = ROTTEN_WOOD_TYPE.getStairs(new GeneratedWoodType.GenerationSettings(
            new DatagenModContainer.BlockLootOptions(true, "minecraft:stick", 3)));

    public static final Block ROTTEN_WOOD_FENCE = ROTTEN_WOOD_TYPE.getFence(new GeneratedWoodType.GenerationSettings(
            new DatagenModContainer.BlockLootOptions(true, "minecraft:stick", 1)));

    public static final Block ROTTEN_WOOD_FENCE_GATE = ROTTEN_WOOD_TYPE.getFenceGate(new GeneratedWoodType.GenerationSettings(
            new DatagenModContainer.BlockLootOptions(true, "minecraft:stick", 1)));

    public static final Block ROTTEN_WOOD_TRAPDOOR = ROTTEN_WOOD_TYPE.getTrapdoor(new GeneratedWoodType.GenerationSettings(
            new DatagenModContainer.BlockLootOptions(true, "minecraft:stick", 4)));

    public static final Block ROTTEN_WOOD_DOOR = ROTTEN_WOOD_TYPE.getDoor(new GeneratedWoodType.GenerationSettings(
            new DatagenModContainer.BlockLootOptions(true, "minecraft:stick", 4)));

    //-------------MUD Brick Stuff----------------//
    public static final Block INFESTED_MUD_BRICKS = new InfestedMudBricks(Blocks.MUD_BRICKS, FabricBlockSettings.copy(Blocks.INFESTED_STONE_BRICKS));

    public static final Block CRACKED_MUD_BRICKS = new CubeAll(Blocks.MUD_BRICKS, "cracked_mud_bricks") {
        @Override
        public ItemConvertible generateData(DatagenModContainer container, String identifier) {
            container.addTag("minecraft:blocks/mineable/pickaxe", identifier);
            container.RESOURCE_PACK.addRecipe(container.getSimpleID("cracked_mud_bricks_from_smelt"),
                    JRecipe.smelting(JIngredient.ingredient().item("mud_bricks"), JResult.result(container.getStringID(identifier))).cookingTime(200).experience(0.1f)
            );
            return super.generateData(container, identifier);
        }
    };

    public static final Block CRACKED_MUD_BRICK_SLAB = new Slab(FabricBlockSettings.copyOf(Blocks.MUD_BRICK_SLAB), "cracked_mud_bricks", new String[]{"cracked_mud_bricks"}){
        @Override
        public ItemConvertible generateData(DatagenModContainer container, String identifier) {
            container.addTag("minecraft:blocks/mineable/pickaxe", identifier);
            return super.generateData(container, identifier);
        }
    };

    public static final Block CRACKED_MUD_BRICK_STAIRS = new Stairs(FabricBlockSettings.copyOf(Blocks.MUD_BRICK_STAIRS), CRACKED_MUD_BRICKS, "cracked_mud_bricks", new String[]{"cracked_mud_bricks"}){
        @Override
        public ItemConvertible generateData(DatagenModContainer container, String identifier) {
            container.addTag("minecraft:blocks/mineable/pickaxe", identifier);
            return super.generateData(container, identifier);
        }
    };

    public static final Block ARCHEOLOGY_TABLE = new ArchelogyTable(FabricBlockSettings.copy(Blocks.CRAFTING_TABLE));

    public static final Block VASE = new VaseBlock(
            new String[]{"0", "1", "2"},
            false
    );

    public static final Block VASE_CREEPER = new VaseBlock(
            new String[]{"3", "4", "5"},
            false
    );

    public static final Block LOOT_VASE = new VaseBlock(
            new String[]{"0", "1", "2"},
            true
    );

    public static final Block LOOT_VASE_CREEPER = new VaseBlock(
            new String[]{"3", "4", "5"},
            true
    );

    public static final Block EVOKER_TRAP = new EvokerTrapBlock(FabricBlockSettings.copy(Blocks.STONE).strength(25f));

}