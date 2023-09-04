package de.sakurajin.evenbetterarcheology.structures;

import de.sakurajin.evenbetterarcheology.EvenBetterArcheology;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.StructureType;

public class ModStructures {
    public static StructureType<ModStructure> EVENBETTERARCHEOLOGY_STRUCTURES;

    public static void registerStructureFeatures() {
        EVENBETTERARCHEOLOGY_STRUCTURES = Registry.register(Registries.STRUCTURE_TYPE, new Identifier(EvenBetterArcheology.DATA.MOD_ID, "evenbetterarcheology_structures"), () -> ModStructure.CODEC);
    }
}
