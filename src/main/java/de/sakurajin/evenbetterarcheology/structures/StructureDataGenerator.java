package de.sakurajin.evenbetterarcheology.structures;

import de.sakurajin.sakuralib.util.DatagenModContainer;
import de.sakurajin.sakuralib.json.worldgen.processor.JProcessor;
import de.sakurajin.sakuralib.json.worldgen.processor.ProcessorRule;

import java.util.HashMap;

public class StructureDataGenerator {
    private final HashMap<String, JProcessor> processorsParts = new HashMap<>();
    private final String                      structureType   = "evenbetterarcheology_structures";

    public StructureDataGenerator(DatagenModContainer container) {
        generateProcessorsParts(container);

        generateBiomeTags(container);
        generateStructureTags(container);

        generateStructures(container);
    }

    private void generateArcheologistCamps(DatagenModContainer container) {
        ModStructureDataBuilder
            .create("archeologist_camp_grassy", structureType)
            .addProcessor(processorsParts.get("susdirt_taiga"))
            .buildStructure(container)
            .buildStructurePool(container);

        ModStructureDataBuilder
            .create("archeologist_camp_redsand", structureType)
            .addProcessor(processorsParts.get("susredsand_mesa"))
            .buildStructure(container)
            .buildStructurePool(container);

        ModStructureDataBuilder
            .create("archeologist_camp_sand", structureType)
            .addProcessor(processorsParts.get("sussand_desert"))
            .buildStructure(container)
            .buildStructurePool(container);
    }

    private void generateFossils(DatagenModContainer container) {
        ModStructureDataBuilder
            .create("fossil_chicken", structureType)
            .addProcessor(processorsParts.get("fossil_chicken"))
            .buildStructure(container)
            .buildStructurePool(container);

        ModStructureDataBuilder
            .create("fossil_chicken_birch", structureType)
            .addProcessor(processorsParts.get("fossil_chicken"))
            .buildStructure(container)
            .buildStructurePool(container);

        ModStructureDataBuilder
            .create("fossil_creeper", structureType)
            .addProcessor(processorsParts.get("fossil_creeper"))
            .buildStructure(container)
            .buildStructurePool(container);

        ModStructureDataBuilder
            .create("fossil_jungle_0", structureType)
            .addProcessor(processorsParts.get("fossil_jungle"))
            .startHeight(-3)
            .buildStructure(container)
            .buildStructurePool(container);

        ModStructureDataBuilder
            .create("fossil_jungle_1", structureType)
            .addProcessor(processorsParts.get("fossil_jungle"))
            .startHeight(-1)
            .buildStructure(container)
            .buildStructurePool(container);

        ModStructureDataBuilder
            .create("fossil_sheep_0", structureType)
            .addProcessor(processorsParts.get("fossil_sheep"))
            .buildStructure(container)
            .buildStructurePool(container);

        ModStructureDataBuilder
            .create("villager_grave", structureType)
            .addProcessors(
                processorsParts.get("fossil_villager"),
                processorsParts.get("cobblestone_to_mossy_cobblestone_0.5")
            )
            .buildStructure(container)
            .buildStructurePool(container);
    }

    private void generateUnderwater(DatagenModContainer container) {
        for (int i = 0; i < 4; i++) {
            ModStructureDataBuilder
                .create("underwater_" + i, structureType)
                .addProcessor(processorsParts.get("sussand_underwater"))
                .structureBiomeID(container.getSimpleID("underwater", "#has_structure"))
                .buildStructure(container)
                .buildStructurePool(container);
        }
    }

    private void generateRuins(DatagenModContainer container) {
        ModStructureDataBuilder
            .create("ruins_sand", structureType)
            .addProcessors(
                processorsParts.get("chest_buried_ruins_sand"),
                processorsParts.get("susgravel_plains")
            )
            .startHeight(-4)
            .buildStructure(container)
            .buildStructurePool(container);

        ModStructureDataBuilder
            .create("buried_ruins_sand", structureType)
            .addProcessor(processorsParts.get("susgravel_plains"))
            .startHeight(-4)
            .buildStructure(container)
            .buildStructurePool(container);

        ModStructureDataBuilder
            .create("desert_obelisk", structureType)
            .addProcessors(
                processorsParts.get("sandstone_to_cut_sandstone_0.15"),
                processorsParts.get("sussand_desert")
            )
            .buildStructure(container)
            .buildStructurePool(container);

        ModStructureDataBuilder
            .create("mesa_ruins", structureType)
            .addProcessor(processorsParts.get("susredsand_mesa"))
            .buildStructure(container)
            .buildStructurePool(container);

        ModStructureDataBuilder
            .create("mott", structureType)
            .addProcessors(processorsParts.get("susdirt_taiga"))
            .startHeight(-2)
            .buildStructure(container)
            .buildStructurePool(container);

        ModStructureDataBuilder
            .create("stonehenge_grassy", structureType)
            .addProcessor(processorsParts.get("susgravel_plains"))
            .buildStructure(container)
            .buildStructurePool(container);

        ModStructureDataBuilder
            .create("temple_jungle", structureType)
            .addProcessors(
                processorsParts.get("mud_bricks_to_cracked_mud_bricks_0.1"),
                processorsParts.get("cobblestone_to_mossy_cobblestone_0.3"),
                processorsParts.get("stone_bricks_to_mossy_stone_bricks_0.3")
            )
            .buildStructure(container)
            .buildStructurePool(container);

        ModStructureDataBuilder
            .create("tumulus_grassy", structureType)
            .addProcessors(
                processorsParts.get("chest_tumulus"),
                processorsParts.get("susgravel_plains")
            )
            .buildStructure(container)
            .buildStructurePool(container);
    }

