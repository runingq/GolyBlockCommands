package goly.anticommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("golypvp.commands")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', GolyAntiCommands.getInstance().getConfig().getString("messages.no-permission")));
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            GolyAntiCommands.getInstance().reload();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', GolyAntiCommands.getInstance().getConfig().getString("messages.reloaded")));
            return true;
        }

        sender.sendMessage(ChatColor.RED + "Uso: /anticommands reload");
        return true;
    }
}
