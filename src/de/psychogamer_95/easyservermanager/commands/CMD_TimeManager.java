package de.psychogamer_95.easyservermanager.commands;

import de.psychogamer_95.easyservermanager.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_TimeManager implements CommandExecutor {

    private Main plugin;
    public CMD_TimeManager (Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("zeit").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("tag")) {
                    if (p.hasPermission("easyservermanager.time.tag")) {
                        p.getLocation().getWorld().setTime(2000);
                        p.sendMessage("§3Du hast die Zeit erfolgreich auf §2Tag §3gestellt");
                        return true;
                    } else {

                        // NoPerm \\

                        p.sendMessage("§4Keine Rechte...");
                    }
                }
                if (args[0].equalsIgnoreCase("nacht")) {
                    if (p.hasPermission("easyservermanager.time.nacht")) {
                        p.getLocation().getWorld().setTime(13000);
                        p.sendMessage("§3Du hast die Zeit erfolgreich auf §2Nacht §3gestellt");
                        return true;
                    } else {

                        // NoPerm \\

                        p.sendMessage("§4Keine Rechte...");
                    }
                }
            } else {

                // UseCommand \\

                p.sendMessage("§2Verwendung: §8/zeit <tag> oder <nacht>");
            }
        } else {

            // Only Player Console Message \\

            Bukkit.getConsoleSender().sendMessage("§4Nur für Spieler!!");
        }
        return true;
    }
}