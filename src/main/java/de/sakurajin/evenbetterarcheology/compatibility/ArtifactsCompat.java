package de.sakurajin.evenbetterarcheology.compatibility;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.block.custom.ArchelogyTable;

import de.sakurajin.sakuralib.loot.v2.table_insert.LootEntryInsert;
import de.sakurajin.sakuralib.loot.v2.LootTableManager;
import de.sakurajin.sakuralib.loot.v2.LootSourceHelper;

import de.sakurajin.sakuralib.loot.v2.table_insert.LootTableInsertManager;
import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

/**
 * This class handles compatibility with the Artifacts mod.
 * If enabled in the config file, artifacts will be added to the archeology table loot table.
 */
public class ArtifactsCompat {
    public static void init(){
        if(!FabricLoader.getInstance().isModLoaded("artifacts")) return;

        //register the function that adds the artifacts to the archeology table loot table
        LootTableInsertManager.addProvider(ArchelogyTable.IDENTIFY_LOOT_TABLE, ArtifactsCompat::getArtifactsLootEntries);
    }

    /**
     * This function is used to generate the loot table entries that are injected into the archeology table loot table.
     * It should be called every time the loot tables are build, so calling /reload should call this function.
     *
     * @return a list of loot table entries that are injected into the archeology table loot table
     */
    public static ArrayList<LootEntryInsert> getArtifactsLootEntries(){
        EvenBetterArcheology.DATA.LOGGER.debug("Adding artifacts to archeology table loot table");

        ArrayList<LootEntryInsert> entries = new ArrayList<>();
        if(EvenBetterArcheology.CONFIG.COMPATIBILITY_OPTIONS.ARCHEOLOGY_TABLE_ARTIFACTS_LOOT()){
            entries.add(
                new LootEntryInsert(
                    LootTableEntry
                        .builder(new Identifier("artifacts", "artifact"))
                        .weight(EvenBetterArcheology.CONFIG.COMPATIBILITY_OPTIONS.ARCHEOLOGY_TABLE_ARTIFACTS_LOOT_WEIGHT())
                        .quality(EvenBetterArcheology.CONFIG.COMPATIBILITY_OPTIONS.ARCHEOLOGY_TABLE_ARTIFACTS_LOOT_QUALITY())
                        .build(),
                    0,
                    LootSourceHelper.ALL
                )
            );
        }
        return entries;
    }
}
