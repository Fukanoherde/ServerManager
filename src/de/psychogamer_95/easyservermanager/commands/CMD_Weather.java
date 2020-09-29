package de.psychogamer_95.easyservermanager.commands;

import de.psychogamer_95.easyservermanager.Main;
import de.psychogamer_95.easyservermanager.manager.MessageManager;
import de.psychogamer_95.easyservermanager.manager.PermManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Weather implements CommandExecutor {

    private Main plugin;
    public CMD_Weather (Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("wetter").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 1) {
                if (p.hasPermission(PermManager.getValue("Perms.Weather").toString()) || p.hasPermission("Perms.All")) {
                    if (args[0].equalsIgnoreCase("regen")) {
                        Bukkit.getWorld(p.getLocation().getWorld().getName()).setStorm(true);
                        Bukkit.getWorld(p.getLocation().getWorld().getName()).setThundering(true);
                        p.sendMessage(plugin.Prefix + MessageManager.getValue("Message.SetRain").toString().replaceAll("&", "§"));
                        return true;
                    }
                } else {
                    p.sendMessage(plugin.Prefix + plugin.NoPerm);
                }
            } else {
                p.sendMessage(plugin.Prefix + MessageManager.getValue("Message.Use_Weather").toString().replaceAll("&", "§"));
            }
            if (p.hasPermission(PermManager.getValue("Perms.Weather").toString()) || p.hasPermission("Perms.All")) {
                if (args[0].equalsIgnoreCase("sonne")) {
                    Bukkit.getWorld(p.getLocation().getWorld().getName()).setStorm(false);
                    Bukkit.getWorld(p.getLocation().getWorld().getName()).setThundering(false);
                    p.sendMessage(plugin.Prefix + MessageManager.getValue("Message.SetSun").toString().replaceAll("&", "§"));
                    return true;
                } else {
                    p.sendMessage(plugin.Prefix + MessageManager.getValue("Message.Use_Weather").toString().replaceAll("&", "§"));
                }
            } else {
                p.sendMessage(plugin.Prefix + plugin.NoPerm);
            }
        } else {
            sender.sendMessage("");
        }
        return false;
    }
}