package de.sakurajin.evenbetterarcheology.block;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.api.datagen.generationType;
import de.sakurajin.evenbetterarcheology.api.owo_annotations.GenerateBlock;
import de.sakurajin.evenbetterarcheology.api.owo_annotations.ItemRarity;
import de.sakurajin.evenbetterarcheology.api.owo_annotations.ModdedRarity;
import de.sakurajin.evenbetterarcheology.api.owo_annotations.NoItemGroup;
import de.sakurajin.evenbetterarcheology.block.custom.*;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.lang.reflect.Field;

public class ModBlocks implements BlockRegistryContainer {

    //-----------SUS VARIANTS-------------//
    @GenerateBlock(type = generationType.SUSPICIOUS)
    public static final Block SUSPICIOUS_RED_SAND = new SusBlock(Blocks.RED_SAND, FabricBlockSettings.copy(Blocks.SUSPICIOUS_SAND), SoundEvents.ITEM_BRUSH_BRUSHING_SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND_COMPLETE);

    @GenerateBlock(type = generationType.SUSPICIOUS)
    public static final Block SUSPICIOUS_DIRT = new SusBlock(Blocks.DIRT, FabricBlockSettings.copy(Blocks.SUSPICIOUS_GRAVEL), SoundEvents.ITEM_BRUSH_BRUSHING_GRAVEL, SoundEvents.ITEM_BRUSH_BRUSHING_GRAVEL_COMPLETE);

    //---------FOSSILIFEROUS BLOCKS-----------//
    @GenerateBlock(type = generationType.SUSPICIOUS)
    public static final Block FOSSILIFEROUS_DIRT = new SusBlock(Blocks.DIRT, FabricBlockSettings.copy(Blocks.SUSPICIOUS_GRAVEL), SoundEvents.BLOCK_GRAVEL_HIT, SoundEvents.BLOCK_GRAVEL_BREAK);

