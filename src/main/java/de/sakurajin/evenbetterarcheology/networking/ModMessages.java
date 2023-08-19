package de.sakurajin.evenbetterarcheology.networking;

import de.sakurajin.evenbetterarcheology.BetterArcheology;
import de.sakurajin.evenbetterarcheology.networking.packet.ItemStackSyncS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier ITEM_SYNC = new Identifier(BetterArcheology.MOD_ID, "item_sync");

    public static void registerC2SPackets() {
    }

    public static void registerS2CPackets() {
        BetterArcheology.LOGGER.info("registering Server->Client packages");
        ClientPlayNetworking.registerGlobalReceiver(ITEM_SYNC, ItemStackSyncS2CPacket::receive);
    }
}
