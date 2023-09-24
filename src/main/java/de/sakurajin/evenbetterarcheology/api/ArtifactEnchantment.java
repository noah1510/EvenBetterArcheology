package de.sakurajin.evenbetterarcheology.api;

import com.google.gson.JsonObject;
import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.sakuralib.loot.v1.LootDistributionHelper;
import de.sakurajin.sakuralib.loot.v1.LootEntryInsert;
import de.sakurajin.sakuralib.loot.v1.LootSourceHelper;
import de.sakurajin.sakuralib.loot.v1.LootTableManager;
import net.devtech.arrp.json.loot.JEntry;
import net.devtech.arrp.json.loot.JLootTable;
import net.devtech.arrp.json.loot.JPool;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetEnchantmentsLootFunction;
import net.minecraft.loot.function.SetLoreLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * A generic class representing an enchantment that is only available as loot.
 * It also includes a registry for all artifact enchantments and can generate a loot table for them.
 */
public abstract class ArtifactEnchantment extends Enchantment {
    protected final String NAME;

    /**
     * Create a new artifact enchantment
     *
     * @param enchantmentName the name of the enchantment
     * @param weight          the weight of the enchantment
     * @param target          the target of the enchantment
     * @param slotTypes       the slot types of the enchantment
     * @apiNote This will automatically be added to the list of artifact enchantments and the evenbetterarcheology tab
     * It will also be registered in the game with the identifier "evenbetterarcheology:<enchantmentName>"
     */
    protected ArtifactEnchantment(String enchantmentName, Rarity weight, EnchantmentTarget target, EquipmentSlot... slotTypes) {
        super(weight, target, slotTypes);
        NAME = enchantmentName;
        register(enchantmentName, this);
    }

    /**
     * By default, the enchantment is not available from the enchantment table
     *
     * @return false
     */
    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    /**
     * Block the enchantment from being fishable
     *
     * @return false
     */
    @Override
    public boolean isTreasure() {
        return false;
    }

    /**
     * Block the enchantment from being randomly selected
     *
     * @return false
     */
    @Override
    public boolean isAvailableForRandomSelection() {
        return false;
    }

    /**
     * Set the rarity of the enchantment to VERY_RARE since it is only available as loot
     *
     * @return Rarity.VERY_RARE
     */
    @Override
    public Rarity getRarity() {
        return Rarity.VERY_RARE;
    }

    /**
     * The identifier of the loot table for artifact enchantments.
     * This table produces one enchanted book with one of the registered artifact enchantments.
     * The enchantment level is determined by the power distribution with a power of 4.
     * All enchantments should have a weight of 100 when you sum all the weights of their levels.
     * All enchantments have a quality of level-1.
     *
     * @see LootDistributionHelper#getPowerDistribution(int, int)
     */
    public static final Identifier LOOT_TABLE_ID = EvenBetterArcheology.DATA.getSimpleID("artifact_enchantments");

    /**
     * Get a loot table entry which can result in an artifact enchantment
     *
     * @return a loot table entry
     */
    public static JEntry getLootTableEntry() {
        return JLootTable.entry().type("minecraft:loot_table").name(LOOT_TABLE_ID.toString());
    }

    /**
     * Create an Enchantment Book with the given enchantment and level and the name "Identified Artifact"
     * @param enchantment the enchantment
     * @param level the level
     * @return an Item Stack with the enchantment book
     */
    public static ItemStack createArtifactEnchantmentBook(ArtifactEnchantment enchantment, int level) {
        return EnchantedBookItem.forEnchantment(
            new EnchantmentLevelEntry(enchantment, level)
        ).setCustomName(
            Text
                .translatable("item.evenbetterarcheology.identified_artifact")
                .formatted(Formatting.RESET, Formatting.YELLOW)
        );
    }

    /**
     * Register an enchantment and add a book with the enchantment to the evenbetterarcheology tab and the loot table
     *
     * @param name        the name of the enchantment
     * @param enchantment the enchantment
     */
    private static void register(String name, ArtifactEnchantment enchantment) {
        int maxLevel = enchantment.getMaxLevel();
        if (maxLevel < 1) {
            throw new IllegalArgumentException("Artifact enchantments must have at least one level");
        }

        //get the distribution ahead of time to speed things up a bit
        var scaledWeights = LootDistributionHelper.getPowerDistribution(4, maxLevel);

        //add all books to the creative tab and the loot table
        for (int level = 0; level < maxLevel; level++) {
            final var book = createArtifactEnchantmentBook(enchantment, level + 1);

            //add the enchantment book to the evenbetterarcheology tab
            ItemGroupEvents
                .modifyEntriesEvent(EvenBetterArcheology.DATA.VANILLA_GROUP_KEY)
                .register(entries -> entries.add(book));


            //inject the book into the artifact enchantment loot table
            int weight = (int) Math.ceil(scaledWeights.get(level) * 100);
            LootTableManager.insertEntries(
                LOOT_TABLE_ID,
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

        //register the enchantment with the minecraft registry
        Registry.register(Registries.ENCHANTMENT, new Identifier(EvenBetterArcheology.DATA.MOD_ID, name), enchantment);
    }
}
