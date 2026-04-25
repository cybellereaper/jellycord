require "../src/medusae"

button_row = Medusae::Client::DiscordActionRow.of([
  Medusae::Client::DiscordButton.primary("confirm", "Confirm"),
  Medusae::Client::DiscordButton.link("https://discord.com/developers/docs", "Docs"),
])

select_row = Medusae::Client::DiscordActionRow.of([
  Medusae::Client::DiscordStringSelectMenu.of("theme", [
    Medusae::Client::DiscordSelectOption.of("Dark", "dark").as_default,
    Medusae::Client::DiscordSelectOption.of("Light", "light"),
  ]).with_placeholder("Choose a theme").with_selection_range(1, 1),
])

payload = Medusae::Client::DiscordMessage.of_content("Choose your settings")
  .with_components([button_row, select_row])
  .as_ephemeral
  .to_payload

puts payload.to_json
