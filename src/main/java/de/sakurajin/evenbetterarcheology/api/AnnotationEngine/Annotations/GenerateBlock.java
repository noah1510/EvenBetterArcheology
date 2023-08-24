package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Annotations;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.BlockGenerationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface GenerateBlock{
    String[] textures() default {};
    Class<?> type();
}
