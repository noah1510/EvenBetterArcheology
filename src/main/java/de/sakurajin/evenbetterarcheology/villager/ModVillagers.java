package de.sakurajin.evenbetterarcheology.villager;

import com.google.common.collect.ImmutableSet;
import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.block.ModBlocks;
import de.sakurajin.evenbetterarcheology.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class ModVillagers {
    //ENTRIES--------------------------------------------------------------------//
    public static final PointOfInterestType ARCHEOLOGY_TABLE_POI = registerPOI("archeology_table_poi", ModBlocks.ARCHEOLOGY_TABLE);
    public static final VillagerProfession ARCHEOLOGIST = registerProfession("archeologist",
            RegistryKey.of(Registries.POINT_OF_INTEREST_TYPE.getKey(), new Identifier(EvenBetterArcheology.MOD_ID, "archeology_table_poi")));

    //TRADES---------------------------------------------------------------------//
    public static void registerTrades() {
        Archeologist_vanilla.registerTrades();
    }

    //REGISTRATION---------------------------------------------------------------//
    public static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, new Identifier(EvenBetterArcheology.MOD_ID, name),
                VillagerProfessionBuilder.create().id(new Identifier(EvenBetterArcheology.MOD_ID, name)).workstation(type)
                        .workSound(SoundEvents.ITEM_BRUSH_BRUSHING_SAND).build());                                            //TODO: Sound change later?
    }

    public static PointOfInterestType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(new Identifier(EvenBetterArcheology.MOD_ID, name),
                1, 1, ImmutableSet.copyOf(block.getStateManager().getStates()));
    }

    //REGISTRATION---------------------------------------------------------------//
    public static void registerVillagers() {
        EvenBetterArcheology.LOGGER.debug("Registering Villagers for " + EvenBetterArcheology.MOD_ID);
    }
}
