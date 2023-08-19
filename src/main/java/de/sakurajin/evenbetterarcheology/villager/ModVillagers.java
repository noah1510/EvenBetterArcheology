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
        TradeOfferHelper.registerVillagerOffers(ARCHEOLOGIST, 1,
                factories -> {
                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(ModBlocks.ROTTEN_PLANKS, 6),
                            10, 2, 0.02f                                                                                     //max uses, experience, price Multiplier
                    )));

                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 4),
                            new ItemStack(Items.BRUSH, 1),
                            4, 5, 0.02f                                                                                     //max uses, experience, price Multiplier
                    )));

                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.BONE, 16),
                            new ItemStack(Items.EMERALD, 1),
                            16, 20, 0.02f                                                                                     //max uses, experience, price Multiplier
                    )));
                });

        TradeOfferHelper.registerVillagerOffers(ARCHEOLOGIST, 2,
                factories -> {
                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(Blocks.MUD_BRICKS),
                            14, 5, 0.02f
                    )));

                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(Blocks.LANTERN),
                            12, 10, 0.02f
                    )));
                });

        TradeOfferHelper.registerVillagerOffers(ARCHEOLOGIST, 3,
                factories -> {
                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 4),
                            new ItemStack(Blocks.COBWEB),
                            10, 5, 0.02f
                    )));

                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 8),
                            new ItemStack(ModItems.IRON_BRUSH),
                            4, 10, 0.03f
                    )));
                });

        TradeOfferHelper.registerVillagerOffers(ARCHEOLOGIST, 4,
                factories -> {
                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 8),
                            new ItemStack(ModBlocks.VASE_CREEPER, 1),
                            8, 10, 0.025f
                    )));

                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 8),
                            new ItemStack(ModBlocks.VASE, 1),
                            8, 10, 0.025f
                    )));
                });

        TradeOfferHelper.registerVillagerOffers(ARCHEOLOGIST, 5,
                factories -> {
                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 16),
                            new ItemStack(ModItems.DIAMOND_BRUSH, 1),
                            4, 10, 0.03f
                    )));

                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 32),
                            new ItemStack(ModItems.ARTIFACT_SHARDS, 1),
                            3, 30, 0.1f
                    )));

                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 4),
                            new ItemStack(Items.SPYGLASS, 1),
                            8, 10, 0.02f
                    )));

                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 8),
                            new ItemStack(ModItems.BOMB_ITEM, 1),
                            3, 10, 0.05f
                    )));
                });
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
