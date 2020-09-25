package de.psychogamer_95.easyservermanager.commands;

import de.psychogamer_95.easyservermanager.Main;
import de.psychogamer_95.easyservermanager.api.BanAPI;
import de.psychogamer_95.easyservermanager.manager.BanUnit;
import de.psychogamer_95.easyservermanager.manager.PermManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CMD_Tempban implements CommandExecutor {

    private Main plugin;
    public CMD_Tempban (Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("tempban").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission((String) PermManager.getValue("Perms.Tempban")) || sender.hasPermission((String)PermManager.getValue("Perms.All"))) {
            if(args.length >= 4) {
                String playername = args[0];
                Player target = Bukkit.getPlayer(args[0]);
                if(BanAPI.isBanned(getUUID(playername))) {
                    sender.sendMessage(plugin.Prefix + "§4Dieser Spieler ist bereits gebannt!");
                    return true;
                }
                long value;
                try {
                    value = Integer.valueOf(args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(plugin.Prefix + "§cZahlenwert muss eine Zahl sein!");
                    return true;
                }
                String unitString = args[2];
                String reason = "";
                for(int i = 3; i < args.length; i++) {
                    reason += args[i] + " ";
                }
                List<String> units = BanUnit.getUnitsAsString();
                if(units.contains(unitString.toLowerCase())) {
                    BanUnit unit = BanUnit.getUnit(unitString);
                    long seconds = value * unit.getToSecond();
                    BanAPI.ban(getUUID(target.getName()), target.getUniqueId().toString(), reason, seconds);
                    if (Bukkit.getPlayer(target.getName()) != null && plugin.BungeeCord == true) {
                        target.kickPlayer("\n" + plugin.ServerName + " §4Netzwerk"
                                + "\n§cDu wurdest vom Netzwerk gebannt!"
                                + "\n§4GRUND: §e" + BanAPI.getReason(target.getPlayer().getUniqueId().toString()) + "\n"
                                + "\n"
                                + "\n§bVerbleibene Zeit: §e" + BanAPI.getRemainingTime(target.getPlayer().getUniqueId().toString()) + ""
                                + "\n§aDu kannst im Forum ein Entbannungsantrag stellen!"
                                + "\n§5FORUM: " + plugin.Forum);
                        sender.sendMessage("§aDu hast §e" + playername + " §af§r §e" + value + " §e" + unit.getName() + " §agebannt!");
                        return true;
                    } else {
                        target.kickPlayer("\n" + plugin.ServerName + " §4Server"
                                + "\n§cDu wurdest vom Server gebannt!"
                                + "\n§4GRUND: §e" + BanAPI.getReason(target.getPlayer().getUniqueId().toString()) + "\n"
                                + "\n"
                                + "\n§bVerbleibene Zeit: §e" + BanAPI.getRemainingTime(target.getPlayer().getUniqueId().toString()) + ""
                                + "\n§aDu kannst im Forum ein Entbannungsantrag stellen!"
                                + "\n§5FORUM: " + plugin.Forum);
                    }
                }
                sender.sendMessage(plugin.Prefix + "§cDiese einheit existiert nicht! §aEs existieren nur <sek> <min> <hours> <day> <week>");
                return true;
            }
            sender.sendMessage(plugin.Prefix + "§c/tempban <Spieler> <Zahlenwert> <Einheit> <Grund>");
            return true;
        } else {
            sender.sendMessage(plugin.Prefix + plugin.NoPerm);
            return true;
        }
    }
    private String getUUID(String playername) {
        return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
    }
}