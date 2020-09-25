package de.psychogamer_95.easyservermanager.api;

import de.psychogamer_95.easyservermanager.database.MySQL;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MuteAPI {

    public static void Mute(String uuid, String playername, String reason, long seconds) {
        long end = 0;
        if(seconds == -1) {
            end = -1;
        } else {
            long cureent = System.currentTimeMillis();
            long millis = seconds*1000;
            end = cureent+millis;
        }
        MySQL.update("INSERT INTO MutedPlayers (Name, UUID, End, Reason) VALUES ('" +playername+"','"+uuid+"','"+end+"','"+reason+"')");
        if(Bukkit.getPlayer(playername) != null) {
        }
    }
    public static void unmute(String uuid) {
        MySQL.update("DELETE FROM MutedPlayers WHERE UUID='"+uuid+"'");
    }
    public static boolean isMuted(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT End FROM MutedPlayers WHERE UUID='"+uuid+"'");
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static String getReason(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM MutedPlayers WHERE UUID='"+uuid+"'");
        try {
            while(rs.next()) {
                return rs.getString("Reason");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static Long getEnd(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM MutedPlayers WHERE UUID='"+uuid+"'");
        try {
            while(rs.next()) {
                return rs.getLong("End");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<String> getMutePlayers() {
        List<String> list = new ArrayList<String>();
        ResultSet rs = MySQL.getResult("SELECT * FROM MutedPlayers");
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
        long minutes = 0;
        long hours = 0;
        long days = 0;
        long weeks = 0;
        while(millis > 1000) {
            millis-=1000;
            seconds++;
        }
        while(seconds > 60) {
            seconds-=60;
            minutes++;
        }
        while(minutes > 60) {
            millis-=60;
            hours++;
        }
        while(hours > 24) {
            millis-=24;
            days++;
        }
        while(days > 7) {
            millis-=7;
            weeks++;
        }
        return "§e" + weeks + " §Woche(n) " + days + " Tage(n) " + hours + " Stunde(n) " + minutes + " Minute(n) " + seconds + " Sekunde(n)";
    }
}