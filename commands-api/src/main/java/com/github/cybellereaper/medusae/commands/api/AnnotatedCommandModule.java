package com.github.cybellereaper.medusae.commands.api;

import com.github.cybellereaper.medusae.commands.core.execute.CommandFramework;

import java.util.List;

/**
 * Stable contract between runtime bootstrap and annotation-based command modules.
 */
public interface AnnotatedCommandModule {
    void registerCoreCapabilities(CommandFramework framework);

    List<Object> commandHandlers();

    List<Object> interactionModules();
}
