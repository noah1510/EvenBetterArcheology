package de.sakurajin.evenbetterarcheology.util;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.api.item.BetterBrushItem;
import de.sakurajin.evenbetterarcheology.item.ModItems;

public class DataGenerator{

    public static void generate() {
        ((BetterBrushItem)ModItems.IRON_BRUSH).generateResourceData(EvenBetterArcheology.RESOURCE_PACK);
        ((BetterBrushItem)ModItems.DIAMOND_BRUSH).generateResourceData(EvenBetterArcheology.RESOURCE_PACK);
    }

}
