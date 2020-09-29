package de.psychogamer_95.easyservermanager.listener;

import de.psychogamer_95.easyservermanager.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerManager implements Listener {

    private Main plugin;
    public ServerManager (Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onMOTD (ServerListPingEvent e) {
        e.setMaxPlayers(plugin.MaxPlayers);
        e.setMotd(plugin.MOTDHeader + "\n" + plugin.MOTDFooter);
    }
}