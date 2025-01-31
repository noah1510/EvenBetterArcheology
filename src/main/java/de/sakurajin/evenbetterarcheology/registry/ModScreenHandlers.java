package de.sakurajin.evenbetterarcheology.registry;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.screen.FossilInventoryScreenHandler;
import de.sakurajin.evenbetterarcheology.screen.IdentifyingScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<IdentifyingScreenHandler>     IDENTIFYING_SCREEN_HANDLER = new ScreenHandlerType<>(IdentifyingScreenHandler::new, FeatureFlags.VANILLA_FEATURES);
    public static ScreenHandlerType<FossilInventoryScreenHandler> FOSSIL_SCREEN_HANDLER      = new ScreenHandlerType<>(FossilInventoryScreenHandler::new, FeatureFlags.VANILLA_FEATURES);

    public static void registerAllScreenHandlers(){
        Registry.register(Registries.SCREEN_HANDLER, EvenBetterArcheology.DATA.getSimpleID("archeology_table"), IDENTIFYING_SCREEN_HANDLER);
        Registry.register(Registries.SCREEN_HANDLER, EvenBetterArcheology.DATA.getSimpleID("fossil"), FOSSIL_SCREEN_HANDLER);
    }
}
