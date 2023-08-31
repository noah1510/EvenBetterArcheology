package de.sakurajin.evenbetterarcheology.compatibility;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import net.fabricmc.loader.api.FabricLoader;

public class tectonic {
    public static void init(DatagenModContainer container) {
        if (
            !FabricLoader.getInstance().isModLoaded("tectonic") &&
            !FabricLoader.getInstance().isModLoaded("terratonic") &&
            !EvenBetterArcheology.CONFIG.ALWAYS_CREATE_TECTONIC_COMPATIBILITY()
        ) {return;}

        String biomePrefix = "worldgen/biome/";

        container.addTag(biomePrefix+"is_desert",
            "tectonic:desert_dunes",
            "tectonic:red_desert",
            "tectonic:red_desert_dunes"
        );

        container.addTag("minecraft:"+biomePrefix+"is_badlands", "tectonic:badlands_spires");
        container.addTag("minecraft:"+biomePrefix+"is_taiga", "tectonic:old_growth_snowy_taiga");

        container.addTag(biomePrefix+"is_cold_forest", "tectonic:old_growth_evergreen_forest");

        container.addTag(biomePrefix+"is_grassy",
                "tectonic:grasslands",
                "tectonic:evergreen_forest",
                "tectonic:autumn_forest"
        );

        container.addTag(biomePrefix + "is_flowery",
                "tectonic:alpine_meadow"
        );

        container.addTag(biomePrefix + "is_birch_forest", "tectonic:autumn_birch_forest");
    }
}
