package com.github.cybellereaper.commands.core.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RoleSelectHandler {
    String value();
}
