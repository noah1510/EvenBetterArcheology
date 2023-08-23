package de.sakurajin.evenbetterarcheology.api.owo_annotations;

import de.sakurajin.evenbetterarcheology.api.datagen.generationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface GenerateBlock{
    String[] textures() default {};
    generationType type();
}
