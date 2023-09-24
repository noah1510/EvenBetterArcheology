package de.sakurajin.evenbetterarcheology.util;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.*;

@Modmenu(modId = "evenbetterarcheology")
@Config(name = "even_better_archeology", wrapperName = "evenbetterarcheologyConfig")
public class ModConfigModel {
    @SectionHeader("enchantments")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    @RestartRequired
    public boolean ARTIFACT_ENCHANTMENTS_ENABLED = true;
    @RangeConstraint(min = 0.1f, max = 2.00f)
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float PENETRATING_STRIKE_EFFECTIVENESS = 1.0f;

    @RangeConstraint(min = 1, max = 10)
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    @RestartRequired
    public int PENETRATING_STRIKE_MAXLEVEL = 3;

    @RangeConstraint(min = 0.1f, max = 1.0f)
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float SOARING_WINDS_BOOST = 0.3f;

    @RangeConstraint(min = 1, max = 5)
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    @RestartRequired
    public int SOARING_WINDS_MAXLEVEL = 3;

    @SectionHeader("blocks")
    @RangeConstraint(min = 1, max = 100)
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public int OCELOT_FOSSIL_FLEE_RANGE = 20;

    //@SectionHeader("worldgen")
    @Nest
    public ModCompatibilityOptions COMPATIBILITY_OPTIONS = new ModCompatibilityOptions();

    public static class ModCompatibilityOptions{
        @SectionHeader("artifacts")
        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        @RestartRequired
        public boolean ARCHEOLOGY_TABLE_ARTIFACTS_LOOT = true;

        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        @RestartRequired
        public int ARCHEOLOGY_TABLE_ARTIFACTS_LOOT_WEIGHT = 15;

        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        @RestartRequired
        public int ARCHEOLOGY_TABLE_ARTIFACTS_LOOT_QUALITY = 2;
    }
}