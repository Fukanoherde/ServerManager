package de.psychogamer_95.easyservermanager.commands;

import de.psychogamer_95.easyservermanager.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CMD_Kick implements CommandExecutor {

    private Main plugin;
    public CMD_Kick (Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("kick").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        return true;
    }
}