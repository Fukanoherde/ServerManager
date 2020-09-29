package de.psychogamer_95.easyservermanager.manager;

import de.psychogamer_95.easyservermanager.Main;

public class MessageManager {

    private static FileWriter fileWriter = new FileWriter(Main.getSystem().getDataFolder().getPath(), "Messages.yml");
    public static void  loadFile(){
        setValue("Plugin.Entwickler", "PsychoGamer_95");
        setValue("Message.NoPerm", "easyservermanager.*");
        setValue("Message.Use_Check", "easyservermanager.ban");
        setValue("Message.Use_GameMode", "easyservermanager.tempban");
        setValue("Message.Use_Ban", "easyservermanager.check");
        setValue("Message.Use_Gamemode", "easyservermanager.gamemode");
        setValue("Message.Use_Mute", "easyservermanager.gamemode.other");
        setValue("Message.Use_Tempban", "easyservermanager.mute");
        setValue("Message.Use_TimeManager", "easyservermanager.time");
        setValue("Message.Use_Unban", "easyservermanager.unban");

        setValue("Message.MySQL_Connect", "&7[&3MySQL&7] &eThe database succesfully connected!");
        setValue("Message.MySQL_Connect_Error", "&7[&3MySQL&7] &4ERROR: the connection has been error!");
        setValue("Message.MySQL_Close", "&7[&3MySQL&7] &ethe Connection succesfully close!");
        setValue("Message.MySQL_Close_Error", "&7[&3MySQL&7] &4the MySQL Connection can not Close!");
        setValue("Message.MySQL_CreateBanTable", "&7[&3MySQL&7] &eBannedPlayers Table created");
        setValue("Message.MySQL_CreateBanTable_Error", "&7[&3MySQL&7] &4the &cBannedPlayers &4Table can not created!");
        setValue("Message.MySQL_CreateMuteTable", "&7[&3MySQL&7] &eMutedPlayers Table created");
        setValue("Message.MySQL_CreateMuteTable_Error", "&7[&3MySQL&7] &4the &cMutedPlayers &4Table can not created!");

        setValue("Message.UseKick", "&bUse: &7/kick <Player> <Reason>");
        setValue("Message.PlayerNotOnlineKick", "&cThe specified player is not online");
        setValue("Message.YourselfKick", "&cWhy do you want to kick yourself");
        setValue("Message.Use_Ban", "easyservermanager.check");
        setValue("Message.Use_Gamemode", "easyservermanager.gamemode");
        setValue("Message.Use_Mute", "easyservermanager.gamemode.other");
        setValue("Message.Use_Tempban", "easyservermanager.mute");
        setValue("Message.Use_TimeManager", "easyservermanager.time");
        setValue("Message.Use_Unban", "easyservermanager.unban");
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