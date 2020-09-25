package de.psychogamer_95.easyservermanager.commands;

import de.psychogamer_95.easyservermanager.Main;
import de.psychogamer_95.easyservermanager.api.BanAPI;
import de.psychogamer_95.easyservermanager.manager.BanUnit;
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
        if(sender.hasPermission("easyservermanager.tempban")) {
            if(args.length >= 4) {
                String playername = args[0];
                Player target = Bukkit.getPlayer(args[0]);
                if(BanAPI.isBanned(getUUID(playername))) {
                    sender.sendMessage(Main.getSystem().Prefix + "§4Dieser Spieler ist bereits gebannt!");
                    return true;
                }
                long value;
                try {
                    value = Integer.valueOf(args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(Main.getSystem().Prefix + "§cZahlenwert muss eine Zahl sein!");
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
                    if (Bukkit.getPlayer(target.getName()) != null && Main.getSystem().BungeeCord == true) {
                        target.kickPlayer("\n" + Main.getSystem().ServerName + " §4Netzwerk"
                                + "\n§cDu wurdest vom Netzwerk gebannt!"
                                + "\n§4GRUND: §e" + BanAPI.getReason(target.getPlayer().getUniqueId().toString()) + "\n"
                                + "\n"
                                + "\n§bVerbleibene Zeit: §e" + BanAPI.getRemainingTime(target.getPlayer().getUniqueId().toString()) + ""
                                + "\n§aDu kannst im Forum ein Entbannungsantrag stellen!"
                                + "\n§5FORUM: " + Main.getSystem().Forum);
                        sender.sendMessage("§aDu hast §e" + playername + " §af§r §e" + value + " §e" + unit.getName() + " §agebannt!");
                        return true;
                    } else {
                        target.kickPlayer("\n" + Main.getSystem().ServerName + " §4Server"
                                + "\n§cDu wurdest vom Server gebannt!"
                                + "\n§4GRUND: §e" + BanAPI.getReason(target.getPlayer().getUniqueId().toString()) + "\n"
                                + "\n"
                                + "\n§bVerbleibene Zeit: §e" + BanAPI.getRemainingTime(target.getPlayer().getUniqueId().toString()) + ""
                                + "\n§aDu kannst im Forum ein Entbannungsantrag stellen!"
                                + "\n§5FORUM: " + Main.getSystem().Forum);
                    }
                }
                sender.sendMessage(Main.getSystem().Prefix + "§cDiese einheit existiert nicht! §aEs existieren nur <sek> <min> <hours> <day> <week>");
                return true;
            }
            sender.sendMessage(Main.getSystem().Prefix + "§c/tempban <Spieler> <Zahlenwert> <Einheit> <Grund>");
            return true;
        }
        return true;
    }
    private String getUUID(String playername) {
        return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
    }
}