# Interaction Command Framework Migration Notes

## Existing command-only code

Existing `registerCommands(...)` usage remains valid and unchanged.

## Incremental adoption path

1. Keep existing slash command handlers and return `String`/`ImmediateResponse`.
2. Switch command handlers to `InteractionReply` when richer payloads are needed.
3. Register command + UI handlers together with `registerModules(...)`.
4. Add `@ButtonHandler`, select handlers, and `@ModalHandler` for command-first workflows.
5. Use `@PathParam` and `@Field` to get typed bindings for custom-id routes and modal fields.

## New response model highlights

- `InteractionReply` supports content, embeds, components, ephemeral flags, defer and update modes.
- `ModalReply` opens modals from command, component, or context handlers.
- `String` return values are still accepted and mapped to immediate public replies.

## Stateful route strategy

Custom IDs can optionally include a state segment after `|`, e.g.:

`ticket:close:42|signedStatePayload`

The framework extracts route params from the left segment and passes the optional state payload into interaction execution context for custom codecs/session resolvers.
