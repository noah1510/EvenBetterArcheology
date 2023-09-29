package de.sakurajin.evenbetterarcheology.api.enchantment;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.sakuralib.loot.v2.LootSourceHelper;
import de.sakurajin.sakuralib.loot.v2.distribution.PowerDistribution;
import de.sakurajin.sakuralib.loot.v2.table_insert.LootEntryInsert;
import de.sakurajin.sakuralib.loot.v2.table_insert.LootTableInsertManager;
import net.devtech.arrp.json.loot.JEntry;
import net.devtech.arrp.json.loot.JLootTable;
import net.minecraft.item.Items;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetEnchantmentsLootFunction;
import net.minecraft.loot.function.SetLoreLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;

public class ArtifactEnchantmentRegistry {
    /**
     * The identifier of the loot table for artifact enchantments.
     * This table produces one enchanted book with one of the registered artifact enchantments.
     * The enchantment level is determined by the power distribution with a power of 4.
     * All enchantments should have a weight of 100 when you sum all the weights of their levels.
     * All enchantments have a quality of level-1.
     *
     * @see PowerDistribution#GetDistribution(int, int)
     */
    public static final Identifier LOOT_TABLE_ID = EvenBetterArcheology.DATA.getSimpleID("artifact_enchantments");

    /**
     * Keep track of all registered artifact enchantments.
     * This is useful to rebuild the loot table when needed.
     */
    private static final ArrayList<ArtifactEnchantment> REGISTERED_ENCHANTMENTS = new ArrayList<>();

    static{
        /*
         * register the loot table
         */
        LootTableInsertManager.addProvider(LOOT_TABLE_ID, ArtifactEnchantmentRegistry::getArtifactEnchantmentEntries);
    }

    /**
     * Get a loot table entry which can result in an artifact enchantment
     *
     * @return a loot table entry
     */
    public static JEntry getLootTableEntry() {
        return JLootTable.entry().type("minecraft:loot_table").name(LOOT_TABLE_ID.toString());
    }

    /**
     * Register an enchantment and add a book with the enchantment to the evenbetterarcheology tab and the loot table
     *
     * @param name        the name of the enchantment
     * @param enchantment the enchantment
     */
    @ApiStatus.Internal
    public static void register(String name, ArtifactEnchantment enchantment) {
        if (enchantment.getMaxLevel() < 1) {
            throw new IllegalArgumentException("Artifact enchantments must have at least one level");
        }

        //add the enchantment to the list of registered enchantments
        REGISTERED_ENCHANTMENTS.add(enchantment);

        //register the enchantment with the minecraft registry
        Registry.register(Registries.ENCHANTMENT, new Identifier(EvenBetterArcheology.DATA.MOD_ID, name), enchantment);
    }

    /**
     * Get a list with all artifact enchantment entries that should be added to the loot table
     * The enchantment level is determined by the power distribution with a power of 4.
     * This is registered as a provider for the loot table insert manager.
     * This will cause the loot table to be rebuilt every time the loot tables are built.
     * @return a list with all artifact enchantment entries
     */
    public static ArrayList<LootEntryInsert> getArtifactEnchantmentEntries(){
        final ArrayList<LootEntryInsert> entries = new ArrayList<>();
        EvenBetterArcheology.DATA.LOGGER.debug("Rebuilding the artifact enchantment loot table");

        for(var enchantment: REGISTERED_ENCHANTMENTS){
            final int maxLevel = enchantment.getMaxLevel();

            //get the distribution ahead of time to speed things up a bit
            final var scaledWeights = PowerDistribution.GetDistribution(4, maxLevel);

            //add all books to the creative tab and the loot table
            for (int level = 0; level < maxLevel; level++) {
                //add the book entry to the array
                int weight = (int) Math.ceil(scaledWeights.get(level) * 100);
                entries.add(
                    new LootEntryInsert(
                        ItemEntry
                            .builder(Items.ENCHANTED_BOOK)
                            .apply(
                                new SetEnchantmentsLootFunction.Builder(true)
                                    .enchantment(enchantment, ConstantLootNumberProvider.create(level + 1))
                            ).apply(
                                new SetLoreLootFunction.Builder().lore(
                                    Text
                                        .translatable("item.evenbetterarcheology.identified_artifact")
                                        .formatted(Formatting.RESET, Formatting.YELLOW)
                                )
                            )
                            .weight(weight)
                            .quality(level)
                            .build(),
                        0,
                        LootSourceHelper.ALL
                    )
                );
            }
        }

        return entries;
    }
}
