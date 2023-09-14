package de.sakurajin.evenbetterarcheology;

import de.sakurajin.sakuralib.util.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.compatibility.LevelZCompat;
import de.sakurajin.evenbetterarcheology.registry.*;
import de.sakurajin.evenbetterarcheology.structures.StructureDataGenerator;
import de.sakurajin.evenbetterarcheology.util.evenbetterarcheologyConfig;
import io.wispforest.owo.itemgroup.Icon;
import net.devtech.arrp.api.RRPCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class EvenBetterArcheology implements ModInitializer {
	public static final DatagenModContainer DATA = new DatagenModContainer(
			"evenbetterarcheology",
			() -> Icon.of(ModItems.UNIDENTIFIED_ARTIFACT),
			RRPCallback.BETWEEN_MODS_AND_USER
	);

	public static final evenbetterarcheologyConfig CONFIG = evenbetterarcheologyConfig.createAndLoad();

	@Override
	public void onInitialize() {
		DATA.LOGGER.info("Even Better Archeology says Hello");	//info message
        if (DATA.GROUP == null) {
			DATA.LOGGER.error("Something went wrong when creating the item group");
			throw new RuntimeException("Something went wrong when creating the item group");
		}

        DATA.GROUP.initialize(); //initializes the ItemGroup

		DATA.registerContainer(ModItems.class, false);	//registers Items and adds them to the Tab
		DATA.registerContainer(ModBlocks.class, false);	//registers Blocks	and BlockItems

		ModEntityTypes.registerModEntityTypes();

		ModBlockEntities.registerBlockEntities(); //registers Block-Entities
		ModScreenHandlers.registerAllScreenHandlers(); //registers all Screen-Handlers

		ModVillagers.registerVillagers(); //registers all Villagers from BetterArcheology
		ModVillagers.registerTrades(); //registers all Villager Trades

		ModMessages.registerC2SPackets();
		ModEnchantments.registerModEnchantments();

		ModStructures.registerStructureFeatures();

		var structureData = new StructureDataGenerator(DATA);

		//load all compatibility stuff
		LevelZCompat.init(DATA);

		//generate all tags
		DATA.registerAllTags();

		if (FabricLoader.getInstance().isDevelopmentEnvironment()){
			DATA.RESOURCE_PACK.dump();
		}
	}
}