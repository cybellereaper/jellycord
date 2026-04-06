package com.github.cybellereaper.examples.interactions;

import com.github.cybellereaper.client.*;

import java.util.List;

public final class InteractionRouterExamples {
    private InteractionRouterExamples() {
    }

    public static void register(DiscordClient client) {
        client.onSlashCommandContext("ticket", ctx -> ctx.respondWithModal(buildTicketModal()));

        client.onModalSubmitContext("ticket:create", ctx -> {
            String subject = ctx.modalValue("subject");
            String details = ctx.modalValue("details");
            DiscordButton closeButton = DiscordButton.primary("ticket:close", "Close Ticket");
            DiscordActionRow row = DiscordActionRow.of(List.of(closeButton));
            ctx.respondWithMessage(DiscordMessage.ofComponents(
                    "Ticket created: **" + safe(subject) + "**\n" + safe(details),
                    List.of(row)
            ).asEphemeral());
        });

        client.onComponentInteractionContext("ticket:close", ctx ->
                ctx.respondWithMessage(DiscordMessage.ofContent("Ticket closed.").asEphemeral()));
    }

    static DiscordModal buildTicketModal() {
        DiscordTextInput subject = DiscordTextInput.shortInput("subject", "Subject")
                .withLengthRange(3, 80)
                .withPlaceholder("Billing question");
        DiscordTextInput details = DiscordTextInput.paragraph("details", "Details")
                .withLengthRange(10, 1000)
                .withPlaceholder("Describe the issue...");

        return DiscordModal.of(
                "ticket:create",
                "Create Ticket",
                List.of(
                        DiscordActionRow.of(List.of(subject)),
                        DiscordActionRow.of(List.of(details))
                )
        );
    }

    static String safe(String value) {
        return value == null || value.isBlank() ? "(empty)" : value;
    }
}
