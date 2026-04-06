package com.github.cybellereaper.examples.interactions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InteractionRouterExamplesTest {

    @Test
    void buildsTicketModalWithExpectedFields() {
        var modal = InteractionRouterExamples.buildTicketModal();

        assertEquals("ticket:create", modal.customId());
        assertEquals(2, modal.components().size());
        assertEquals("subject", modal.components().getFirst().components().getFirst().toPayload().get("custom_id"));
        assertEquals("details", modal.components().get(1).components().getFirst().toPayload().get("custom_id"));
    }

    @Test
    void safeFallsBackForBlankValues() {
        assertEquals("(empty)", InteractionRouterExamples.safe(null));
        assertEquals("(empty)", InteractionRouterExamples.safe("  "));
        assertEquals("value", InteractionRouterExamples.safe("value"));
    }
}
