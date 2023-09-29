package de.sakurajin.evenbetterarcheology.util;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.*;

@Modmenu(modId = "evenbetterarcheology")
@Config(name = "even_better_archeology", wrapperName = "evenbetterarcheologyConfig")
@Sync(Option.SyncMode.OVERRIDE_CLIENT)
public class ModConfigModel {
    @SectionHeader("enchantments")
    public boolean ARTIFACT_ENCHANTMENTS_ENABLED = true;
    @RangeConstraint(min = 0.1f, max = 2.00f)
    public float PENETRATING_STRIKE_EFFECTIVENESS = 1.0f;

    @RangeConstraint(min = 1, max = 10)
    public int PENETRATING_STRIKE_MAXLEVEL = 3;

    @RangeConstraint(min = 0.1f, max = 1.0f)
    public float SOARING_WINDS_BOOST = 0.3f;

    @RangeConstraint(min = 1, max = 5)
    public int SOARING_WINDS_MAXLEVEL = 3;

    @SectionHeader("blocks")
    @RangeConstraint(min = 1, max = 100)
    public int OCELOT_FOSSIL_FLEE_RANGE = 20;

    //@SectionHeader("worldgen")
    @Nest
    @Expanded
    public ModCompatibilityOptions COMPATIBILITY_OPTIONS = new ModCompatibilityOptions();

    public static class ModCompatibilityOptions{
        @SectionHeader("artifacts")
        public boolean ARCHEOLOGY_TABLE_ARTIFACTS_LOOT = true;

        public int ARCHEOLOGY_TABLE_ARTIFACTS_LOOT_WEIGHT = 15;

        public int ARCHEOLOGY_TABLE_ARTIFACTS_LOOT_QUALITY = 2;
    }
}