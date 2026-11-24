package de.sakurajin.evenbetterarcheology.datagen;

import de.sakurajin.sakuralib.datagen.v3.SakuraDataGeneratorEntrypoint;
import de.sakurajin.sakuralib.datagen.v3.SakuraDatagenPack;
import org.jetbrains.annotations.NotNull;

/**
 * Build-time Fabric Data Generation entrypoint for Even Better Archeology.
 *
 * <p>Phase 0 sets up the entrypoint and build wiring without migrating any
 * runtime ARRP providers yet; build-time providers will be registered in
 * {@link #configurePack(SakuraDatagenPack)} in Phases 1–3.</p>
 */
public class EvenBetterArcheologyDataGenerator extends SakuraDataGeneratorEntrypoint {

    @Override
    protected void configurePack(@NotNull SakuraDatagenPack pack) {
        // Phase 0: intentionally empty until Phase 1+ providers are migrated.
    }
}
