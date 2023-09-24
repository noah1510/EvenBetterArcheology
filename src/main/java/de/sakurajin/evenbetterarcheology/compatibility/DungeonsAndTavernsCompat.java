package de.sakurajin.evenbetterarcheology.compatibility;

import de.sakurajin.evenbetterarcheology.registry.ModItems;
import de.sakurajin.sakuralib.loot.v1.LootEntryInsert;
import de.sakurajin.sakuralib.loot.v1.LootSourceHelper;
import de.sakurajin.sakuralib.loot.v1.LootTableManager;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

import java.util.List;

public class DungeonsAndTavernsCompat {
    public static List<Identifier> DUNGEONS_AND_TAVERNS_LOOT_TABLES = List.of(
        new Identifier("minecraft", "archaelogy/ancient_city"),
        new Identifier("minecraft", "archaelogy/conduit_ruins"),
        new Identifier("minecraft", "archaelogy/jungle_ruins"),
        new Identifier("minecraft", "archaelogy/ruins")
    );

    public static void init(){
        if(!FabricLoader.getInstance().isModLoaded("mr_dungeons_andtaverns")){
            return;
        }

        //inject artifact shards into all archeology loot tables
        var ArtifactShardEntry = new LootEntryInsert(
            ItemEntry.builder(ModItems.ARTIFACT_SHARDS).weight(4).quality(1).build(),
            0,
            LootSourceHelper.ALL
        );

        DUNGEONS_AND_TAVERNS_LOOT_TABLES.forEach(table -> {
            LootTableManager.insertEntry(ArtifactShardEntry, table);
        });
    }
}
