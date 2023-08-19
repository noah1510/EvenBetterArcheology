package de.sakurajin.evenbetterarcheology.structures;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.StructureType;

public class ModStructureFeatures {
    public static StructureType<ModStructures> BETTERARCHEOLOGY_STRUCTURES;

    public static void registerStructureFeatures() {
        BETTERARCHEOLOGY_STRUCTURES = Registry.register(Registries.STRUCTURE_TYPE, new Identifier(EvenBetterArcheology.MOD_ID, "betterarcheology_structures"), () -> ModStructures.CODEC);
    }
}
