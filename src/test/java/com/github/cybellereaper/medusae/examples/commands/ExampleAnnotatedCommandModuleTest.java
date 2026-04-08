package com.github.cybellereaper.medusae.examples.commands;

import com.github.cybellereaper.medusae.commands.api.AnnotatedCommandModule;
import com.github.cybellereaper.medusae.commands.core.execute.CommandFramework;
import com.github.cybellereaper.medusae.commands.core.model.CommandInteraction;
import com.github.cybellereaper.medusae.commands.core.model.CommandOptionValue;
import com.github.cybellereaper.medusae.commands.core.model.CommandType;
import com.github.cybellereaper.medusae.commands.core.model.InteractionHandlerType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExampleAnnotatedCommandModuleTest {

    @Test
    void annotatedModuleRegistersDiscoverableCommandsAndHandlers() {
        AnnotatedCommandModule module = new ExampleAnnotatedCommandModule();
        CommandFramework framework = new CommandFramework();

        module.registerCoreCapabilities(framework);
        framework.registerCommands(module.commandHandlers().toArray());
        framework.registerModules(module.interactionModules().toArray());

        assertTrue(framework.registry().find("user").isPresent());
        assertTrue(framework.registry().find("ticket").isPresent());
        assertTrue(framework.interactionRegistry().find(InteractionHandlerType.BUTTON, "ticket:create").isPresent());
        assertTrue(framework.interactionRegistry().find(InteractionHandlerType.MODAL, "ticket:create").isPresent());

        List<String> completions = framework.executeAutocomplete(new CommandInteraction(
                "user", CommandType.CHAT_INPUT, null, "ban",
                Map.of("reason", new CommandOptionValue("sp", 3)),
                "reason", null, false, "guild", "caller", Set.of(), Set.of(),
                null, null, null, null, null, null
        ), response -> {});

        assertTrue(completions.contains("Spam"));
    }
}
