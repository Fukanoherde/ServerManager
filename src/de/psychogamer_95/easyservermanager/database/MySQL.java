package de.psychogamer_95.easyservermanager.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
                Bukkit.getConsoleSender().sendMessage("§7[§3MySQL§7] §eThe database succesfully connected!");
            } catch (SQLException er) {
                Bukkit.getConsoleSender().sendMessage("�7[�3MySQL�7] �4ERROR: the connection has been error! �3Please contact the Plugin Developer -> PsychoGamer_95!");
            }
        }
    }
    public static void close () {
        if (isConnected()) {
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage("§7[§3MySQL§7] §ethe Connection succesfully close!");
            } catch (SQLException er) {
                Bukkit.getConsoleSender().sendMessage("§7[§3MySQL§7] §4the MySQL Connection can not Close! §cPlease contact the Plugin Developer -> §4PsychoGamer_95");
            }
        }
    }
    public static boolean isConnected() {
        return con != null;
    }
    public static void createBanTable() {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS BannedPlayers (Name VARCHAR(100), UUID VARCHAR(100), End VARCHAR(100), Reason VARCHAR(100))");
                Bukkit.getConsoleSender().sendMessage("�7[�3MySQL�7] BannedPlayers Table created");

            } catch (SQLException er) {
                Bukkit.getConsoleSender().sendMessage("§7[§3MySQL§7] §4the §cBannedPlayers §4Table can not created! §cPlease contact the Plugin Developer -> §4PsychoGamer_95");
            }
        }
    }
    public static void createMuteTable() {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS MutedPlayers (Name VARCHAR(100), UUID VARCHAR(100), End VARCHAR(100), Reason VARCHAR(100))");
                Bukkit.getConsoleSender().sendMessage("�7[�3MySQL�7] MutedPlayers Table created");

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