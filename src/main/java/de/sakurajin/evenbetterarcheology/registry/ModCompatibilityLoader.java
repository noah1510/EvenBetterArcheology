package de.sakurajin.evenbetterarcheology.registry;

import de.sakurajin.evenbetterarcheology.compatibility.ArtifactsCompat;
import de.sakurajin.evenbetterarcheology.compatibility.DungeonsAndTavernsCompat;
import de.sakurajin.evenbetterarcheology.compatibility.LevelZCompat;

/**
 * This class handles loading all compatibility stuff.
 * It is called from the main mod class.
 */
public class ModCompatibilityLoader {
    public static void init(){
        //load all compatibility stuff
        LevelZCompat.init();
        DungeonsAndTavernsCompat.init();
        ArtifactsCompat.init();
    }
}
