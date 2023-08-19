package de.sakurajin.evenbetterarcheology.villager;

import de.sakurajin.evenbetterarcheology.block.ModBlocks;
import de.sakurajin.evenbetterarcheology.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;

public class Archeologist_vanilla{
    public static void registerTrades() {
        TradeOfferHelper.registerVillagerOffers(ModVillagers.ARCHEOLOGIST, 1,
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

        TradeOfferHelper.registerVillagerOffers(ModVillagers.ARCHEOLOGIST, 2,
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

        TradeOfferHelper.registerVillagerOffers(ModVillagers.ARCHEOLOGIST, 3,
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

        TradeOfferHelper.registerVillagerOffers(ModVillagers.ARCHEOLOGIST, 4,
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

        TradeOfferHelper.registerVillagerOffers(ModVillagers.ARCHEOLOGIST, 5,
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

}
