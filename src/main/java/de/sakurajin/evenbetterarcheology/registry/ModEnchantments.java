package de.sakurajin.evenbetterarcheology.registry;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.api.ArtifactEnchantment;
import de.sakurajin.evenbetterarcheology.enchantment.PenetratingStrikeEnchantment;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static Enchantment PENETRATING_STRIKE = new PenetratingStrikeEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND);
    public static Enchantment SOARING_WINDS = new ArtifactEnchantment("soaring_winds", Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR_CHEST, EquipmentSlot.MAINHAND) {
        @Override
        public int getMaxLevel() {
            return EvenBetterArcheology.CONFIG.SOARING_WINDS_MAXLEVEL();
        }

        @Override
        public boolean isAcceptableItem(ItemStack stack) {
            return stack.getItem() instanceof ElytraItem || stack.getItem() instanceof FabricElytraItem;
        }
    };

    public static Enchantment TUNNELING = new ArtifactEnchantment("tunneling", Enchantment.Rarity.VERY_RARE, EnchantmentTarget.DIGGER, EquipmentSlot.MAINHAND){
        @Override
        public boolean isAcceptableItem(ItemStack stack) {
            return stack.getItem() instanceof PickaxeItem || stack.getItem() instanceof ShovelItem;
        }
    };

    public static Enchantment SEAS_BOUNTY = new ArtifactEnchantment("seas_bounty", Enchantment.Rarity.VERY_RARE, EnchantmentTarget.FISHING_ROD, EquipmentSlot.MAINHAND) {
        @Override
        public boolean isAcceptableItem(ItemStack stack) {
            return stack.getItem() instanceof FishingRodItem;
        }
    };

    //A simple function to make sure the static variables are initialized
    public static void registerModEnchantments() {
        EvenBetterArcheology.DATA.LOGGER.info("Registering Enchantments for " + EvenBetterArcheology.DATA.MOD_ID);
    }
}