    private void generateStructures(DatagenModContainer container) {
        generateArcheologistCamps(container);
        generateFossils(container);
        generateUnderwater(container);
        generateRuins(container);
    }

    private void generateBiomeTags(DatagenModContainer container) {
        String biomePrefix = "worldgen/biome/";

        container.addTag(biomePrefix + "is_desert", "minecraft:desert");
        container.addTag(biomePrefix + "is_thick_jungle", "minecraft:jungle", "minecraft:bamboo_jungle");
        container.addTag(biomePrefix + "is_birch_forest", "minecraft:birch_forest", "minecraft:old_growth_birch_forest");
        container.addTag(
            biomePrefix + "is_flowery",
            "minecraft:plains",
            "minecraft:sunflower_plains",
            "minecraft:forest",
            "minecraft:cherry_grove",
            "minecraft:meadow"
        );
        container.addTag(
            biomePrefix + "is_grassy",
            "#minecraft:is_taiga",
            "#is_flowery",
            "#is_birch_forest"
        );
        container.addTag(
            biomePrefix + "is_cold_forest",
            "#minecraft:is_taiga",
            "minecraft:dark_forest",
            "minecraft:stony_peaks"
        );
    }

    private void generateStructureTags(DatagenModContainer container) {
        String structurePrefix = "worldgen/biome/has_structure/";

        //structures in the badlands biomes
        container.addTag(structurePrefix + "archeologist_camp_redsand", "#minecraft:is_badlands");
        container.addTag(structurePrefix + "mesa_ruins", "#minecraft:is_badlands");

        //structures in the desert biomes
        container.addTag(structurePrefix + "archeologist_camp_sand", "#evenbetterarcheology:is_desert");
        container.addTag(structurePrefix + "buried_ruins_sand", "#evenbetterarcheology:is_desert");
        container.addTag(structurePrefix + "ruins_sand", "#evenbetterarcheology:is_desert");
        container.addTag(structurePrefix + "desert_obelisk", "#evenbetterarcheology:is_desert");

        //structures in all jungle biomes
        container.addTag(structurePrefix + "fossil_jungle_0", "#minecraft:is_jungle");
        container.addTag(structurePrefix + "fossil_jungle_1", "#minecraft:is_jungle");

        //structures in thick jungle biomes
        container.addTag(structurePrefix + "temple_jungle", "#evenbetterarcheology:is_thick_jungle");

        //structures underwater
        container.addTag(structurePrefix + "underwater", "#minecraft:is_ocean");

        //structures in all birch forest biomes
        container.addTag(structurePrefix + "fossil_chicken_birch", "#evenbetterarcheology:is_birch_forest");

        //structures in all grassy biomes
        container.addTag(structurePrefix + "archeologist_camp_grassy", "#evenbetterarcheology:is_grassy");
        container.addTag(structurePrefix + "fossil_creeper", "#evenbetterarcheology:is_grassy");
        container.addTag(structurePrefix + "stonehenge_grassy", "#evenbetterarcheology:is_grassy");
        container.addTag(structurePrefix + "tumulus_grassy", "#evenbetterarcheology:is_grassy");
        container.addTag(structurePrefix + "fossil_sheep_0", "#evenbetterarcheology:is_grassy");

        //structures in all flowery biomes
        container.addTag(structurePrefix + "fossil_chicken", "#evenbetterarcheology:is_flowery");

        //structures in all cold forest biomes
        container.addTag(structurePrefix + "mott", "#evenbetterarcheology:is_cold_forest");

        //structures with custom biome lists
        container.addTag(
            structurePrefix + "villager_grave",
            "#minecraft:is_hill",
            "#minecraft:is_taiga",
            "#minecraft:is_jungle",
            "#minecraft:is_forest",
            "minecraft:savanna",
            "minecraft:plains",
            "minecraft:sunflower_plains",
            "minecraft:swamp"
        );

    }

