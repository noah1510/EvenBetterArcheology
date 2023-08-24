package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Parsers;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Annotations.GenerateBlock;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.BlockGenerationType;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.DatagenModContainer;
import net.minecraft.item.ItemConvertible;

import java.lang.reflect.Field;

public class BlockGenerationParser implements AnnotationParser{

    @Override
    public void parse(String namespace, ItemConvertible value, String identifier, Field field, DatagenModContainer container) {
        if (field.isAnnotationPresent(GenerateBlock.class)){
            GenerateBlock annotation = field.getAnnotation(GenerateBlock.class);
            try{
                if (!BlockGenerationType.class.isAssignableFrom(annotation.type())){
                    container.LOGGER.error("Could not generate Block " + identifier + " because the specified type is not a valid GenerationType");
                    return;
                }

                ((BlockGenerationType)annotation.type().getConstructor().newInstance()).generate(identifier, container, annotation.textures());
            }catch(Exception e) {
                container.LOGGER.error("Could not generate Block ", e);
            }
        }
    }
}
