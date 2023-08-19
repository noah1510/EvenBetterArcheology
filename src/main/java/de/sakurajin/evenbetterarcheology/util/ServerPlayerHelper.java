package de.sakurajin.evenbetterarcheology.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class ServerPlayerHelper {
    public static ServerPlayerEntity getServerPlayer(PlayerEntity player) {
        if (player instanceof ServerPlayerEntity) {
            return (ServerPlayerEntity) player;
        } else {
            return null;
        }
    }
}
