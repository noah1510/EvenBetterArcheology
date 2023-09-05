package de.sakurajin.evenbetterarcheology.compatibility;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import net.fabricmc.loader.api.FabricLoader;
import net.levelz.stats.Skill;
import net.minecraft.util.Pair;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class LevelZ {

    //private static Skill ARCHEOLOGY;
    public static void init(DatagenModContainer container) {
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
