package de.sakurajin.evenbetterarcheology.registry;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.api.enchantment.ArtifactEnchantment;

import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;

public class ModEnchantments {
    public static ArtifactEnchantment PENETRATING_STRIKE = new ArtifactEnchantment("penetrating_strike", EnchantmentTarget.WEAPON, EquipmentSlot.MAINHAND) {
        //also allowing axes
        @Override
        public boolean isAcceptableItem(ItemStack stack) {
            if (stack.getItem() instanceof AxeItem) {
                return true;
            }
            return super.isAcceptableItem(stack);
        }

        @Override
        public int getMaxLevel() {
            return EvenBetterArcheology.CONFIG.PENETRATING_STRIKE_MAXLEVEL();
        }
    };

    public static ArtifactEnchantment SOARING_WINDS = new ArtifactEnchantment("soaring_winds", EnchantmentTarget.ARMOR_CHEST, EquipmentSlot.MAINHAND) {
        @Override
        public int getMaxLevel() {
            return EvenBetterArcheology.CONFIG.SOARING_WINDS_MAXLEVEL();
        }

        @Override
        public boolean isAcceptableItem(ItemStack stack) {
            return stack.getItem() instanceof ElytraItem || stack.getItem() instanceof FabricElytraItem;
        }
    };

    public static ArtifactEnchantment TUNNELING = new ArtifactEnchantment("tunneling", EnchantmentTarget.DIGGER, EquipmentSlot.MAINHAND){
        @Override
        public boolean isAcceptableItem(ItemStack stack) {
            return stack.getItem() instanceof PickaxeItem || stack.getItem() instanceof ShovelItem;
        }
    };

    public static ArtifactEnchantment SEAS_BOUNTY = new ArtifactEnchantment("seas_bounty", EnchantmentTarget.FISHING_ROD, EquipmentSlot.MAINHAND) {
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
