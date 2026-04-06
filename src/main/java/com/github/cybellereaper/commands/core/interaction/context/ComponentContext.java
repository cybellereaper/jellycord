package com.github.cybellereaper.commands.core.interaction.context;

import com.github.cybellereaper.commands.core.execute.CommandResponder;
import com.github.cybellereaper.commands.core.model.InteractionExecution;

import java.util.Map;

public final class ComponentContext extends InteractionContext {
    public ComponentContext(InteractionExecution interaction, CommandResponder responder, Map<String, String> pathParams) {
        super(interaction, responder, pathParams);
    }
}
