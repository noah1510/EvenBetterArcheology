package de.sakurajin.evenbetterarcheology.enchantment;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;

public class SoaringWindsEnchantment extends ArtifactEnchantment {
    protected SoaringWindsEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot... slotTypes) {
        super(weight, target, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return EvenBetterArcheology.CONFIG.SOARING_WINDS_MAXLEVEL();
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ElytraItem;
    }

}
