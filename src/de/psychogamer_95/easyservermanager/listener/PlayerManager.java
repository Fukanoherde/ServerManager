package de.psychogamer_95.easyservermanager.listener;

import de.psychogamer_95.easyservermanager.Main;
import de.psychogamer_95.easyservermanager.api.BanAPI;
import de.psychogamer_95.easyservermanager.database.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerManager implements Listener {

    private Main plugin;
    public PlayerManager (Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent e) {
        if(BanAPI.isBanned(e.getPlayer().getUniqueId().toString())) {
            if(Bukkit.getPlayer(e.getPlayer().getName()) != null && Main.getSystem().BungeeCord == true) {
                Bukkit.getPlayer(e.getPlayer().getName()).kickPlayer("\n" + Main.getSystem().ServerName + " §4Netzwerk"
                        + "\n§cDu wurdest vom Netzwerk gebannt!"
                        + "\n§4GRUND: §e" + BanAPI.getReason(e.getPlayer().getUniqueId().toString()) + "\n"
                        + "\n"
                        + "\n§bVerbleibene Zeit: §e" + BanAPI.getRemainingTime(e.getPlayer().getUniqueId().toString()) + ""
                        + "\n§aDu kannst im Forum ein Entbannungsantrag stellen!"
                        + "\n§5FORUM: " + Main.getSystem().Forum);
            } else {
                if(Bukkit.getPlayer(e.getPlayer().getName()) != null) {
                    Bukkit.getPlayer(e.getPlayer().getName()).kickPlayer("\n" + Main.getSystem().ServerName + " §4Server"
                            + "\n§cDu wurdest vom Server gebannt!"
                            + "\n§4GRUND: §e" + BanAPI.getReason(e.getPlayer().getUniqueId().toString()) + "\n"
                            + "\n"
                            + "\n§bVerbleibene Zeit: §e" + BanAPI.getRemainingTime(e.getPlayer().getUniqueId().toString()) + ""
                            + "\n§aDu kannst im Forum ein Entbannungsantrag stellen!"
                            + "\n§5FORUM: " + Main.getSystem().Forum);
                }
            }
        }
    }
}