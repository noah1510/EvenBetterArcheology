package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;

public class Stairs implements BlockGenerationType {
    @Override
    public void generate(String name, DatagenModContainer container, String[] textures) {
        if (textures.length == 1) {
            container.RESOURCE_GENERATION_HELPER.generateStairs(name, textures[0]);
        }else if (textures.length == 3){
            container.RESOURCE_GENERATION_HELPER.generateStairs(name, textures[0], textures[1], textures[2]);
        }else{
            throw new IllegalArgumentException("Stairs need 1 or 3 textures");
        }
    }
}
