package de.sakurajin.evenbetterarcheology.structures;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.json.worldgen.processor.JProcessor;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.json.worldgen.processor.ProcessorRule;

import java.util.HashMap;

public class StructureDataGenerator {
    private final HashMap<String, JProcessor> processorsParts = new HashMap<>();

    public StructureDataGenerator(DatagenModContainer container){
        generateProcessorsParts(container);

        generateStructureProcessors(container);
    }

    private void generateStructureProcessors(DatagenModContainer container){
        String[] addDirect = new String[]{
            "fossil_chicken", "fossil_creeper", "fossil_jungle", "fossil_sheep", "fossil_villager",
            "sussand_underwater", "susgravel_plains", "susdirt_taiga", "susredsand_mesa", "sussand_desert"
        };
        for(String key : addDirect){
            processorsParts.get(key).addToResourcePack(container, key);
        }

        JProcessor.addToResourcePack(
                container, "temple_jungle",
                processorsParts.get("mud_bricks_to_cracked_mud_bricks_0.1"),
                processorsParts.get("cobblestone_to_mossy_cobblestone_0.3"),
                processorsParts.get("stone_bricks_to_mossy_stone_bricks_0.3")
        );

        JProcessor.addToResourcePack(
                container, "tumulus_grassy",
                processorsParts.get("chest_tumulus"),
                processorsParts.get("susgravel_plains")
        );

        JProcessor.addToResourcePack(
                container, "villager_grave",
                processorsParts.get("fossil_villager"),
                processorsParts.get("cobblestone_to_mossy_cobblestone_0.5")
        );

        JProcessor.addToResourcePack(
                container, "ruins_sand",
                processorsParts.get("chest_buried_ruins_sand"),
                processorsParts.get("susgravel_plains")
        );

        JProcessor.addToResourcePack(
                container, "desert_obelisk",
                processorsParts.get("sandstone_to_cut_sandstone_0.15"),
                processorsParts.get("sussand_desert")
        );
    }

    private void generateProcessorsParts(DatagenModContainer container){
        processorsParts.put("susdirt_taiga", JProcessor.byRule()
            .rule(ProcessorRule.addLootTable("evenbetterarcheology:suspicious_dirt", "evenbetterarcheology:archeology/taiga_dirt"))
        );

        processorsParts.put("susredsand_mesa", JProcessor.byRule()
            .rule(ProcessorRule.addLootTable("evenbetterarcheology:suspicious_red_sand", "evenbetterarcheology:archeology/mesa_red_sand"))
        );

        processorsParts.put("sussand_desert", JProcessor.byRule()
            .rule(ProcessorRule.addLootTable("minecraft:suspicious_sand", "evenbetterarcheology:archeology/desert_sand"))
        );

        processorsParts.put("sussand_underwater", JProcessor.byRule()
            .rule(ProcessorRule.addLootTable("minecraft:suspicious_sand", "evenbetterarcheology:archeology/sussand_underwater"))
        );

        processorsParts.put("fossil_chicken", JProcessor.byRule()
            .rule(ProcessorRule.addLootTable("evenbetterarcheology:fossiliferous_dirt", "evenbetterarcheology:archeology/fossiliferous_dirt_chicken"))
        );

        processorsParts.put("fossil_creeper", JProcessor.byRule()
            .rule(ProcessorRule.addLootTable("evenbetterarcheology:fossiliferous_dirt", "evenbetterarcheology:archeology/fossiliferous_dirt_creeper"))
        );

        processorsParts.put("fossil_jungle", JProcessor.byRule()
            .rule(ProcessorRule.addLootTable("evenbetterarcheology:fossiliferous_dirt", "evenbetterarcheology:archeology/fossiliferous_dirt_jungle"))
        );

        processorsParts.put("fossil_sheep", JProcessor.byRule()
            .rule(ProcessorRule.addLootTable("evenbetterarcheology:fossiliferous_dirt", "evenbetterarcheology:archeology/fossiliferous_dirt_sheep"))
        );

        processorsParts.put("fossil_villager", JProcessor.byRule()
            .rule(ProcessorRule.addLootTable("evenbetterarcheology:fossiliferous_dirt", "evenbetterarcheology:archeology/fossiliferous_dirt_villager"))
        );

        processorsParts.put("chest_buried_ruins_sand", JProcessor.byRule()
            .rule(ProcessorRule.addLootTable("minecraft:chest", "evenbetterarcheology:chests/chest_buried_ruins_sand"))
        );

        processorsParts.put("chest_tumulus", JProcessor.byRule()
                .rule(ProcessorRule.addLootTable("minecraft:chest", "evenbetterarcheology:chests/chest_tumulus"))
        );

        processorsParts.put("chest_underwater", JProcessor.byRule()
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
