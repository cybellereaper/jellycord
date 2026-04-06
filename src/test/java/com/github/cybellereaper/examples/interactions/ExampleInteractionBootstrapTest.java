package com.github.cybellereaper.examples.interactions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ExampleInteractionBootstrapTest {

    @Test
    void createsFrameworkWithRegisteredRoutes() {
        var framework = ExampleInteractionBootstrap.createFramework();
        assertFalse(framework.routeRegistry().allHandlers().isEmpty());
    }
}
