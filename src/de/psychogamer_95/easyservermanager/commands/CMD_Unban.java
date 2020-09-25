package de.psychogamer_95.easyservermanager.commands;

import de.psychogamer_95.easyservermanager.Main;
import de.psychogamer_95.easyservermanager.api.BanAPI;
import de.psychogamer_95.easyservermanager.manager.PermManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CMD_Unban implements CommandExecutor {

    private Main plugin;
    public CMD_Unban (Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("unban").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission((String) PermManager.getValue("Perms.Unban")) || sender.hasPermission((String) PermManager.getValue("Perms.All"))) {
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
            sender.sendMessage(plugin.Prefix + plugin.NoPerm);
        }
        return true;
    }
    private String getUUID(String playername) {
        return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
    }
}