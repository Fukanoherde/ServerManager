package de.psychogamer_95.easyservermanager.commands;

import de.psychogamer_95.easyservermanager.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_GameMode implements CommandExecutor {

    public Main plugin;
    public CMD_GameMode (Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("gamemode").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender cms, Command cmd, String label, String[] args) {

        if (cms instanceof Player) {
            Player p = (Player) cms;
            if (p.hasPermission("")) {
                if (args.length == 1) {
                    if (cmd.getName().equalsIgnoreCase(args[1])) {

                    } else {
                        p.sendMessage("§cGive me a number!");
                    }
                } else {
                    p.sendMessage("§8Use: §7/gm <0>, <1>, <2>, <3>");
                }
            } else {

                // NoPerm \\

                p.sendMessage("");
            }
        } else {
            Bukkit.getConsoleSender().sendMessage("§4ERROR: §cThis command is for the real player!");
        }
        return false;
    }
}