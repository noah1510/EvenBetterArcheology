package de.sakurajin.evenbetterarcheology.enchantment;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.api.ArtifactEnchantment;
import de.sakurajin.evenbetterarcheology.registry.ModEnchantments;
import de.sakurajin.evenbetterarcheology.util.ElytraHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class PenetratingStrikeEnchantment extends ArtifactEnchantment {

    public PenetratingStrikeEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
        super("penetrating_strike", weight, EnchantmentTarget.WEAPON, slotTypes);
    }

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

    /**
     * This function returns the level of the penetrating strike enchantment of the player.
     * It checks both hands and returns the first level it finds.
     * If no level is found it returns 0
     * @param entity the entity to check
     * @return int the level of the penetrating strike enchantment
     */
    public static int getPenetratingStrikeLevel(LivingEntity entity){
        int penetratingStrikeLevel = 0;

        penetratingStrikeLevel = ElytraHelper.getSafeEnchantLevel(ModEnchantments.PENETRATING_STRIKE, entity.getMainHandStack());
        if(penetratingStrikeLevel > 0){
            return penetratingStrikeLevel;
        }

        penetratingStrikeLevel = ElytraHelper.getSafeEnchantLevel(ModEnchantments.PENETRATING_STRIKE, entity.getOffHandStack());
        if(penetratingStrikeLevel > 0){
            return penetratingStrikeLevel;
        }

        return 0;
    }
}

