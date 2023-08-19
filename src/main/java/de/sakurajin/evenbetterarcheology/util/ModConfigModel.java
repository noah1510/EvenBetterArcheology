package de.sakurajin.evenbetterarcheology.util;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RangeConstraint;
import io.wispforest.owo.config.annotation.SectionHeader;

@Modmenu(modId = "evenbetterarcheology")
@Config(name = "even_better_archeology", wrapperName = "evenbetterarcheologyConfig")
public class ModConfigModel {
    @SectionHeader("enchantments")
    public boolean ARTIFACT_ENCHANTMENTS_ENABLED = true;
    @RangeConstraint(min = 0.01f, max = 1.00f)
    public float PENETRATING_STRIKE_PROTECTION_IGNORANCE = 0.33f;

    @RangeConstraint(min = 0.1f, max = 1.0f)
    public float SOARING_WINDS_BOOST = 0.3f;

    @RangeConstraint(min = 1, max = 5)
    public int SOARING_WINDS_MAXLEVEL = 3;

    @SectionHeader("blocks")
    @RangeConstraint(min = 1, max = 100)
    public int OCELOT_FOSSIL_FLEE_RANGE = 20;
}