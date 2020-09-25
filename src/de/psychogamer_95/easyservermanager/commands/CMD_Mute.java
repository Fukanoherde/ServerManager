package de.psychogamer_95.easyservermanager.commands;

import de.psychogamer_95.easyservermanager.Main;
import de.psychogamer_95.easyservermanager.api.MuteAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CMD_Mute implements CommandExecutor {

    private Main plugin;
    public CMD_Mute (Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("mute").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("")) {
            if (args.length >=2) {
                String playername = args[0];
                if (!MuteAPI.isMuted(getUUID(playername))) {
                    sender.sendMessage(plugin.Prefix + "");
                    return true;
                } else {
                    String reason = "";
                    for(int i = 1; i < args.length; i++) {
                        reason += args[i] + " ";
                    }
                    MuteAPI.Mute(getUUID(playername), playername, reason, -1);
                    sender.sendMessage(Main.getSystem().Prefix + "§7Du hast §e" + playername + " §4PERMAMENT §7vom Chat gebannt!");
                    return true;
                }
            } else {
                sender.sendMessage(plugin.Prefix + "§bUse: §8/mute <Player> <Reason>");
                return true;
            }
        } else {
            sender.sendMessage(plugin.Prefix + plugin.NoPerm);
            return true;
        }
    }
    private String getUUID(String playername) {
        return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
    }
}