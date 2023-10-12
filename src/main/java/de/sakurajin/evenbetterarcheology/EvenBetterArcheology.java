package de.sakurajin.evenbetterarcheology;

import de.sakurajin.evenbetterarcheology.api.block.FossilCraftableWithEntity;
import de.sakurajin.evenbetterarcheology.api.block.entity.BlockEntityWithInventory;
import de.sakurajin.evenbetterarcheology.block.custom.ArchelogyTable;
import de.sakurajin.evenbetterarcheology.block.fossils.SheepFossilFull;
import de.sakurajin.sakuralib.datagen.v1.DatagenModContainer;
import de.sakurajin.evenbetterarcheology.registry.*;
import de.sakurajin.evenbetterarcheology.structures.StructureDataGenerator;
import de.sakurajin.evenbetterarcheology.util.evenbetterarcheologyConfig;

import io.wispforest.owo.itemgroup.Icon;
import net.devtech.arrp.api.RRPCallback;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerBlockEntityEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

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

		ModNetworking.registerC2SPackets();
		ModEnchantments.registerModEnchantments();

		ModStructures.registerStructureFeatures();

		//Block interactions
		registerBlockInteractions();

		var structureData = new StructureDataGenerator(DATA);
		PatchouliBookGeneration.generateBook();

		//load all compatibility stuff
		ModCompatibilityLoader.init();

		//generate all tags
		DATA.registerAllTags();

		if (FabricLoader.getInstance().isDevelopmentEnvironment()){
			DATA.RESOURCE_PACK.dump();
		}
	}

	private void registerBlockInteractions(){
		//handle using blocks
		UseBlockCallback.EVENT.register((PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) -> {
			if (!player.isSpectator()) {
				BlockState state = world.getBlockState(hitResult.getBlockPos());

				if(state.getBlock() instanceof FossilCraftableWithEntity || state.getBlock() instanceof ArchelogyTable){
					var blockEntity = world.getBlockEntity(hitResult.getBlockPos());
					if(blockEntity instanceof NamedScreenHandlerFactory screenHandlerFactory){
						player.openHandledScreen(screenHandlerFactory);
						return ActionResult.SUCCESS;
					}
				}

				if(state.getBlock() instanceof SheepFossilFull sheepFossil){
					return sheepFossil.playSound(state, world, hitResult.getBlockPos());
				}
			}
			return ActionResult.PASS;
		});

		//handle replacing blocks
		PlayerBlockBreakEvents.AFTER.register((World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) -> {
			if(blockEntity instanceof BlockEntityWithInventory inventoryEntity){
				ItemScatterer.spawn(world, pos, inventoryEntity);
				world.updateComparators(pos, state.getBlock());
			}
		});

		//handle loading the block entities
		ServerBlockEntityEvents.BLOCK_ENTITY_LOAD.register((BlockEntity blockEntity, ServerWorld world) -> {
			if(blockEntity instanceof BlockEntityWithInventory inventoryEntity) {
				inventoryEntity.transferDataToClients();
			}
		});
	}
}