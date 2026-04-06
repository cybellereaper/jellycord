package com.github.cybellereaper.commands.core.interaction.context;

import com.github.cybellereaper.commands.core.execute.CommandResponder;
import com.github.cybellereaper.commands.core.model.InteractionExecution;

import java.util.Map;

public final class ModalContext extends InteractionContext {
    public ModalContext(InteractionExecution interaction, CommandResponder responder, Map<String, String> pathParams) {
        super(interaction, responder, pathParams);
    }

    public String field(String fieldId) {
        return interaction().modalFields().get(fieldId);
    }
}
