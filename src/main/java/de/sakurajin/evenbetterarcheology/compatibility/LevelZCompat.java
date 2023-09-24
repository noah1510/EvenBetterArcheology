package de.sakurajin.evenbetterarcheology.compatibility;

import de.sakurajin.sakuralib.datagen.v1.DatagenModContainer;
import net.fabricmc.loader.api.FabricLoader;

/**
 * This class loads all compatibility with levelz.
 * At the moment it does nothing.
 * In the future it will add the archeology skill to the levelz mod.
 * This will also set the level requirements for block interaction and use.
 */
public class LevelZCompat {

    //private static Skill ARCHEOLOGY;
    public static void init() {
        if (!FabricLoader.getInstance().isModLoaded("levelz")) {
            return;
        }


        //construct the skill using reflection
        /*try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodHandle skillConstructor = lookup.unreflectConstructor(Skill.class.getDeclaredConstructor(int.class, String.class));
            ARCHEOLOGY = (Skill) skillConstructor.invoke(0, "archeology");
        }catch (Exception e) {
            container.LOGGER.error("Could not find Skill constructor", e);
        }catch (Throwable throwable) {
            container.LOGGER.error("Could not invoke Skill constructor", throwable);
        }*/

    }
}
