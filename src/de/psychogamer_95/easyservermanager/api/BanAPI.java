package de.psychogamer_95.easyservermanager.api;

import de.psychogamer_95.easyservermanager.Main;
import de.psychogamer_95.easyservermanager.database.MySQL;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BanAPI {

    public static void ban(String uuid, String playername, String reason, long seconds) {
        long end = 0;
        if(seconds == -1) {
            end = -1;
        } else {
            long cureent = System.currentTimeMillis();
            long millis = seconds*1000;
            end = cureent+millis;
        }
        MySQL.update("INSERT INTO BannedPlayers (Name, UUID, End, Reason) VALUES ('" +playername+"','"+uuid+"','"+end+"','"+reason+"')");
        if(Bukkit.getPlayer(playername) != null && Main.getSystem().BungeeCord == true) {
            Bukkit.getPlayer(playername).kickPlayer("\n" + Main.getSystem().ServerName + " §4Netzwerk"
                    + "\n§cDu wurdest vom Netzwerk gebannt!"
                    + "\n§4GRUND: §e" + getReason(uuid) + "\n"
                    + "\n"
                    + "\n§bVerbleibene Zeit: §e" + getRemainingTime(uuid) + ""
                    + "\n§aDu kannst im Forum ein Entbannungsantrag stellen!"
                    + "\n§5FORUM: " + Main.getSystem().Forum);
        } else {
            if(Bukkit.getPlayer(playername) != null) {
                Bukkit.getPlayer(playername).kickPlayer("\n" + Main.getSystem().ServerName + " §4Server"
                        + "\n§cDu wurdest vom Server gebannt!"
                        + "\n§4GRUND: §e" + getReason(uuid) + "\n"
                        + "\n"
                        + "\n§bVerbleibene Zeit: §e" + getRemainingTime(uuid) + ""
                        + "\n§aDu kannst im Forum ein Entbannungsantrag stellen!"
                        + "\n§5FORUM: " + Main.getSystem().Forum);
            }
        }
    }
    public static void unban(String uuid) {
        MySQL.update("DELETE FROM BannedPlayers WHERE UUID='"+uuid+"'");
    }
    public static boolean isBanned(String uuid) {
        if(existsPlayer(uuid)) {
            long end = getEnd(uuid);

            long remainingTime = end - System.currentTimeMillis();

            if (remainingTime < 0) {
                System.out.println(remainingTime);
                unban(uuid);
                return false;
            }
            return true;
        }
        return false;
    }
    public static String getReason(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='"+uuid+"'");
        try {
            while(rs.next()) {
                return rs.getString("Reason");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static boolean existsPlayer(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT 1 FROM BannedPlayers WHERE UUID='"+uuid+"'");
        try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static Long getEnd(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='"+uuid+"'");
        try {
            if (rs.next()) {
                return rs.getLong("End");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<String> getBannedPlayers() {
        List<String> list = new ArrayList<String>();
        ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers");
        try {
            while(rs.next()) {
                list.add(rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static String getRemainingTime(String uuid) {
        long current = System.currentTimeMillis();
        long end = getEnd(uuid);
        if(end == -1) {
            return "§4Permanent";
        }
        long millis = end - current;
        long seconds = 0;
        int minutes = 0;
        int hours = 0;
        int days = 0;
        int weeks = 0;
        while(millis > 1000) {
            millis-=1000;
            seconds++;
        }
        while(seconds > 60) {
            seconds-=60;
            minutes++;
        }
        while(minutes > 60) {
            minutes-=60;
            hours++;
        }
        while(hours > 24) {
            hours-=24;
            days++;
        }
        while(days > 7) {
            days-=7;
            weeks++;
        }
        return "§e" + weeks + " §eWoche(n) " + days + " Tage(n) " + hours + " Stunde(n) " + minutes + " Minute(n) " + seconds + " Sekunde(n)";
    }
}