    private void generateProcessorsParts(DatagenModContainer container) {
        processorsParts.put("susdirt_taiga", JProcessor
            .byRule()
            .rule(ProcessorRule.addLootTable("evenbetterarcheology:suspicious_dirt", "evenbetterarcheology:archeology/taiga_dirt"))
        );

        processorsParts.put("susredsand_mesa", JProcessor
            .byRule()
            .rule(ProcessorRule.addLootTable("evenbetterarcheology:suspicious_red_sand", "evenbetterarcheology:archeology/mesa_red_sand"))
        );

        processorsParts.put("sussand_desert", JProcessor
            .byRule()
            .rule(ProcessorRule.addLootTable("minecraft:suspicious_sand", "evenbetterarcheology:archeology/desert_sand"))
        );

        processorsParts.put("sussand_underwater", JProcessor
            .byRule()
            .rule(ProcessorRule.addLootTable("minecraft:suspicious_sand", "evenbetterarcheology:archeology/sussand_underwater"))
        );

        processorsParts.put("fossil_chicken", JProcessor
            .byRule()
            .rule(ProcessorRule.addLootTable("evenbetterarcheology:fossiliferous_dirt", "evenbetterarcheology:archeology/fossiliferous_dirt_chicken"))
        );

        processorsParts.put("fossil_creeper", JProcessor
            .byRule()
            .rule(ProcessorRule.addLootTable("evenbetterarcheology:fossiliferous_dirt", "evenbetterarcheology:archeology/fossiliferous_dirt_creeper"))
        );

        processorsParts.put("fossil_jungle", JProcessor
            .byRule()
            .rule(ProcessorRule.addLootTable("evenbetterarcheology:fossiliferous_dirt", "evenbetterarcheology:archeology/fossiliferous_dirt_jungle"))
        );

        processorsParts.put("fossil_sheep", JProcessor
            .byRule()
            .rule(ProcessorRule.addLootTable("evenbetterarcheology:fossiliferous_dirt", "evenbetterarcheology:archeology/fossiliferous_dirt_sheep"))
        );

        processorsParts.put("fossil_villager", JProcessor
            .byRule()
            .rule(ProcessorRule.addLootTable("evenbetterarcheology:fossiliferous_dirt", "evenbetterarcheology:archeology/fossiliferous_dirt_villager"))
        );

        processorsParts.put("chest_buried_ruins_sand", JProcessor
            .byRule()
            .rule(ProcessorRule.addLootTable("minecraft:chest", "evenbetterarcheology:chests/chest_buried_ruins_sand"))
        );

        processorsParts.put("chest_tumulus", JProcessor
            .byRule()
            .rule(ProcessorRule.addLootTable("minecraft:chest", "evenbetterarcheology:chests/chest_tumulus"))
        );

        processorsParts.put("chest_underwater", JProcessor
            .byRule()
            .rule(ProcessorRule.addLootTable("minecraft:barrel", "evenbetterarcheology:chests/chest_underwater"))
        );

        processorsParts.put("susgravel_plains", JProcessor.byRule().rule(ProcessorRule.replaceBlockWithLoot(
            "minecraft:gravel",
            "minecraft:suspicious_gravel",
            0.1,
            "evenbetterarcheology:archeology/plains_gravel"
        )));

        processorsParts.put("sandstone_to_cut_sandstone_0.15", JProcessor.byRule().rule(ProcessorRule.replaceBlock(
            "minecraft:sandstone",
            "minecraft:cut_sandstone",
            0.15)));

        processorsParts.put("cobblestone_to_mossy_cobblestone_0.5", JProcessor.byRule().rule(ProcessorRule.replaceBlock(
            "minecraft:cobblestone",
            "minecraft:mossy_cobblestone",
            0.5)));

        processorsParts.put("stone_bricks_to_mossy_stone_bricks_0.3", JProcessor.byRule().rule(ProcessorRule.replaceBlock(
            "minecraft:stone_bricks",
            "minecraft:mossy_stone_bricks",
            0.3)));

        processorsParts.put("stone_bricks_to_cracked_stone_bricks_0.3", JProcessor.byRule().rule(ProcessorRule.replaceBlock(
            "minecraft:stone_bricks",
            "minecraft:cracked_stone_bricks",
            0.3)));

        processorsParts.put("cobblestone_to_mossy_cobblestone_0.3", JProcessor.byRule().rule(ProcessorRule.replaceBlock(
            "minecraft:cobblestone",
            "minecraft:mossy_cobblestone",
            0.3)));

        processorsParts.put("mud_bricks_to_cracked_mud_bricks_0.1", JProcessor.byRule().rule(ProcessorRule.replaceBlock(
            "minecraft:mud_bricks",
            "evenbetterarcheology:cracked_mud_bricks",
            0.1)));

    }
}
