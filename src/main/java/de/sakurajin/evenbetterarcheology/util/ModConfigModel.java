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


    /*private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("artifact.enchantments.enabled", true), "Set to true or false to enable or disable effects");
        configs.addKeyValuePair(new Pair<>("penetrating.strike.protection.ignorance", 0.33f), "Set to % of damage-reduction from Protection Enchantments that should be ignored, keep in range of 0-1.00");
        configs.addKeyValuePair(new Pair<>("soaring.winds.boost", 0.5f), "Set to movement speed multiplier, that should be applied when starting to fly");
        configs.addKeyValuePair(new Pair<>("soaring.winds.maxlevel", 1), "Set the maximum level for soaring winds");
        configs.addKeyValuePair(new Pair<>("ocelot.fossil.flee.range", 20), "Range in Block that the Fossil scares Creepers away");
    }

    private static void assignConfigs() {
        ARTIFACT_ENCHANTMENTS_ENABLED = CONFIG.getOrDefault("artifact.enchantments.enabled", true);
        PENETRATING_STRIKE_PROTECTION_IGNORANCE = (float) CONFIG.getOrDefault("penetrating.strike.protection.ignorance", 0.33f);
        SOARING_WINDS_BOOST = (float) CONFIG.getOrDefault("soaring.winds.boost", 0.3f);
        SOARING_WINDS_MAXLEVEL = CONFIG.getOrDefault("soaring.winds.maxlevel", 1);
        OCELOT_FOSSIL_FLEE_RANGE = CONFIG.getOrDefault("ocelot.fossil.flee.range", 20);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }*/
}