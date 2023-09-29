package de.sakurajin.evenbetterarcheology.api.enchantment;

import de.sakurajin.evenbetterarcheology.api.enchantment.ArtifactEnchantmentRegistry;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

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
     * @param target          the target of the enchantment
     * @param slotTypes       the slot types of the enchantment
     * @apiNote This will automatically be added to the list of artifact enchantments and the evenbetterarcheology tab
     * It will also be registered in the game with the identifier "evenbetterarcheology:<enchantmentName>"
     */
    protected ArtifactEnchantment(String enchantmentName, EnchantmentTarget target, EquipmentSlot... slotTypes) {
        super(Enchantment.Rarity.VERY_RARE, target, slotTypes);
        NAME = enchantmentName;
        ArtifactEnchantmentRegistry.register(enchantmentName, this);
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
}
