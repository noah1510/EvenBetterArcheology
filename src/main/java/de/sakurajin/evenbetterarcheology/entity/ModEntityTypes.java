package de.sakurajin.evenbetterarcheology.entity;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntityTypes {
    public static final EntityType<BombEntity> BOMB_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(EvenBetterArcheology.DATA.MOD_ID, "bombentity"),
            FabricEntityTypeBuilder.<BombEntity>create(SpawnGroup.MISC, BombEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    public static void registerModEntityTypes() {
        EvenBetterArcheology.DATA.LOGGER.info("ModEntityTypes of " + EvenBetterArcheology.DATA.MOD_ID + " registered.");
    }
}
