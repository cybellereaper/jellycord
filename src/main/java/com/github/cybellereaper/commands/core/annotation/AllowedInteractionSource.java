package com.github.cybellereaper.commands.core.annotation;

import com.github.cybellereaper.commands.core.model.InteractionSource;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface AllowedInteractionSource {
    InteractionSource[] value();
}
