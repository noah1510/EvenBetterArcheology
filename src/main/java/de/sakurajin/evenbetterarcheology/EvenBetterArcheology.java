package de.sakurajin.evenbetterarcheology;

import de.sakurajin.evenbetterarcheology.block.ModBlocks;
import de.sakurajin.evenbetterarcheology.block.entity.ModBlockEntities;
import de.sakurajin.evenbetterarcheology.entity.ModEntityTypes;
import de.sakurajin.evenbetterarcheology.item.ModItems;
import de.sakurajin.evenbetterarcheology.networking.ModMessages;
import de.sakurajin.evenbetterarcheology.screen.ModScreenHandlers;
import de.sakurajin.evenbetterarcheology.util.DataGenerator;
import de.sakurajin.evenbetterarcheology.util.evenbetterarcheologyConfig;
import de.sakurajin.evenbetterarcheology.villager.ModVillagers;
import de.sakurajin.evenbetterarcheology.enchantment.ModEnchantments;
import de.sakurajin.evenbetterarcheology.structures.ModStructureFeatures;
import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.api.RRPCallback;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EvenBetterArcheology implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("evenbetterarcheology");
	public static final String MOD_ID = "evenbetterarcheology";
	public static final evenbetterarcheologyConfig CONFIG = evenbetterarcheologyConfig.createAndLoad();

	public static final OwoItemGroup GROUP = OwoItemGroup
			.builder(new Identifier(MOD_ID, MOD_ID), () -> Icon.of(ModItems.UNIDENTIFIED_ARTIFACT))
			// additional builder configuration goes between these lines
			.build();

	public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create(MOD_ID+":resources");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Even Better Archeology says Hello");	//info message
		GROUP.initialize(); //initializes the ItemGroup

		FieldRegistrationHandler.register(ModItems.class, MOD_ID, false);	//registers Items and adds them to the Tab
		FieldRegistrationHandler.register(ModBlocks.class, MOD_ID, false);	//registers Blocks	and BlockItems
		ModEntityTypes.registerModEntityTypes();

		ModBlockEntities.registerBlockEntities(); //registers Block-Entities
		ModScreenHandlers.registerAllScreenHandlers(); //registers all Screen-Handlers

		ModVillagers.registerVillagers(); //registers all Villagers from BetterArcheology
		ModVillagers.registerTrades(); //registers all Villager Trades

		ModMessages.registerC2SPackets();
		ModEnchantments.registerModEnchantments();

		ModStructureFeatures.registerStructureFeatures();

		//Generate the data for the resource pack
		DataGenerator.generate();

		//Register the resource pack
		RRPCallback.AFTER_VANILLA.register(a -> a.add(RESOURCE_PACK));
	}
}