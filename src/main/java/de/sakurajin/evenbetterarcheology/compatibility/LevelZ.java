package de.sakurajin.evenbetterarcheology.compatibility;

import de.sakurajin.evenbetterarcheology.api.DatagenEngine.DatagenModContainer;
import net.fabricmc.loader.api.FabricLoader;
import net.levelz.stats.Skill;

public class LevelZ {
    public static void init(DatagenModContainer container){
        if(!FabricLoader.getInstance().isModLoaded("levelz")){
            return;
        }


    }
}
