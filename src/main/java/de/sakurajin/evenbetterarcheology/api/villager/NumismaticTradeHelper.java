package de.sakurajin.evenbetterarcheology.api.villager;

import com.glisco.numismaticoverhaul.currency.CurrencyHelper;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

import java.util.ArrayList;

/**
 * A helper class to create trades for villagers.
 * It will automatically use currency instead of emeralds if numismatic-overhaul is present.
 * The prices can either be set using emeralds or currency and will be converted automatically.
 * The use of currency is recommended as it will automatically scale with the currency value and will
 * automatically use emerald blocks if the price is higher than 64 emeralds.
 * The TradeParameters class is used to create all the individual trades.
 * The registerTrades method can be used to register all trades for a profession at once using a list of TradeParameters.
 *
 * @see TradeParameters
 */
public class NumismaticTradeHelper {
    private static final boolean hasNumismatic = FabricLoader.getInstance().isModLoaded("numismatic-overhaul");

    /**
     * A class to store all the parameters for a trade.
     */
    public static class TradeParameters{
        public int price = 125;
        public int emeraldAmount = 1;
        public ItemConvertible item = Items.EMERALD;
        public int amount = 1;
        public int maxUses = 16;
        public int merchantExperience = 1;
        public float priceMultiplier = 0.02f;
        public boolean sell = false;
        public int tradeLevel = 1;

        /**
         * @param price The price in currency (e.g. 80 for 80 bronze coins or 200 for 2 silver coins)
         * @param emeraldAmount The amount of emeralds to be traded when numismatic-overhaul is not present
         * @param item The item to be traded
         * @param amount The amount of items to be traded
         * @param maxUses The maximum number of times this trade can be used
         * @param merchantExperience The experience given to the player when using this trade
         * @param priceMultiplier The price multiplier for this trade
         * @param sell Whether the item is sold or bought by the villager
         */
        public TradeParameters(int price, int emeraldAmount, ItemConvertible item, int amount, int maxUses, int merchantExperience, float priceMultiplier, boolean sell, int tradeLevel) {
            this.price = price;
            this.emeraldAmount = emeraldAmount;
            this.item = item;
            this.amount = amount;
            this.maxUses = maxUses;
            this.merchantExperience = merchantExperience;
            this.priceMultiplier = priceMultiplier;
            this.sell = sell;
            this.tradeLevel = tradeLevel;
        }

        /**
         * This is the constructor you should probably use.
         * It will automatically calculate the emerald amount, merchant experience and price multiplier based on the price.
         * @param price The price in currency (e.g. 80 for 80 bronze coins or 200 for 2 silver coins)
         * @param item The item to be traded
         * @param amount The amount of items to be traded
         * @param maxUses The maximum number of times this trade can be used
         * @param sell Whether the item is sold or bought by the villager
         * @param tradeLevel The level at which this trade should be unlocked
         */
        public TradeParameters(int price, ItemConvertible item, int amount, int maxUses, boolean sell, int tradeLevel) {
            this.price = price;
            this.item = item;
            this.amount = amount;
            this.maxUses = maxUses;
            this.sell = sell;
            this.tradeLevel = tradeLevel;
            this.emeraldAmount = emeraldsFromCurrency(price);
            this.merchantExperience = (int) Math.floor(price / 100f) + tradeLevel;
            this.priceMultiplier = 0.01f + 0.0025f * ((float) price / 250);
        }

        /**
         * A constructor to create a trade with the default values.
         * They can be changed afterward.
         */
        public TradeParameters(){}
    }

    /**
     * Creates a trade offer for a villager
     * If numismatic-overhaul is present currency will be used instead of emeralds.
     * The amount of emeralds may be larger than 64, in which case emerald blocks will be used.
     * @param parameters The TradeParameters for the trade
     * @return The trade offer to be added to the factory
     */
    public static TradeOffer createOffer(TradeParameters parameters) {
        ItemStack currency;
        if(hasNumismatic) {
            currency = CurrencyHelper.getClosest(parameters.price);
        }else{
            currency = getEmeraldStack(parameters.emeraldAmount);
        }
        ItemStack stack = new ItemStack(parameters.item, parameters.amount);
        if(parameters.sell){
            return new TradeOffer(currency, stack, parameters.maxUses, parameters.merchantExperience, parameters.priceMultiplier);
        }else{
            return new TradeOffer(stack, currency, parameters.maxUses, parameters.merchantExperience, parameters.priceMultiplier);
        }
    }

    /**
     * Register all trades for a profession from a list of trade parameters
     * @param profession The profession to register the trade for
     * @param trades A list with all trades the villager should have
     */
    public static void registerTrades(VillagerProfession profession, ArrayList<TradeParameters> trades){
        for(int level = 1; level <= 5; level++) {
            final int currentLevel = level;
            ArrayList<TradeOffer> offers = trades.stream().filter(t -> t.tradeLevel == currentLevel).collect(ArrayList::new, (list, item) -> list.add(createOffer(item)), ArrayList::addAll);
            TradeOfferHelper.registerVillagerOffers(profession, currentLevel, factories -> offers.forEach(tradeOffer -> factories.add((entity, random) -> tradeOffer)));
        }
    }

    /**
     * Convert a currency amount to an amount of emeralds
     * @param currency the currency amount
     * @return the amount of emeralds
     */
    public static int emeraldsFromCurrency(int currency){
        return (int) Math.ceil(currency / 125.0);
    }

    /**
     * Convert an amount of emeralds to an emerald stack
     * If the trade cost is higher than 64 emeralds, emerald blocks will be used.
     * @param amount the amount of emeralds
     * @return the ItemStack containing the emeralds
     */
    public static ItemStack getEmeraldStack(int amount){
        if (amount >= 64){
            return new ItemStack(Items.EMERALD_BLOCK, amount / 9);
        }else{
            return new ItemStack(Items.EMERALD, amount);
        }
    }
}
