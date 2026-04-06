package com.github.cybellereaper.commands.core.interaction.context;

import com.github.cybellereaper.commands.core.execute.CommandResponder;
import com.github.cybellereaper.commands.core.model.InteractionExecution;
import com.github.cybellereaper.commands.core.response.CommandResponse;

import java.util.Map;
import java.util.Objects;

public class InteractionContext {
    private final InteractionExecution interaction;
    private final CommandResponder responder;
    private final Map<String, String> pathParams;

    public InteractionContext(InteractionExecution interaction, CommandResponder responder, Map<String, String> pathParams) {
        this.interaction = Objects.requireNonNull(interaction, "interaction");
        this.responder = Objects.requireNonNull(responder, "responder");
        this.pathParams = pathParams == null ? Map.of() : Map.copyOf(pathParams);
    }

    public InteractionExecution interaction() {
        return interaction;
    }

    public Map<String, String> pathParams() {
        return pathParams;
    }

    public String pathParam(String name) {
        return pathParams.get(name);
    }

    public void respond(CommandResponse response) {
        responder.accept(response);
    }
}
