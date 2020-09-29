package de.psychogamer_95.easyservermanager.commands;

import de.psychogamer_95.easyservermanager.Main;
import de.psychogamer_95.easyservermanager.manager.MessageManager;
import de.psychogamer_95.easyservermanager.manager.PermManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Kick implements CommandExecutor {

    private Main plugin;
    public CMD_Kick (Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("kick").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.hasPermission((String) PermManager.getValue("Perms.Kick")) || sender.hasPermission((String) PermManager.getValue("Perms.All"))) {
            if (args.length >= 2) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == sender) {
                    sender.sendMessage(plugin.Prefix + MessageManager.getValue("Message.YourselfKick").toString().replaceAll("&", "§"));
                    return true;
                } else {
                    if (target != null) {
                        String msg = "";
                        for(int i = 1; i < args.length; i++) {
                            msg = msg + args[i] + " ";
                        }
                        sender.sendMessage(plugin.Prefix + "§eDu hast den Spieler §a" + target.getDisplayName() + " §eerfolgreich gekickt!");
                        target.kickPlayer(Main.getSystem().ServerName + "\n\n"
                                + "§bDu wurdest vom Server gekickt!\n§cGrund: §4" + msg + "\n\n§9gekickt worden von: §7" + sender.getName());
                        return true;
                    } else {
                        sender.sendMessage(plugin.Prefix + MessageManager.getValue("MessageManager.PlayerNotOnlineKick").toString().replaceAll("&", "§"));
                        return true;
                    }
                }
            } else {
                sender.sendMessage(MessageManager.getValue("Message.Use_Kick").toString().replaceAll("&", "§"));
                return true;
            }
        } else {
            sender.sendMessage(plugin.Prefix + plugin.NoPerm);
        }
        return true;
    }
}