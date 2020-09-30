package de.psychogamer_95.easyservermanager.manager;

import de.psychogamer_95.easyservermanager.Main;

public class PermManager {

    private static FileWriter fileWriter = new FileWriter(Main.getSystem().getDataFolder().getPath(), "Perms.yml");
    public static void  loadFile(){
        setValue("Plugin.Entwickler", "PsychoGamer_95");
        setValue("Perms.All", "easyservermanager.*");
        setValue("Perms.Ban", "easyservermanager.ban");
        setValue("Perms.Tempban", "easyservermanager.tempban");
        setValue("Perms.Check", "easyservermanager.check");
        setValue("Perms.Gamemode", "easyservermanager.gamemode");
        setValue("Perms.Gamemode_Other", "easyservermanager.gamemode.other");
        setValue("Perms.Mute", "easyservermanager.mute");
        setValue("Perms.Time", "easyservermanager.time");
        setValue("Perms.Unban", "easyservermanager.unban");
        setValue("Perms.Kick", "easyservermanager.kick");
        setValue("Perms.Weather", "easyservermanager.weather");
    }
    private static void setValue(String valuePath, String value){
        if (!fileWriter.valueExist(valuePath)){
            fileWriter.setValue(valuePath, value);
            fileWriter.save();
        }
    }
    public static Object getValue(String valuePath){
        return fileWriter.getObject(valuePath);
    }
}