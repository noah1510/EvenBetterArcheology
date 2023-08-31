package de.sakurajin.evenbetterarcheology.enchantment;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static Enchantment PENETRATING_STRIKE = register("penetrating_strike", new PenetratingStrikeEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));
    public static Enchantment SOARING_WINDS = register("soaring_winds", new SoaringWindsEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR_CHEST, EquipmentSlot.MAINHAND));
    public static Enchantment TUNNELING = register("tunneling", new TunnelingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.DIGGER, EquipmentSlot.MAINHAND) {
    });

    private static Enchantment register(String name, Enchantment enchantment) {
        registerEnchantedBookWith(enchantment);
        return Registry.register(Registries.ENCHANTMENT, new Identifier(EvenBetterArcheology.DATA.MOD_ID, name), enchantment);
    }

    private static void registerEnchantedBookWith(Enchantment enchantment) {
        ItemGroupEvents.modifyEntriesEvent(EvenBetterArcheology.DATA.VANILLA_GROUP_KEY).register(entries -> entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, enchantment.getMaxLevel())).setCustomName(Text.translatable("item.evenbetterarcheology.identified_artifact").formatted(Formatting.RESET,
                Formatting.YELLOW))));
    }

    //LOGGER
    public static void registerModEnchantments() {
        EvenBetterArcheology.DATA.LOGGER.info("Registering Enchantments for " + EvenBetterArcheology.DATA.MOD_ID);
    }
}
