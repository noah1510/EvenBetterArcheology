package de.sakurajin.evenbetterarcheology.datafix;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.schemas.Schema;
import net.minecraft.datafixer.fix.BlockNameFix;

import java.util.Map;

public class BetterArcheology {
    // this is a map of old block names to new block names
    public static final Map<String, String> BLOCKS = ImmutableMap.ofEntries(
        // sus blocks
        Map.entry("betterarcheology:suspicious_red_sand", "evenbetterarcheology:suspicious_red_sand"),
        Map.entry("betterarcheology:suspicious_dirt", "evenbetterarcheology:suspicious_dirt"),
        Map.entry("betterarcheology:fossiliferous_dirt", "evenbetterarcheology:fossiliferous_dirt"),

        //fossils
        Map.entry("betterarcheology:villager_fossil", "evenbetterarcheology:villager_fossil"),
        Map.entry("betterarcheology:villager_fossil_head", "evenbetterarcheology:villager_fossil_head"),
        Map.entry("betterarcheology:villager_fossil_body", "evenbetterarcheology:villager_fossil_body"),

        Map.entry("betterarcheology:ocelot_fossil", "evenbetterarcheology:ocelot_fossil"),
        Map.entry("betterarcheology:ocelot_fossil_head", "evenbetterarcheology:ocelot_fossil_head"),
        Map.entry("betterarcheology:ocelot_fossil_body", "evenbetterarcheology:ocelot_fossil_body"),

        Map.entry("betterarcheology:sheep_fossil", "evenbetterarcheology:sheep_fossil"),
        Map.entry("betterarcheology:sheep_fossil_head", "evenbetterarcheology:sheep_fossil_head"),
        Map.entry("betterarcheology:sheep_fossil_body", "evenbetterarcheology:sheep_fossil_body"),

        Map.entry("betterarcheology:chicken_fossil", "evenbetterarcheology:chicken_fossil"),
        Map.entry("betterarcheology:chicken_fossil_head", "evenbetterarcheology:chicken_fossil_head"),
        Map.entry("betterarcheology:chicken_fossil_body", "evenbetterarcheology:chicken_fossil_body"),

        Map.entry("betterarcheology:creeper_fossil", "evenbetterarcheology:creeper_fossil"),
        Map.entry("betterarcheology:creeper_fossil_head", "evenbetterarcheology:creeper_fossil_head"),
        Map.entry("betterarcheology:creeper_fossil_body", "evenbetterarcheology:creeper_fossil_body"),

        //rotton wood
        Map.entry("betterarcheology:rotten_log", "evenbetterarcheology:rotten_wood_log"),
        Map.entry("betterarcheology:rotten_planks", "evenbetterarcheology:rotten_wood_planks"),
        Map.entry("betterarcheology:rotten_slab", "evenbetterarcheology:rotten_wood_slab"),
        Map.entry("betterarcheology:rotten_stairs", "evenbetterarcheology:rotten_wood_stairs"),
        Map.entry("betterarcheology:rotten_fence", "evenbetterarcheology:rotten_wood_fence"),
        Map.entry("betterarcheology:rotten_fence_gate", "evenbetterarcheology:rotten_wood_fence_gate"),
        Map.entry("betterarcheology:rotten_trapdoor", "evenbetterarcheology:rotten_wood_trapdoor"),
        Map.entry("betterarcheology:rotten_door", "evenbetterarcheology:rotten_wood_door"),

        //other blocks
        Map.entry("betterarcheology:infested_mud_bricks", "evenbetterarcheology:infested_mud_bricks"),
        Map.entry("betterarcheology:cracked_mud_bricks", "evenbetterarcheology:cracked_mud_bricks"),
        Map.entry("betterarcheology:cracked_mud_brick_slab", "evenbetterarcheology:cracked_mud_brick_slab"),
        Map.entry("betterarcheology:cracked_mud_brick_stairs", "evenbetterarcheology:cracked_mud_brick_stairs"),
        Map.entry("betterarcheology:archeology_table", "evenbetterarcheology:archeology_table"),
        Map.entry("betterarcheology:loot_vase", "evenbetterarcheology:loot_vase"),
        Map.entry("betterarcheology:vase", "evenbetterarcheology:vase"),
        Map.entry("betterarcheology:loot_vase_creeper", "evenbetterarcheology:loot_vase_creeper"),
        Map.entry("betterarcheology:vase_creeper", "evenbetterarcheology:vase_creeper"),
        Map.entry("betterarcheology:evoker_trap", "evenbetterarcheology:evoker_trap")
    );

