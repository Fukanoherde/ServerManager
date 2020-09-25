package de.psychogamer_95.easyservermanager.commands;

import de.psychogamer_95.easyservermanager.Main;
import de.psychogamer_95.easyservermanager.api.BanAPI;
import de.psychogamer_95.easyservermanager.api.MuteAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CMD_Check implements CommandExecutor {

    private Main plugin;
    public CMD_Check (Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("check").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("")) {
            if (args.length == 1) {
                if(args[0].equalsIgnoreCase("list")) {
                    List<String> list = BanAPI.getBannedPlayers();
                    if(list.size() == 0) {
                        sender.sendMessage(plugin.Prefix + "§cEs sind keine Spieler gebannt!");
                        return true;
                    }
                    for(String AllBanned : BanAPI.getBannedPlayers()) {
                        sender.sendMessage("§7---------- §6§lBan-Liste §7----------");
                        sender.sendMessage("§e" + AllBanned + " §7(Grund: §r" + BanAPI.getReason(getUUID(AllBanned)) + "§7)");
                    }
                    return true;
                }
                String playername = args[0];
                sender.sendMessage("§7---------- §6§lBan-Infos §7----------");
                sender.sendMessage("");
                sender.sendMessage(plugin.Prefix + "§eName: §a" + playername);
                sender.sendMessage(plugin.Prefix + "§eGebannt: " + (BanAPI.isBanned(getUUID(playername)) ? "§aJa" : "§cNein"));
                sender.sendMessage(plugin.Prefix + "§eGemutet: " + (MuteAPI.isMuted(getUUID(playername)) ? "§aJa" : "§cNein"));
                if(MuteAPI.isMuted(getUUID(playername))) {
                    sender.sendMessage(plugin.Prefix + "§eMute-Grund: §a" + MuteAPI.getReason(getUUID(playername)));
                    sender.sendMessage(plugin.Prefix + "§eMute-Dauer: §a" + MuteAPI.getRemainingTime(getUUID(playername)));
                }
                if(BanAPI.isBanned(getUUID(playername))) {
                    sender.sendMessage(plugin.Prefix + "§eBan-Grund: §a" + BanAPI.getReason(getUUID(playername)));
                    sender.sendMessage(plugin.Prefix + "§eBan-Dauer: §a" + BanAPI.getRemainingTime(getUUID(playername)));
                }
            } else {
                sender.sendMessage(plugin.Prefix + "§c/check <list> or <Spieler>");
                return true;
            }
        } else {
            sender.sendMessage(plugin.Prefix + plugin.NoPerm);
            return true;
        }
        return true;
    }
    private String getUUID(String playername) {
        return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
    }
}