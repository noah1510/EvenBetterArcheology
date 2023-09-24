package de.sakurajin.evenbetterarcheology.compatibility;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.block.custom.ArchelogyTable;
import de.sakurajin.sakuralib.loot.v1.LootEntryInsert;
import de.sakurajin.sakuralib.loot.v1.LootTableManager;
import de.sakurajin.sakuralib.loot.v1.LootSourceHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.util.Identifier;

/**
 * This class handles compatibility with the Artifacts mod.
 * If enabled in the config file, artifacts will be added to the archeology table loot table.
 */
public class ArtifactsCompat {
    public static void init(){
        if(!FabricLoader.getInstance().isModLoaded("artifacts")) return;

        //inject artifacts into the archeology table loot table
        if(EvenBetterArcheology.CONFIG.COMPATIBILITY_OPTIONS.ARCHEOLOGY_TABLE_ARTIFACTS_LOOT()){
            LootTableManager.insertEntries(
                ArchelogyTable.IDENTIFY_LOOT_TABLE,
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
    }
}
