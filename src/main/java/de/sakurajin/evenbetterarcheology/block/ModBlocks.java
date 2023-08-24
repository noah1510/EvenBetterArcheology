package de.sakurajin.evenbetterarcheology.block;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Annotations.BlockItemOptions.BlockItemTexture;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Annotations.BlockItemOptions.NoItemModel;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Containers.ParsedBlockRegistryContainer;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Annotations.GenerateBlock;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Annotations.BlockItemOptions.BlockItemRarity;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Annotations.ModdedRarity;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Annotations.BlockItemOptions.NoItemGroup;
import de.sakurajin.evenbetterarcheology.block.custom.*;
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
    @BlockItemTexture("evenbetterarcheology:block/suspicious_red_sand_0")
    @GenerateBlock(type = de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.SusBlock.class)
    public static final Block SUSPICIOUS_RED_SAND = new SusBlock(Blocks.RED_SAND, FabricBlockSettings.copy(Blocks.SUSPICIOUS_SAND), SoundEvents.ITEM_BRUSH_BRUSHING_SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND_COMPLETE);

    @BlockItemTexture("evenbetterarcheology:block/suspicious_dirt_0")
    @GenerateBlock(type = de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.SusBlock.class)
    public static final Block SUSPICIOUS_DIRT = new SusBlock(Blocks.DIRT, FabricBlockSettings.copy(Blocks.SUSPICIOUS_GRAVEL), SoundEvents.ITEM_BRUSH_BRUSHING_GRAVEL, SoundEvents.ITEM_BRUSH_BRUSHING_GRAVEL_COMPLETE);

    //---------FOSSILIFEROUS BLOCKS-----------//
    @BlockItemTexture("evenbetterarcheology:block/fossiliferous_dirt_0")
    @GenerateBlock(type = de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.SusBlock.class)
    public static final Block FOSSILIFEROUS_DIRT = new SusBlock(Blocks.DIRT, FabricBlockSettings.copy(Blocks.SUSPICIOUS_GRAVEL), SoundEvents.BLOCK_GRAVEL_HIT, SoundEvents.BLOCK_GRAVEL_BREAK);

    //-------------FOSSILS---------------//
    //Villager
    @BlockItemRarity(ModdedRarity.UNCOMMON)
    @NoItemModel
    public static final Block VILLAGER_FOSSIL = new VillagerFossilBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK).luminance((state) -> {
        return state.get(VillagerFossilBlock.INVENTORY_LUMINANCE);
    }));

    @BlockItemRarity(ModdedRarity.UNCOMMON)
    @NoItemModel
    public static final Block VILLAGER_FOSSIL_HEAD = new VillagerFossilHeadBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    @BlockItemRarity(ModdedRarity.UNCOMMON)
    @NoItemModel
    public static final Block VILLAGER_FOSSIL_BODY = new VillagerFossilBodyBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    //Ocelot
    @BlockItemRarity(ModdedRarity.UNCOMMON)
    @NoItemModel
    public static final Block OCELOT_FOSSIL = new OcelotFossilBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK));

    @BlockItemRarity(ModdedRarity.UNCOMMON)
    @NoItemModel
    public static final Block OCELOT_FOSSIL_HEAD = new OcelotFossilHeadBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    @BlockItemRarity(ModdedRarity.UNCOMMON)
    @NoItemModel
    public static final Block OCELOT_FOSSIL_BODY = new OcelotFossilBodyBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    //Sheep
    @BlockItemRarity(ModdedRarity.UNCOMMON)
    @NoItemModel
    public static final Block SHEEP_FOSSIL = new SheepFossilBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK));

    @BlockItemRarity(ModdedRarity.UNCOMMON)
    @NoItemModel
    public static final Block SHEEP_FOSSIL_HEAD = new SheepFossilHeadBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    @BlockItemRarity(ModdedRarity.UNCOMMON)
    @NoItemModel
    public static final Block SHEEP_FOSSIL_BODY = new SheepFossilBodyBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    //Sheep
    @BlockItemRarity(ModdedRarity.UNCOMMON)
    @NoItemModel
    public static final Block CHICKEN_FOSSIL = new ChickenFossilBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK));

    @BlockItemRarity(ModdedRarity.UNCOMMON)
    @NoItemModel
    public static final Block CHICKEN_FOSSIL_HEAD = new ChickenFossilHeadBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    @BlockItemRarity(ModdedRarity.UNCOMMON)
    @NoItemModel
    public static final Block CHICKEN_FOSSIL_BODY = new ChickenFossilBodyBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    //Creeper
    @BlockItemRarity(ModdedRarity.UNCOMMON)
    @NoItemModel
    public static final Block CREEPER_FOSSIL = new CreeperFossilBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK));

    @BlockItemRarity(ModdedRarity.UNCOMMON)
    @NoItemModel
    public static final Block CREEPER_FOSSIL_HEAD = new CreeperFossilHeadBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    @BlockItemRarity(ModdedRarity.UNCOMMON)
    @NoItemModel
    public static final Block CREEPER_FOSSIL_BODY = new CreeperFossilBodyBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));


    //-----------ROTTEN WOOD-------------//
    public static final WoodType ROTTEN_WOOD_TYPE = registerWoodType("rotten_wood");
    public static final BlockSetType ROTTEN_WOOD_BLOCKSET = registerBlockSetType("rotten_wood");

    @GenerateBlock(textures = {"rotten_log/rotten_log_top", "rotten_log/rotten_log"}, type = de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.CubeColumn.class)
    public static final Block ROTTEN_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).sounds(BlockSoundGroup.NETHER_STEM));

    @GenerateBlock(textures = {"rotten_log/rotten_planks"}, type = de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.CubeAll.class)
    public static final Block ROTTEN_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.NETHER_STEM));

    @GenerateBlock(textures = {"rotten_log/rotten_planks"}, type = de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.Slab.class)
    public static final Block ROTTEN_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB).sounds(BlockSoundGroup.NETHER_STEM));

    @GenerateBlock(textures = {"rotten_log/rotten_planks"}, type = de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.Stairs.class)
    public static final Block ROTTEN_STAIRS = new StairsBlock(ROTTEN_PLANKS.getDefaultState(), FabricBlockSettings.copy(Blocks.OAK_STAIRS).sounds(BlockSoundGroup.NETHER_STEM));

    @NoItemModel
    public static final Block ROTTEN_FENCE = new FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE).sounds(BlockSoundGroup.NETHER_STEM));

    @NoItemModel
    public static final Block ROTTEN_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE).sounds(BlockSoundGroup.NETHER_STEM), ROTTEN_WOOD_TYPE);

    @NoItemModel
    public static final Block ROTTEN_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copy(Blocks.OAK_TRAPDOOR).sounds(BlockSoundGroup.NETHER_STEM), ROTTEN_WOOD_BLOCKSET);

    @NoItemModel
    public static final Block ROTTEN_DOOR = new DoorBlock(FabricBlockSettings.copy(Blocks.OAK_DOOR).sounds(BlockSoundGroup.NETHER_STEM), ROTTEN_WOOD_BLOCKSET);

    //-------------MUD Brick Stuff----------------//
    @GenerateBlock(textures = {"minecraft:block/mud_bricks"}, type = de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.FromParent.class)
    public static final Block INFESTED_MUD_BRICKS = new InfestedMudBricks(Blocks.MUD_BRICKS, FabricBlockSettings.copy(Blocks.INFESTED_STONE_BRICKS));

    @GenerateBlock(textures = {"cracked_mud_bricks"}, type = de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.CubeAll.class)
    public static final Block CRACKED_MUD_BRICKS = new Block(FabricBlockSettings.copy(Blocks.MUD_BRICKS));

    @GenerateBlock(textures = {"cracked_mud_bricks"}, type = de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.Slab.class)
    public static final Block CRACKED_MUD_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.MUD_BRICK_SLAB));

    @GenerateBlock(textures = {"cracked_mud_bricks"}, type = de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.Stairs.class)
    public static final Block CRACKED_MUD_BRICK_STAIRS = new StairsBlock(CRACKED_MUD_BRICKS.getDefaultState(), FabricBlockSettings.copy(Blocks.MUD_BRICK_STAIRS));

    @NoItemModel
    public static final Block ARCHEOLOGY_TABLE = new ArchelogyTable(FabricBlockSettings.copy(Blocks.CRAFTING_TABLE));

    @NoItemModel
    public static final Block VASE = new VaseBlock(FabricBlockSettings.copy(Blocks.FLOWER_POT).sounds(BlockSoundGroup.DECORATED_POT));

    @NoItemModel
    public static final Block VASE_CREEPER = new VaseBlock(FabricBlockSettings.copy(Blocks.FLOWER_POT).sounds(BlockSoundGroup.DECORATED_POT));

    @NoItemModel
    @NoItemGroup
    public static final Block LOOT_VASE = new LootVaseBlock(FabricBlockSettings.copy(Blocks.FLOWER_POT).sounds(BlockSoundGroup.DECORATED_POT));

    @NoItemModel
    @NoItemGroup
    public static final Block LOOT_VASE_CREEPER =new LootVaseBlock(FabricBlockSettings.copy(Blocks.FLOWER_POT).sounds(BlockSoundGroup.DECORATED_POT));

    @NoItemModel
    @NoItemGroup
    public static final Block EVOKER_TRAP = new EvokerTrapBlock(FabricBlockSettings.copy(Blocks.STONE).strength(25f));

    private static WoodType registerWoodType(String id) {
        return WoodTypeRegistry.register(new Identifier(EvenBetterArcheology.DATA.MOD_ID, id), new BlockSetType(id));
    }

    private static BlockSetType registerBlockSetType(String id) {
        return BlockSetTypeRegistry.registerWood(new Identifier(EvenBetterArcheology.DATA.MOD_ID, id));
    }

}