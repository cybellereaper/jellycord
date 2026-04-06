package com.github.cybellereaper.examples.events;

import com.github.cybellereaper.gateway.GatewayIntent;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExampleEventBootstrapTest {

    @Test
    void reportsIntentDiagnosticsWhenPrivilegedIntentMissing() {
        var framework = ExampleEventBootstrap.createFramework(Set.of(GatewayIntent.GUILDS));
        assertEquals(1, framework.intentDiagnostics().size());
    }
}
