package de.sakurajin.evenbetterarcheology.registry;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import de.sakurajin.evenbetterarcheology.api.block.entity.BlockEntityWithInventory;
import io.wispforest.owo.network.ClientAccess;
import io.wispforest.owo.network.OwoNetChannel;
import io.wispforest.owo.network.ServerAccess;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class ModNetworking {
    public static final OwoNetChannel ARCHEOLOGY_CHANNEL = OwoNetChannel.create(EvenBetterArcheology.DATA.getSimpleID("main"));
    public record ItemUpdatePacket(BlockPos position, List<ItemStack> items) {}
    public record UpdateRequestPacket(BlockPos position) {}

    public static void registerC2SPackets() {
        EvenBetterArcheology.DATA.LOGGER.info("registering Server->Client handlers");

        ARCHEOLOGY_CHANNEL.registerClientboundDeferred(ItemUpdatePacket.class);
        ARCHEOLOGY_CHANNEL.registerServerbound(UpdateRequestPacket.class, (UpdateRequestPacket packet, ServerAccess serverAccess) -> {
            var entity = serverAccess.player().getWorld().getBlockEntity(packet.position());
            if(entity == null) return;

            if(entity instanceof BlockEntityWithInventory inventory){
                inventory.transferDataToClients(serverAccess.player());
            }
        });
    }

    public static void registerS2CPackets() {
        EvenBetterArcheology.DATA.LOGGER.info("registering Server->Client handlers");

        ARCHEOLOGY_CHANNEL.registerClientbound(ItemUpdatePacket.class, (ItemUpdatePacket packet, ClientAccess clientAccess) -> {
            var client = clientAccess.runtime();
            if(client.world == null) return;

            var entity = client.world.getBlockEntity(packet.position());

            if(entity instanceof BlockEntityWithInventory inventory){
                inventory.setInventory(DefaultedList.copyOf(ItemStack.EMPTY, packet.items().toArray(ItemStack[]::new)));
            }
        });
    }
}
