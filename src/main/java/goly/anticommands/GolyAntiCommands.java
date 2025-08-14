package goly.anticommands;

import org.bukkit.plugin.java.JavaPlugin;

public class GolyAntiCommands extends JavaPlugin {

    private static GolyAntiCommands instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new CommandListener(), this);
        getServer().getPluginManager().registerEvents(new TabBlockerListener(), this);
        getCommand("anticommands").setExecutor(new ReloadCommand());
        getLogger().info("GolyAntiCommands Enabled!");
    }

    public static GolyAntiCommands getInstance() {
        return instance;
    }

    public void reload() {
        reloadConfig();
    }
}