    //this is a map of old item names to new item names
    public static final Map<String, String> ITEMS = ImmutableMap.ofEntries(
        // sus blocks item
        Map.entry("betterarcheology:suspicious_red_sand", "evenbetterarcheology:suspicious_red_sand"),
        Map.entry("betterarcheology:suspicious_dirt", "evenbetterarcheology:suspicious_dirt"),
        Map.entry("betterarcheology:fossiliferous_dirt", "evenbetterarcheology:fossiliferous_dirt"),

        //fossils item
        Map.entry("betterarcheology:villager_fossil", "evenbetterarcheology:villager_fossil"),
        Map.entry("betterarcheology:villager_fossil_head", "evenbetterarcheology:villager_fossil_head"),
        Map.entry("betterarcheology:villager_fossil_body", "evenbetterarcheology:villager_fossil_body"),

        Map.entry("betterarcheology:ocelot_fossil", "evenbetterarcheology:ocelot_fossil"),
        Map.entry("betterarcheology:ocelot_fossil_head", "evenbetterarcheology:ocelot_fossil_head"),
        Map.entry("betterarcheology:ocelot_fossil_body", "evenbetterarcheology:ocelot_fossil_body"),

        Map.entry("betterarcheology:sheep_fossil", "evenbetterarcheology:sheep_fossil"),
        Map.entry("betterarcheology:sheep_fossil_head", "evenbetterarcheology:sheep_fossil_head"),
        Map.entry("betterarcheology:sheep_fossil_body", "evenbetterarcheology:sheep_fossil_body"),

        Map.entry("betterarcheology:chicken_fossil", "evenbetterarcheology:chicken_fossil"),
        Map.entry("betterarcheology:chicken_fossil_head", "evenbetterarcheology:chicken_fossil_head"),
        Map.entry("betterarcheology:chicken_fossil_body", "evenbetterarcheology:chicken_fossil_body"),

        Map.entry("betterarcheology:creeper_fossil", "evenbetterarcheology:creeper_fossil"),
        Map.entry("betterarcheology:creeper_fossil_head", "evenbetterarcheology:creeper_fossil_head"),
        Map.entry("betterarcheology:creeper_fossil_body", "evenbetterarcheology:creeper_fossil_body"),

        //rotton wood item
        Map.entry("betterarcheology:rotten_log", "evenbetterarcheology:rotten_wood_log"),
        Map.entry("betterarcheology:rotten_planks", "evenbetterarcheology:rotten_wood_planks"),
        Map.entry("betterarcheology:rotten_slab", "evenbetterarcheology:rotten_wood_slab"),
        Map.entry("betterarcheology:rotten_stairs", "evenbetterarcheology:rotten_wood_stairs"),
        Map.entry("betterarcheology:rotten_fence", "evenbetterarcheology:rotten_wood_fence"),
        Map.entry("betterarcheology:rotten_fence_gate", "evenbetterarcheology:rotten_wood_fence_gate"),
        Map.entry("betterarcheology:rotten_trapdoor", "evenbetterarcheology:rotten_wood_trapdoor"),
        Map.entry("betterarcheology:rotten_door", "evenbetterarcheology:rotten_wood_door"),

        //other blocks item
        Map.entry("betterarcheology:infested_mud_bricks", "evenbetterarcheology:infested_mud_bricks"),
        Map.entry("betterarcheology:cracked_mud_bricks", "evenbetterarcheology:cracked_mud_bricks"),
        Map.entry("betterarcheology:cracked_mud_brick_slab", "evenbetterarcheology:cracked_mud_brick_slab"),
        Map.entry("betterarcheology:cracked_mud_brick_stairs", "evenbetterarcheology:cracked_mud_brick_stairs"),
        Map.entry("betterarcheology:archeology_table", "evenbetterarcheology:archeology_table"),
        Map.entry("betterarcheology:loot_vase", "evenbetterarcheology:loot_vase"),
        Map.entry("betterarcheology:vase", "evenbetterarcheology:vase"),
        Map.entry("betterarcheology:loot_vase_creeper", "evenbetterarcheology:loot_vase_creeper"),
        Map.entry("betterarcheology:vase_creeper", "evenbetterarcheology:vase_creeper"),
        Map.entry("betterarcheology:evoker_trap", "evenbetterarcheology:evoker_trap"),

        //regular items
        Map.entry("betterarcheology:iron_brush", "evenbetterarcheology:iron_brush"),
        Map.entry("betterarcheology:diamond_brush", "evenbetterarcheology:diamond_brush"),
        Map.entry("betterarcheology:artifact_shards", "evenbetterarcheology:artifact_shards"),
        Map.entry("betterarcheology:unidentified_artifact", "evenbetterarcheology:unidentified_artifact"),
        Map.entry("betterarcheology:bomb", "evenbetterarcheology:bomb"),
        Map.entry("betterarcheology:torrent_totem", "evenbetterarcheology:torrent_totem"),
        Map.entry("betterarcheology:soul_totem", "evenbetterarcheology:soul_totem")
    );

}