    //-------------FOSSILS---------------//
    //Villager
    @ItemRarity(ModdedRarity.UNCOMMON)
    public static final Block VILLAGER_FOSSIL = new VillagerFossilBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK).luminance((state) -> {
        return state.get(VillagerFossilBlock.INVENTORY_LUMINANCE);
    }));

    @ItemRarity(ModdedRarity.UNCOMMON)
    public static final Block VILLAGER_FOSSIL_HEAD = new VillagerFossilHeadBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    @ItemRarity(ModdedRarity.UNCOMMON)
    public static final Block VILLAGER_FOSSIL_BODY = new VillagerFossilBodyBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    //Ocelot
    @ItemRarity(ModdedRarity.UNCOMMON)
    public static final Block OCELOT_FOSSIL = new OcelotFossilBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK));

    @ItemRarity(ModdedRarity.UNCOMMON)
    public static final Block OCELOT_FOSSIL_HEAD = new OcelotFossilHeadBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    @ItemRarity(ModdedRarity.UNCOMMON)
    public static final Block OCELOT_FOSSIL_BODY = new OcelotFossilBodyBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    //Sheep
    @ItemRarity(ModdedRarity.UNCOMMON)
    public static final Block SHEEP_FOSSIL = new SheepFossilBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK));

    @ItemRarity(ModdedRarity.UNCOMMON)
    public static final Block SHEEP_FOSSIL_HEAD = new SheepFossilHeadBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    @ItemRarity(ModdedRarity.UNCOMMON)
    public static final Block SHEEP_FOSSIL_BODY = new SheepFossilBodyBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    //Sheep
    @ItemRarity(ModdedRarity.UNCOMMON)
    public static final Block CHICKEN_FOSSIL = new ChickenFossilBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK));

    @ItemRarity(ModdedRarity.UNCOMMON)
    public static final Block CHICKEN_FOSSIL_HEAD = new ChickenFossilHeadBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    @ItemRarity(ModdedRarity.UNCOMMON)
    public static final Block CHICKEN_FOSSIL_BODY = new ChickenFossilBodyBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    //Creeper
    @ItemRarity(ModdedRarity.UNCOMMON)
    public static final Block CREEPER_FOSSIL = new CreeperFossilBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK));

    @ItemRarity(ModdedRarity.UNCOMMON)
    public static final Block CREEPER_FOSSIL_HEAD = new CreeperFossilHeadBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));

    @ItemRarity(ModdedRarity.UNCOMMON)
    public static final Block CREEPER_FOSSIL_BODY = new CreeperFossilBodyBlock(FabricBlockSettings.copy(Blocks.SKELETON_SKULL).sounds(BlockSoundGroup.BONE));


    //-----------ROTTEN WOOD-------------//
    public static final WoodType ROTTEN_WOOD_TYPE = registerWoodType("rotten_wood");
    public static final BlockSetType ROTTEN_WOOD_BLOCKSET = registerBlockSetType("rotten_wood");

    @GenerateBlock(textures = {"rotten_log/rotten_log_top", "rotten_log/rotten_log"}, type = generationType.PILLAR)
    public static final Block ROTTEN_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).sounds(BlockSoundGroup.NETHER_STEM));

    @GenerateBlock(textures = {"rotten_log/rotten_planks"}, type = generationType.CUBE)
    public static final Block ROTTEN_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.NETHER_STEM));

    @GenerateBlock(textures = {"rotten_log/rotten_planks"}, type = generationType.SLAB)
    public static final Block ROTTEN_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB).sounds(BlockSoundGroup.NETHER_STEM));

    @GenerateBlock(textures = {"rotten_log/rotten_planks"}, type = generationType.STAIRS)
    public static final Block ROTTEN_STAIRS = new StairsBlock(ROTTEN_PLANKS.getDefaultState(), FabricBlockSettings.copy(Blocks.OAK_STAIRS).sounds(BlockSoundGroup.NETHER_STEM));

    public static final Block ROTTEN_FENCE = new FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE).sounds(BlockSoundGroup.NETHER_STEM));

    public static final Block ROTTEN_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE).sounds(BlockSoundGroup.NETHER_STEM), ROTTEN_WOOD_TYPE);

    public static final Block ROTTEN_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copy(Blocks.OAK_TRAPDOOR).sounds(BlockSoundGroup.NETHER_STEM), ROTTEN_WOOD_BLOCKSET);

    public static final Block ROTTEN_DOOR = new DoorBlock(FabricBlockSettings.copy(Blocks.OAK_DOOR).sounds(BlockSoundGroup.NETHER_STEM), ROTTEN_WOOD_BLOCKSET);

    //-------------MUD Brick Stuff----------------//
    @GenerateBlock(textures = {"minecraft:block/mud_bricks"}, type = generationType.FROM_PARENT)
    public static final Block INFESTED_MUD_BRICKS = new InfestedMudBricks(Blocks.MUD_BRICKS, FabricBlockSettings.copy(Blocks.INFESTED_STONE_BRICKS));

    @GenerateBlock(textures = {"cracked_mud_bricks"}, type = generationType.CUBE)
    public static final Block CRACKED_MUD_BRICKS = new Block(FabricBlockSettings.copy(Blocks.MUD_BRICKS));

    @GenerateBlock(textures = {"cracked_mud_bricks"}, type = generationType.SLAB)
    public static final Block CRACKED_MUD_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.MUD_BRICK_SLAB));

    @GenerateBlock(textures = {"cracked_mud_bricks"}, type = generationType.STAIRS)
    public static final Block CRACKED_MUD_BRICK_STAIRS = new StairsBlock(CRACKED_MUD_BRICKS.getDefaultState(), FabricBlockSettings.copy(Blocks.MUD_BRICK_STAIRS));

    public static final Block ARCHEOLOGY_TABLE = new ArchelogyTable(FabricBlockSettings.copy(Blocks.CRAFTING_TABLE));

    public static final Block VASE = new VaseBlock(FabricBlockSettings.copy(Blocks.FLOWER_POT).sounds(BlockSoundGroup.DECORATED_POT));

    public static final Block VASE_CREEPER = new VaseBlock(FabricBlockSettings.copy(Blocks.FLOWER_POT).sounds(BlockSoundGroup.DECORATED_POT));


    @NoItemGroup
    public static final Block LOOT_VASE = new LootVaseBlock(FabricBlockSettings.copy(Blocks.FLOWER_POT).sounds(BlockSoundGroup.DECORATED_POT));

    @NoItemGroup
    public static final Block LOOT_VASE_CREEPER =new LootVaseBlock(FabricBlockSettings.copy(Blocks.FLOWER_POT).sounds(BlockSoundGroup.DECORATED_POT));

    @NoItemGroup
    public static final Block EVOKER_TRAP = new EvokerTrapBlock(FabricBlockSettings.copy(Blocks.STONE).strength(25f));


    @Override
    public BlockItem createBlockItem(Block block, String identifier) {
        return new BlockItem(block, new OwoItemSettings().group(EvenBetterArcheology.GROUP));
    }

    @Override
    public void postProcessField(String namespace, Block value, String identifier, Field field) {
        if (field.isAnnotationPresent(NoBlockItem.class)) return;

        // create basic item settings
        var settings = new OwoItemSettings();

        // check if our annotation is present and only
        // assign the itemgroup if it is not
        if (!field.isAnnotationPresent(NoItemGroup.class)){
            settings.group(EvenBetterArcheology.GROUP);
        }

        if (field.isAnnotationPresent(ItemRarity.class)){
            Rarity rarity = switch (field.getAnnotation(ItemRarity.class).value()) {
                case UNCOMMON -> Rarity.UNCOMMON;
                case RARE -> Rarity.RARE;
                case EPIC -> Rarity.EPIC;
                default -> Rarity.COMMON;
            };
            settings.rarity(rarity);
        }

        if (field.isAnnotationPresent(GenerateBlock.class)){
            GenerateBlock annotation = field.getAnnotation(GenerateBlock.class);
            EvenBetterArcheology.RESOURCE_GENERATION_HELPER.autoGenerateSimple(
                    identifier,
                    annotation.textures(),
                    annotation.type()
            );
        }

        // finally, create and register the block item
        Registry.register(Registries.ITEM, new Identifier(namespace, identifier), new BlockItem(value, settings));
    }

    private static WoodType registerWoodType(String id) {
        return WoodTypeRegistry.register(new Identifier(EvenBetterArcheology.MOD_ID, id), new BlockSetType(id));
    }

    private static BlockSetType registerBlockSetType(String id) {
        return BlockSetTypeRegistry.registerWood(new Identifier(EvenBetterArcheology.MOD_ID, id));
    }

    //LOGGER-----------------------------------------------------------------------------//
    public static void registerModBlocks() {
        EvenBetterArcheology.LOGGER.info("Registering Blocks from " + EvenBetterArcheology.MOD_ID);
    }
}