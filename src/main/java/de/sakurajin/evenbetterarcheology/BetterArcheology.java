package de.sakurajin.evenbetterarcheology;

import de.sakurajin.evenbetterarcheology.block.ModBlocks;
import de.sakurajin.evenbetterarcheology.block.entity.ModBlockEntities;
import de.sakurajin.evenbetterarcheology.entity.ModEntityTypes;
import de.sakurajin.evenbetterarcheology.item.ModItemGroup;
import de.sakurajin.evenbetterarcheology.item.ModItems;
import de.sakurajin.evenbetterarcheology.networking.ModMessages;
import de.sakurajin.evenbetterarcheology.screen.ModScreenHandlers;
import de.sakurajin.evenbetterarcheology.util.ModConfigs;
import de.sakurajin.evenbetterarcheology.villager.ModVillagers;
import de.sakurajin.evenbetterarcheology.enchantment.ModEnchantments;
import de.sakurajin.evenbetterarcheology.structures.ModStructureFeatures;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterArcheology implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("evenbetterarcheology");
	public static final String MOD_ID = "evenbetterarcheology";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Even Better Archeology says Hello");	//info message

		ModConfigs.registerConfigs();

		ModItemGroup.registerTab();		//creates CreativeModeTab
		ModItems.registerModItems();	//registers Items and adds them to the Tab
		ModBlocks.registerModBlocks();	//registers Blocks	and BlockItems
		ModEntityTypes.registerModEntityTypes();

		ModBlockEntities.registerBlockEntities(); //registers Block-Entities
		ModScreenHandlers.registerAllScreenHandlers(); //registers all Screen-Handlers

		ModVillagers.registerVillagers(); //registers all Villagers from BetterArcheology
		ModVillagers.registerTrades(); //registers all Villager Trades

		ModMessages.registerC2SPackets();
		ModEnchantments.registerModEnchantments();

		ModStructureFeatures.registerStructureFeatures();
	}
}