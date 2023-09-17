package de.sakurajin.evenbetterarcheology.registry;

import com.google.common.collect.ImmutableSet;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;

import de.sakurajin.sakuralib.villager.v1.TradeHelper;
import de.sakurajin.sakuralib.villager.v1.TradeParameters;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.ArrayList;

public class ModVillagers {
    //ENTRIES--------------------------------------------------------------------//
    public static final PointOfInterestType ARCHEOLOGY_TABLE_POI = registerPOI("archeology_table_poi", ModBlocks.ARCHEOLOGY_TABLE);
    public static final VillagerProfession ARCHEOLOGIST = registerProfession("archeologist",
            RegistryKey.of(Registries.POINT_OF_INTEREST_TYPE.getKey(), new Identifier(EvenBetterArcheology.DATA.MOD_ID, "archeology_table_poi")));

    //TRADES---------------------------------------------------------------------//
    public static void registerTrades() {
        ArrayList<TradeParameters> trades = new ArrayList<>();

        // level 1 trades
        trades.add(new TradeParameters(
                80,
                ModBlocks.ROTTEN_WOOD_PLANKS,
                4,
                16,
                true,
                1
        ));
        trades.add(new TradeParameters(
                250,
                Items.BRUSH,
                1,
                4,
                true,
                1
        ));
        trades.add(new TradeParameters(
                150,
                Items.BONE,
                16,
                16,
                false,
                1
        ));

        //level 2 trades
        trades.add(new TradeParameters(
                150,
                Blocks.MUD_BRICKS,
                1,
                16,
                true,
                2
        ));
        trades.add(new TradeParameters(
                400,
                Blocks.LANTERN,
                1,
                12,
                true,
                2
        ));
        trades.add(new TradeParameters(
                250,
                Items.TORCHFLOWER,
                1,
                16,
                false,
                2
        ));

        //level 3 trades
        trades.add(new TradeParameters(
                500,
                Items.COBWEB,
                1,
                8,
                true,
                3
        ));
        trades.add(new TradeParameters(
                1000,
                ModItems.IRON_BRUSH,
                1,
                4,
                true,
                3
        ));
        trades.add(new TradeParameters(
                250,
                Items.SPYGLASS,
                1,
                16,
                false,
                3
        ));

        //level 4 trades
        trades.add(new TradeParameters(
                1000,
                ModBlocks.VASE_CREEPER,
                1,
                8,
                true,
                4
        ));
        trades.add(new TradeParameters(
                1000,
                ModBlocks.VASE,
                1,
                8,
                true,
                4
        ));
        trades.add(new TradeParameters(
                1000,
                ModItems.BOMB_ITEM,
                1,
                4,
                true,
                4
        ));
        trades.add(new TradeParameters(
                1000,
                Items.SNIFFER_EGG,
                1,
                4,
                false,
                4
        ));

        //level 5 trades
        trades.add(new TradeParameters(
                2000,
                ModItems.DIAMOND_BRUSH,
                1,
                4,
                true,
                5
        ));
        trades.add(new TradeParameters(
                5000,
                ModItems.ARTIFACT_SHARDS,
                1,
                4,
                true,
                5
        ));

        TradeHelper.registerTrades(ModVillagers.ARCHEOLOGIST, trades);
    }

    //REGISTRATION---------------------------------------------------------------//
    public static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> workstation) {
        Identifier villagerID = new Identifier(EvenBetterArcheology.DATA.MOD_ID, name);

        return Registry.register(
            Registries.VILLAGER_PROFESSION,
            villagerID,
            new VillagerProfession(
                villagerID.toString(),
                (entry) -> entry.matchesKey(workstation),
                (entry) -> entry.matchesKey(workstation),
                ImmutableSet.of(),
                ImmutableSet.of(),
                SoundEvents.ITEM_BRUSH_BRUSHING_SAND
            )
        );
    }

    public static PointOfInterestType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(new Identifier(EvenBetterArcheology.DATA.MOD_ID, name),
                1, 1, ImmutableSet.copyOf(block.getStateManager().getStates()));
    }

    //REGISTRATION---------------------------------------------------------------//
    public static void registerVillagers() {
        EvenBetterArcheology.DATA.LOGGER.debug("Registering Villagers for " + EvenBetterArcheology.DATA.MOD_ID);
    }
}
