import com.github.cybellereaper.client.DiscordClient;
import com.github.cybellereaper.client.DiscordClientConfig;
import com.github.cybellereaper.commands.core.execute.CommandFramework;
import com.github.cybellereaper.commands.discord.adapter.DiscordCommandDispatcher;
import com.github.cybellereaper.commands.discord.adapter.DiscordFrameworkBinder;
import com.github.cybellereaper.commands.discord.sync.DiscordCommandSyncService;
import com.github.cybellereaper.events.core.bus.EventFramework;
import com.github.cybellereaper.examples.commands.ExampleCommandBootstrap;
import com.github.cybellereaper.examples.events.ExampleEventBootstrap;
import com.github.cybellereaper.examples.interactions.ExampleInteractionBootstrap;
import com.github.cybellereaper.examples.interactions.InteractionRouterExamples;
import com.github.cybellereaper.gateway.GatewayIntent;
import com.github.cybellereaper.interactions.core.execute.InteractionFramework;

import java.util.Set;

void main() throws Exception {
    String token = System.getenv("DISCORD_BOT_TOKEN");
    String guildId = System.getenv("DISCORD_GUILD_ID");

    if (token == null || token.isBlank()) {
        throw new IllegalStateException("DISCORD_BOT_TOKEN must be set");
    }

    int intents = GatewayIntent.combine(GatewayIntent.GUILDS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT);
    DiscordClientConfig config = DiscordClientConfig.builder(token)
            .intents(intents)
            .build();

    CommandFramework commandFramework = ExampleCommandBootstrap.createFramework();
    InteractionFramework interactionFramework = ExampleInteractionBootstrap.createFramework();
    EventFramework eventFramework = ExampleEventBootstrap.createFramework(Set.of(
            GatewayIntent.GUILDS,
            GatewayIntent.GUILD_MESSAGES,
            GatewayIntent.MESSAGE_CONTENT
    ));

    DiscordCommandDispatcher dispatcher = new DiscordCommandDispatcher(commandFramework);

    try (DiscordClient client = DiscordClient.create(config)) {
        InteractionRouterExamples.register(client);

        DiscordCommandSyncService syncService = new DiscordCommandSyncService(commandFramework);
        if (guildId == null || guildId.isBlank()) {
            syncService.syncGlobal(client);
            client.registerGlobalSlashCommand("ticket", "Open ticket modal example");
        } else {
            syncService.syncGuild(client, guildId);
            client.registerGuildSlashCommand(guildId, "ticket", "Open ticket modal example");
        }

        // Existing command framework adapter wiring.
        DiscordFrameworkBinder.bind(client, commandFramework, dispatcher);

        // Interaction/event frameworks are initialized and available for transport adapters.
        if (!eventFramework.intentDiagnostics().isEmpty()) {
            System.err.println("Event listeners registered with missing intents: " + eventFramework.intentDiagnostics());
        }
        if (interactionFramework.routeRegistry().allHandlers().isEmpty()) {
            throw new IllegalStateException("Interaction example registration failed");
        }

        client.login();
        Thread.currentThread().join();
    }
}
