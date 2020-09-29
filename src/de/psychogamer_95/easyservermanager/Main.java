package de.psychogamer_95.easyservermanager;

import de.psychogamer_95.easyservermanager.commands.*;
import de.psychogamer_95.easyservermanager.database.MySQL;
import de.psychogamer_95.easyservermanager.listener.*;
import de.psychogamer_95.easyservermanager.manager.MessageManager;
import de.psychogamer_95.easyservermanager.manager.PermManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    public static Main instance;
    private CMD_GameMode gm;
    private CMD_TimeManager time;
    private CMD_Ban ban;
    private CMD_Tempban temp;
    private CMD_Unban unban;
    private CMD_Weather weather;
    private CMD_Check check;
    private CMD_Mute mute;
    private CMD_Kick kick;
    private PlayerManager player;
    private ServerManager server;

    @Override
    public void onEnable() {

        // Utils \\

        instance = this;
        if(!new File(getDataFolder(), "config.yml").exists()) {
            saveResource("config.yml", true);
        }

        // Files \\

        PermManager.loadFile();
        MessageManager.loadFile();

        // MySQL \\

        MySQL.setStandardMySQL();
        MySQL.readMySQL();
        MySQL.Connect();
        MySQL.createBanTable();
        MySQL.createMuteTable();

        // Commands \\

        this.gm = new CMD_GameMode(this);
        this.time = new CMD_TimeManager(this);
        this.ban = new CMD_Ban(this);
        this.temp = new CMD_Tempban(this);
        this.unban = new CMD_Unban(this);
        this.check = new CMD_Check(this);
        this.mute = new CMD_Mute(this);
        this.kick = new CMD_Kick(this);
        this.weather = new CMD_Weather(this);

        // Listener \\

        this.player = new PlayerManager(this);
        this.server = new ServerManager(this);

    }

    @Override
    public void onDisable() {

        MySQL.close();
    }

    public static Main getSystem () {
        return instance;
    }
    public String Prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("EasyServerManager.Chat-Prefix"));
    public String MOTDHeader = ChatColor.translateAlternateColorCodes('&', getConfig().getString("MOTD.Header"));
    public String MOTDFooter = ChatColor.translateAlternateColorCodes('&', getConfig().getString("MOTD.Footer"));
    public boolean AllowJoinMessage = getConfig().getBoolean("Messages.AllowJoinMessage");
    public String JoinMessage = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.JoinMessage"));
    public boolean AllowQuitMessage = getConfig().getBoolean("Messages.AllowQuitMessage");
    public String QuitMessage = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.QuitMessage"));
    public boolean BungeeCord = getConfig().getBoolean("EasyServerManager.BungeeCord");
    public boolean AntiBlockBreak = getConfig().getBoolean("EasyServerManager.AntiBlockBreak");
    public boolean AntiBlockPlace = getConfig().getBoolean("EasyServerManager.AntiBlockPlace");
    public boolean AntiRain = getConfig().getBoolean("EasyServerManager.AntiRain");
    public boolean Wartung = getConfig().getBoolean("EasyServerManager.Wartung");
    public String MOTDWartung = ChatColor.translateAlternateColorCodes('&', getConfig().getString("MOTD.Wartung"));
    public boolean AllowGlobalServerChat = getConfig().getBoolean("EasyServerManager.AntiServerChat");
    public String LevelName = ChatColor.translateAlternateColorCodes('&', getConfig().getString("EasyServerManager.LevelName"));
    public String ServerName = ChatColor.translateAlternateColorCodes('&', getConfig().getString("EasyServerManager.ServerName"));
    public String Forum = ChatColor.translateAlternateColorCodes('&', getConfig().getString("EasyServerManager.Forum"));
    public String TeamSpeak = ChatColor.translateAlternateColorCodes('&', getConfig().getString("EasyServerManager.TeamSpeak"));
    public String NoPerm = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.NoPerm"));
    public boolean Scoreboard = getConfig().getBoolean("EasyServerManager.Scoreboard");
    public boolean AllowRain = getConfig().getBoolean("EasyServerManager.AllowRain");
    public boolean Hunger = getConfig().getBoolean("EasyServerManager.Hunger");
    public int MaxPlayers = getConfig().getInt("EasyServerManager.MaxPlayer");
    public int HungerLevel = getConfig().getInt("EasyServerManager.HungerLevel");
    public int LebensLevel = getConfig().getInt("EasyServerManager.LebensLevel");
    public String TabHeader = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Tablist.Header"));
    public String TabFooter = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Tablist.Footer"));
}