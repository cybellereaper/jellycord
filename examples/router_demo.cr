require "json"
require "../src/medusae"

router = Medusae::Client::SlashCommandRouter.new(
  ->(id : String, token : String, type : Int32, data : Hash(String, JSON::Any)?) {
    puts "Responding id=#{id} token=#{token} type=#{type} data=#{data}"
  }
)

router.register_slash_handler("ping") do |interaction|
  puts "slash command received: #{interaction}"
end

router.register_component_handler("confirm") do |interaction|
  puts "button clicked: #{interaction}"
end

router.handle_interaction(JSON.parse(%({"id":"1","token":"abc","type":2,"data":{"name":"ping"}})))
router.handle_interaction(JSON.parse(%({"id":"2","token":"def","type":3,"data":{"custom_id":"confirm"}})))
