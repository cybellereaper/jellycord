package com.github.cybellereaper.examples.events;

import com.github.cybellereaper.events.core.bus.EventFramework;
import com.github.cybellereaper.gateway.GatewayIntent;

import java.util.Set;

public final class ExampleEventBootstrap {
    private ExampleEventBootstrap() {
    }

    public static EventFramework createFramework(Set<GatewayIntent> intents) {
        EventFramework framework = new EventFramework(intents);
        framework.registerListeners(new ModerationEventExample());
        framework.registerFilter("ignoreBots", context -> true);
        return framework;
    }
}
