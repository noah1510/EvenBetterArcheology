package de.sakurajin.evenbetterarcheology.block;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Annotations.ItemOptions.NoItemModel;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Containers.ParsedBlockRegistryContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Blocks.CubeAll;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Blocks.CubeColumn;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Blocks.Slab;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.Presets.Blocks.Stairs;
import de.sakurajin.evenbetterarcheology.block.custom.*;
import de.sakurajin.evenbetterarcheology.block.fossils.*;
import net.devtech.arrp.json.recipe.JIngredient;
import net.devtech.arrp.json.recipe.JRecipe;
import net.devtech.arrp.json.recipe.JResult;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeRegistry;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ModBlocks extends ParsedBlockRegistryContainer {
    public ModBlocks() {super(EvenBetterArcheology.DATA);}

    //-----------SUS VARIANTS-------------//
    public static final Block SUSPICIOUS_RED_SAND = new SusBlock (Blocks.RED_SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND_COMPLETE);

    public static final Block SUSPICIOUS_DIRT = new SusBlock(Blocks.DIRT, SoundEvents.ITEM_BRUSH_BRUSHING_GRAVEL, SoundEvents.ITEM_BRUSH_BRUSHING_GRAVEL_COMPLETE);

    //---------FOSSILIFEROUS BLOCKS-----------//

    public static final Block FOSSILIFEROUS_DIRT = new SusBlock(Blocks.DIRT, SoundEvents.BLOCK_GRAVEL_HIT, SoundEvents.BLOCK_GRAVEL_BREAK);

    //-------------FOSSILS---------------//
    //Villager
    public static final Block VILLAGER_FOSSIL = new VillagerFossilBlock();

    public static final Block VILLAGER_FOSSIL_HEAD = new VillagerFossilHeadBlock();

    public static final Block VILLAGER_FOSSIL_BODY = new VillagerFossilBodyBlock();

    //Ocelot
    public static final Block OCELOT_FOSSIL = new OcelotFossilBlock();

    public static final Block OCELOT_FOSSIL_HEAD = new OcelotFossilHeadBlock();

    public static final Block OCELOT_FOSSIL_BODY = new OcelotFossilBodyBlock();

    //Sheep
    public static final Block SHEEP_FOSSIL = new SheepFossilBlock();

    public static final Block SHEEP_FOSSIL_HEAD = new SheepFossilHeadBlock();

    public static final Block SHEEP_FOSSIL_BODY = new SheepFossilBodyBlock();

    //Chicken
    public static final Block CHICKEN_FOSSIL = new ChickenFossilBlock();

    public static final Block CHICKEN_FOSSIL_HEAD = new ChickenFossilHeadBlock();

    public static final Block CHICKEN_FOSSIL_BODY = new ChickenFossilBodyBlock();

    //Creeper
    public static final Block CREEPER_FOSSIL = new CreeperFossilBlock();
    public static final Block CREEPER_FOSSIL_HEAD = new CreeperFossilHeadBlock();

    public static final Block CREEPER_FOSSIL_BODY = new CreeperFossilBodyBlock();


    //-----------ROTTEN WOOD-------------//
    public static final WoodType ROTTEN_WOOD_TYPE = registerWoodType("rotten_wood");
    public static final BlockSetType ROTTEN_WOOD_BLOCKSET = registerBlockSetType("rotten_wood");

    public static final Block ROTTEN_LOG = new CubeColumn(FabricBlockSettings.copyOf(Blocks.OAK_LOG).sounds(BlockSoundGroup.NETHER_STEM), "rotten_log/rotten_log_top", "rotten_log/rotten_log");

    public static final Block ROTTEN_PLANKS = new CubeAll(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.NETHER_STEM), "rotten_log/rotten_planks");

    public static final Block ROTTEN_SLAB = new Slab(
        FabricBlockSettings.copyOf(Blocks.OAK_SLAB).sounds(BlockSoundGroup.NETHER_STEM),
        new Identifier(EvenBetterArcheology.DATA.MOD_ID, "cracked_mud_bricks"),
        false,
        new String[]{"cracked_mud_bricks"}
    );

    public static final Block ROTTEN_STAIRS = new Stairs(ROTTEN_PLANKS, FabricBlockSettings.copyOf(Blocks.OAK_STAIRS).sounds(BlockSoundGroup.NETHER_STEM), new String[]{"rotten_log/rotten_planks"}, false);

    @NoItemModel
    public static final Block ROTTEN_FENCE = new FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE).sounds(BlockSoundGroup.NETHER_STEM));

    @NoItemModel
    public static final Block ROTTEN_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE).sounds(BlockSoundGroup.NETHER_STEM), ROTTEN_WOOD_TYPE);

    @NoItemModel
    public static final Block ROTTEN_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copy(Blocks.OAK_TRAPDOOR).sounds(BlockSoundGroup.NETHER_STEM), ROTTEN_WOOD_BLOCKSET);

    @NoItemModel
    public static final Block ROTTEN_DOOR = new DoorBlock(FabricBlockSettings.copy(Blocks.OAK_DOOR).sounds(BlockSoundGroup.NETHER_STEM), ROTTEN_WOOD_BLOCKSET);

    //-------------MUD Brick Stuff----------------//
    public static final Block INFESTED_MUD_BRICKS = new InfestedMudBricks(Blocks.MUD_BRICKS, FabricBlockSettings.copy(Blocks.INFESTED_STONE_BRICKS));

    public static final Block CRACKED_MUD_BRICKS = new CubeAll(Blocks.MUD_BRICKS, "cracked_mud_bricks") {
        @Override
        public void generateRecepie(DatagenModContainer container, String identifier) {
            super.generateRecepie(container, identifier);
            container.RESOURCE_PACK.addRecipe(container.DATA_GEN_HELPER.getSimpleID("cracked_mud_bricks_from_smelt"),
                JRecipe.smelting(JIngredient.ingredient().item("mud_bricks"), JResult.item(this.asItem())).cookingTime(200).experience(0.1f)
            );
        }
    };

    public static final Block CRACKED_MUD_BRICK_SLAB = new Slab(
        FabricBlockSettings.copyOf(Blocks.MUD_BRICK_SLAB),
        new Identifier(EvenBetterArcheology.DATA.MOD_ID, "cracked_mud_bricks"),
        new String[]{"cracked_mud_bricks"}
    );

    public static final Block CRACKED_MUD_BRICK_STAIRS = new Stairs(CRACKED_MUD_BRICKS, FabricBlockSettings.copyOf(Blocks.MUD_BRICK_STAIRS), new String[]{"cracked_mud_bricks"});

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

    private static WoodType registerWoodType(String id) {
        return WoodTypeRegistry.register(new Identifier(EvenBetterArcheology.DATA.MOD_ID, id), new BlockSetType(id));
    }

    private static BlockSetType registerBlockSetType(String id) {
        return BlockSetTypeRegistry.registerWood(new Identifier(EvenBetterArcheology.DATA.MOD_ID, id));
    }

}