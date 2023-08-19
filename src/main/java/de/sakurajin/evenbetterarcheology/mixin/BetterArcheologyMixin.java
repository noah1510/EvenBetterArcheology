package de.sakurajin.evenbetterarcheology.mixin;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class BetterArcheologyMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		EvenBetterArcheology.LOGGER.info("BetterArcheology Mixin initialized, prepare for impact");
		EvenBetterArcheology.LOGGER.info("BetterArcheology Artifact Enchantments are " + (EvenBetterArcheology.CONFIG.ARTIFACT_ENCHANTMENTS_ENABLED() ? "enabled" : "disabled"));
	}
}
