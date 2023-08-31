package de.sakurajin.evenbetterarcheology;

import de.sakurajin.evenbetterarcheology.block.ModBlocks;
import de.sakurajin.evenbetterarcheology.block.entity.ModBlockEntities;
import de.sakurajin.evenbetterarcheology.block.entity.client.ArcheologyTableBlockEntityRenderer;
import de.sakurajin.evenbetterarcheology.block.entity.client.SusBlockEntityRenderer;
import de.sakurajin.evenbetterarcheology.block.entity.client.VillagerFossilBlockEntityRenderer;
import de.sakurajin.evenbetterarcheology.entity.ModEntityTypes;
import de.sakurajin.evenbetterarcheology.networking.ModMessages;
import de.sakurajin.evenbetterarcheology.screen.FossilInventoryScreen;
import de.sakurajin.evenbetterarcheology.screen.IdentifyingScreen;
import de.sakurajin.evenbetterarcheology.screen.ModScreenHandlers;
import io.wispforest.owo.config.ConfigSynchronizer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class EvenBetterArcheologyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();

        HandledScreens.register(ModScreenHandlers.IDENTIFYING_SCREEN_HANDLER, IdentifyingScreen::new);
        HandledScreens.register(ModScreenHandlers.FOSSIL_SCREEN_HANDLER, FossilInventoryScreen::new);

        BlockEntityRendererFactories.register(ModBlockEntities.ARCHEOLOGY_TABLE, ArcheologyTableBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.VILLAGER_FOSSIL, VillagerFossilBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.SUSBLOCK, SusBlockEntityRenderer::new);

        EntityRendererRegistry.register(ModEntityTypes.BOMB_ENTITY, FlyingItemEntityRenderer::new);

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
