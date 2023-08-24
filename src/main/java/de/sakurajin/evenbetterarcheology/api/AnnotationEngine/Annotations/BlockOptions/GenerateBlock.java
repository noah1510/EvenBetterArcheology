package de.sakurajin.evenbetterarcheology.api.AnnotationEngine.Annotations.BlockOptions;

import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.AutoDetectBlock;
import de.sakurajin.evenbetterarcheology.api.AnnotationEngine.BlockGenerationTypes.BlockGenerationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface GenerateBlock{
    String[] textures() default {};
    Class<? extends BlockGenerationType> type() default AutoDetectBlock.class;
    boolean generateModel() default true;
    boolean generateState() default true;
}
