package com.github.cybellereaper.commands.core.model;

import java.util.Map;

public record InteractionRouteMatch(String route, Map<String, String> pathParams, String statePayload) {
}
