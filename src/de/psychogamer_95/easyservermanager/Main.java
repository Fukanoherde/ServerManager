package de.psychogamer_95.easyservermanager;

import de.psychogamer_95.easyservermanager.commands.CMD_GameMode;
import de.psychogamer_95.easyservermanager.commands.CMD_TimeManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private Main instance;
    private CMD_GameMode gamemode;
    private CMD_TimeManager time;

    @Override
    public void onEnable() {

        // Utils \\

        instance = this;

        // MySQL \\

        // Commands \\

        this.gamemode = new CMD_GameMode(this);
        this.time = new CMD_TimeManager(this);

        // Listener \\

        // Message \\
    }

    @Override
    public void onDisable() {

        // MySQL \\

        // Message \\
    }
}