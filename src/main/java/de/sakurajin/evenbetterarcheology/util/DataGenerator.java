package de.sakurajin.evenbetterarcheology.util;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.api.item.BetterBrushItem;
import de.sakurajin.evenbetterarcheology.item.ModItems;
import de.sakurajin.evenbetterarcheology.api.datagen.resourceGenerationHelper;

public class DataGenerator{

    private static final resourceGenerationHelper genHelper = new resourceGenerationHelper(EvenBetterArcheology.MOD_ID, EvenBetterArcheology.RESOURCE_PACK);
    public static void generate() {
        ((BetterBrushItem)ModItems.IRON_BRUSH).generateResourceData(EvenBetterArcheology.RESOURCE_PACK);
        ((BetterBrushItem)ModItems.DIAMOND_BRUSH).generateResourceData(EvenBetterArcheology.RESOURCE_PACK);

        genHelper.generateCubeColumn("rotten_log", "rotten_log/rotten_log_top", "rotten_log/rotten_log");
        genHelper.generateCube("rotten_planks","rotten_log/rotten_planks");
        genHelper.generateStairs("rotten_stairs", "rotten_log/rotten_planks");
        genHelper.generateSlab("rotten_slab", "rotten_log/rotten_planks");

        genHelper.generateCube("cracked_mud_bricks","cracked_mud_bricks");
        genHelper.generateStairs("cracked_mud_brick_stairs", "cracked_mud_bricks");
        genHelper.generateSlab("cracked_mud_brick_slab", "cracked_mud_bricks");
        genHelper.generateBlockAndItemFromParent("infested_mud_bricks", "minecraft:block/mud_bricks");
    }

}
