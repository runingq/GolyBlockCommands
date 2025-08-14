package goly.anticommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CommandListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String msg = event.getMessage().toLowerCase();

        List<String> blocked = GolyAntiCommands.getInstance().getConfig().getStringList("commands");
        String authorized = GolyAntiCommands.getInstance().getConfig().getString("authorized-nick");

        for (String cmd : blocked) {
            if (msg.startsWith(cmd.toLowerCase())) {
                if (!player.getName().equalsIgnoreCase(authorized)) {
                    event.setCancelled(true);

                    String head = "§7[§f" + player.getName() + "§7]";
                    String message = GolyAntiCommands.getInstance().getConfig().getString("messages.blocked-command")
                            .replace("{player_head}", head);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));

                    sendWebhook(player.getName(), msg);
                }
                return;
            }
        }
    }

    private void sendWebhook(String player, String command) {
        String webhookUrl = GolyAntiCommands.getInstance().getConfig().getString("webhook-url");
        try {
            URL url = new URL(webhookUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonPayload = "{\"content\": \"El jugador **" + player + "** intentó ejecutar `" + command + "`\"}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            conn.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
