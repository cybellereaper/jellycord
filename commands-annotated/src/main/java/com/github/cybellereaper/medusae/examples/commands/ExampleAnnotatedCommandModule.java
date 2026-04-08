package com.github.cybellereaper.medusae.examples.commands;

import com.github.cybellereaper.medusae.commands.api.AnnotatedCommandModule;
import com.github.cybellereaper.medusae.commands.core.execute.CommandFramework;

import java.util.List;

public final class ExampleAnnotatedCommandModule implements AnnotatedCommandModule {
    private static final List<String> COMMON_REASONS = List.of("Spam", "Harassment", "Raid", "Scam", "Phishing");

    @Override
    public void registerCoreCapabilities(CommandFramework framework) {
        framework.registerCheck("in-guild", ctx -> !ctx.interaction().dm());
        framework.registerAutocomplete("common-reasons", (ctx, input) -> {
            String prefix = input == null ? "" : input.toLowerCase();
            return COMMON_REASONS.stream()
                    .filter(reason -> reason.toLowerCase().startsWith(prefix))
                    .toList();
        });
    }

    @Override
    public List<Object> commandHandlers() {
        return List.of(
                new UserCommands(),
                new UserProfileContextCommands(),
                new MessageContextCommands()
        );
    }

    @Override
    public List<Object> interactionModules() {
        return List.of(new TicketInteractionCommands());
    }
}
