package com.github.cybellereaper.medusae.examples.commands;

import com.github.cybellereaper.medusae.commands.api.AnnotatedCommandModule;
import com.github.cybellereaper.medusae.commands.core.execute.CommandFramework;

public final class ExampleCommandBootstrap {
    private static final AnnotatedCommandModule DEFAULT_MODULE = new ExampleAnnotatedCommandModule();

    private ExampleCommandBootstrap() {
    }

    public static CommandFramework createFramework() {
        CommandFramework framework = new CommandFramework();
        DEFAULT_MODULE.registerCoreCapabilities(framework);
        framework.registerCommands(DEFAULT_MODULE.commandHandlers().toArray());
        framework.registerModules(DEFAULT_MODULE.interactionModules().toArray());
        return framework;
    }
}
