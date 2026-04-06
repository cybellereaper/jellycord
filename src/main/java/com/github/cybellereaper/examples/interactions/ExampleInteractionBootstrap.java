package com.github.cybellereaper.examples.interactions;

import com.github.cybellereaper.interactions.core.execute.InteractionFramework;

public final class ExampleInteractionBootstrap {
    private ExampleInteractionBootstrap() {
    }

    public static InteractionFramework createFramework() {
        InteractionFramework framework = new InteractionFramework();
        framework.registerInteractions(new TicketInteractionExample());
        framework.registerCondition("staffOnly", context -> context.guildId() != null);
        return framework;
    }
}
