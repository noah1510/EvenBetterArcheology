package de.sakurajin.evenbetterarcheology.mixin.minecraft;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * This mixin reduces the protection level of an entity when it is hit by an entity with the penetrating strike enchantment
 * The protection level is reduced by the level of the penetrating strike enchantment
 */
@Mixin(value = net.minecraft.enchantment.ProtectionEnchantment.class)
public abstract class ProtectionEnchantmentMixin {
    @Inject(method = "getProtectionAmount", at = @At("HEAD"), cancellable = true)
    public void reduceProtectionWhenPen(int level, DamageSource source, CallbackInfoReturnable<Integer> cir){
        if(source.getAttacker() instanceof LivingEntity evenbetterarcheology$entity){
            //check if the entity has the penetrating strike enchantment and return early if not
            int evenbetterarcheology$penetratingStrikeLevel = de.sakurajin.evenbetterarcheology.util.EnchantmentHelper.getPenetratingStrikeLevel(evenbetterarcheology$entity);
            if(evenbetterarcheology$penetratingStrikeLevel == 0){
                return;
            }

            //reduce the penetrating strike level by the effectiveness (rounding up)
            evenbetterarcheology$penetratingStrikeLevel = (int) Math.ceil(evenbetterarcheology$penetratingStrikeLevel*EvenBetterArcheology.CONFIG.PENETRATING_STRIKE_EFFECTIVENESS());

            //reduce the protection level by the penetrating strike level and make sure it is not negative
            int evenbetterarcheology$correctedLevel = Math.max(0, level - evenbetterarcheology$penetratingStrikeLevel);
            cir.setReturnValue(evenbetterarcheology$correctedLevel);
        }
    }
}
