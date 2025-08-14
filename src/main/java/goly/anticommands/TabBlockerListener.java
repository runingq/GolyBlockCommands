package goly.anticommands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class TabBlockerListener implements Listener {

    @EventHandler
    public void onTabComplete(PlayerCommandSendEvent event) {
        boolean blockTab = GolyAntiCommands.getInstance().getConfig().getBoolean("blocktabcommands", false);

        if (blockTab) {
            event.getCommands().clear(); // BLOQUEAR EL AUTOCOMPLETAR DE LOS COMANDOS
        }
    }
}
