package de.psychogamer_95.easyservermanager.commands;

import de.psychogamer_95.easyservermanager.Main;
import de.psychogamer_95.easyservermanager.api.BanAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Unban implements CommandExecutor {

    private Main plugin;
    public CMD_Unban (Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("unban").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("easyservermanager.unban")) {
            String target = args[0];
            if (args.length == 1) {
                if(!BanAPI.isBanned(getUUID(target))) {
                    sender.sendMessage(plugin.Prefix + "§cDieser Spieler ist nicht gebannt!");
                    return true;
                }
                BanAPI.unban(getUUID(target));
                sender.sendMessage(plugin.Prefix + "§aDu hast §e" + target + " §aentbannt!");
                return true;
            } else {
                sender.sendMessage(plugin.Prefix + "§c/unban <Spieler>");
                return true;
            }
        } else {
            sender.sendMessage(plugin.Prefix + "§4Keine Rechte...");
        }
        return true;
    }
    private String getUUID(String playername) {
        return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
    }
}