package de.sakurajin.evenbetterarcheology;

import de.sakurajin.evenbetterarcheology.api.block.entity.BlockEntityWithInventory;
import de.sakurajin.evenbetterarcheology.block.entity.ArcheologyTableBlockEntity;
import de.sakurajin.evenbetterarcheology.block.fossils.blockEntity.VillagerFossilBlockEntity;
import de.sakurajin.evenbetterarcheology.registry.ModBlocks;
import de.sakurajin.evenbetterarcheology.registry.ModBlockEntities;
import de.sakurajin.evenbetterarcheology.block.entity.client.ArcheologyTableBlockEntityRenderer;
import de.sakurajin.evenbetterarcheology.block.entity.client.SusBlockEntityRenderer;
import de.sakurajin.evenbetterarcheology.block.entity.client.VillagerFossilBlockEntityRenderer;
import de.sakurajin.evenbetterarcheology.registry.ModEntityTypes;
import de.sakurajin.evenbetterarcheology.registry.ModNetworking;
import de.sakurajin.evenbetterarcheology.screen.FossilInventoryScreen;
import de.sakurajin.evenbetterarcheology.screen.IdentifyingScreen;
import de.sakurajin.evenbetterarcheology.registry.ModScreenHandlers;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientBlockEntityEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.world.ClientWorld;

public class EvenBetterArcheologyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModNetworking.registerS2CPackets();

        HandledScreens.register(ModScreenHandlers.IDENTIFYING_SCREEN_HANDLER, IdentifyingScreen::new);
        HandledScreens.register(ModScreenHandlers.FOSSIL_SCREEN_HANDLER, FossilInventoryScreen::new);

        BlockEntityRendererFactories.register(ModBlockEntities.ARCHEOLOGY_TABLE, ArcheologyTableBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.VILLAGER_FOSSIL, VillagerFossilBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.SUSBLOCK, SusBlockEntityRenderer::new);

        EntityRendererRegistry.register(ModEntityTypes.BOMB_ENTITY, FlyingItemEntityRenderer::new);

        //handle loading the block entities
        ClientBlockEntityEvents.BLOCK_ENTITY_LOAD.register((BlockEntity blockEntity, ClientWorld world) -> {
            if(blockEntity instanceof BlockEntityWithInventory inventoryEntity){
                inventoryEntity.requestDataTransfer();
            }
        });

        //RENDERING
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROTTEN_WOOD_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROTTEN_WOOD_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VILLAGER_FOSSIL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VILLAGER_FOSSIL_BODY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OCELOT_FOSSIL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OCELOT_FOSSIL_BODY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OCELOT_FOSSIL_HEAD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SHEEP_FOSSIL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SHEEP_FOSSIL_BODY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SHEEP_FOSSIL_HEAD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHICKEN_FOSSIL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHICKEN_FOSSIL_HEAD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHICKEN_FOSSIL_BODY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CREEPER_FOSSIL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CREEPER_FOSSIL_BODY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CREEPER_FOSSIL_HEAD, RenderLayer.getCutout());
    }
}
