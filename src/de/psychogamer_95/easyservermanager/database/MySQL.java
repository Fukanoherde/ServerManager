package de.psychogamer_95.easyservermanager.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.psychogamer_95.easyservermanager.manager.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MySQL {

    public static String username;
    public static String password;
    public static String database;
    public static String host;
    public static String port;
    public static Connection con;

    public MySQL(String user, String pass, String host2, String dB) {}

    public static void Connect () {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + username + "&password=" + password + "&autoReconnect=true");
                Bukkit.getConsoleSender().sendMessage(MessageManager.getValue("Message.MySQL_Connect").toString().replaceAll("&", "§") + " §3Please contact the Plugin Developer -> PsychoGamer_95!");
            } catch (SQLException er) {
                Bukkit.getConsoleSender().sendMessage(MessageManager.getValue("Message.MySQL_Connect_Error").toString().replaceAll("&", "§") + " §3Please contact the Plugin Developer -> PsychoGamer_95!");
            }
        }
    }
    public static void close () {
        if (isConnected()) {
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage(MessageManager.getValue("Message.MySQL_Close").toString().replaceAll("&", "§"));
            } catch (SQLException er) {
                Bukkit.getConsoleSender().sendMessage(MessageManager.getValue("MySQL_Close_Error").toString().replaceAll("&", "§" + "§cPlease contact the Plugin Developer -> §4PsychoGamer_95"));
            }
        }
    }
    public static boolean isConnected() {
        return con != null;
    }
    public static void createBanTable() {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS BannedPlayers (Name VARCHAR(100), UUID VARCHAR(100), End BIGINT, Reason VARCHAR(100))");
                Bukkit.getConsoleSender().sendMessage("§7[§3MySQL§7] §eBannedPlayers Table created");

            } catch (SQLException er) {
                Bukkit.getConsoleSender().sendMessage("§7[§3MySQL§7] §4the §cBannedPlayers §4Table can not created! §cPlease contact the Plugin Developer -> §4PsychoGamer_95");
            }
        }
    }
    public static void createMuteTable() {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS MutedPlayers (Name VARCHAR(100), UUID VARCHAR(100), End TIMESTAMP, Reason VARCHAR(100))");
                Bukkit.getConsoleSender().sendMessage("§7[§3MySQL§7] §eMutedPlayers Table created");

            } catch (SQLException er) {
                Bukkit.getConsoleSender().sendMessage("§7[§3MySQL§7] §4the §cMutedPlayers §4Table can not created! §cPlease contact the Plugin Developer -> §4PsychoGamer_95");
            }
        }
    }
    public static void update(String qry) {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate(qry);
            } catch (SQLException er) {
                er.printStackTrace();
            }
        }
    }
    public static ResultSet getResult(String qry) {
        ResultSet rs = null;
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(qry);
        } catch (SQLException er) {
            Connect();
            System.err.println(er);
        }
        return rs;
    }
    public static File getMySQLFile() {
        return new File("plugins/EasyServerManager", "MySQL.yml");
    }
    public static FileConfiguration getMySQLFileConfiguration() {
        return YamlConfiguration.loadConfiguration(getMySQLFile());
    }
    public static void setStandardMySQL() {
        FileConfiguration cfg = getMySQLFileConfiguration();

        cfg.options().copyDefaults(true);
        cfg.addDefault("username", "root");
        cfg.addDefault("password", "password");
        cfg.addDefault("database", "localhost");
        cfg.addDefault("host", "localhost");
        cfg.addDefault("port", "3306");
        try {
            cfg.save(getMySQLFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readMySQL() {
        FileConfiguration cfg = getMySQLFileConfiguration();
        username = cfg.getString("username");
        password = cfg.getString("password");
        database = cfg.getString("database");
        host = cfg.getString("host");
        port = cfg.getString("port");
    }
}