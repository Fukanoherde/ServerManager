package de.psychogamer_95.easyservermanager.commands;

import de.psychogamer_95.easyservermanager.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_GameMode implements CommandExecutor {

    public Main plugin;
    public CMD_GameMode (Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("gamemode").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("0")) {
                        if (p.hasPermission("")) {
                            p.setGameMode(GameMode.SURVIVAL);
                            p.sendMessage(plugin.Prefix + "§3Du bist im §2SURVIVAL §3Modus");
                            return true;
                        } else {

                            // NoPerm \\

                            p.sendMessage(plugin.Prefix + "");
                        }
                    }
                    if (args[0].equalsIgnoreCase("1")) {
                        if (p.hasPermission("")) {
                            p.setGameMode(GameMode.CREATIVE);
                            p.sendMessage(plugin.Prefix + "§3Du bist im §2CREATIVE §3Modus");
                            return true;
                        } else {

                            // NoPerm \\

                            p.sendMessage(plugin.Prefix + "");
                        }
                    }
                    if (args[0].equalsIgnoreCase("2")) {
                        if (p.hasPermission("")) {
                            p.setGameMode(GameMode.ADVENTURE);
                            p.sendMessage(plugin.Prefix + "§3Du bist im §2ADVENTURE §3Modus");
                            return true;
                        } else {

                            // NoPerm \\

                            p.sendMessage(plugin.Prefix + "");
                        }
                    }
                    if (args[0].equalsIgnoreCase("3")) {
                        if (p.hasPermission("")) {
                            p.setGameMode(GameMode.SPECTATOR);
                            p.sendMessage(plugin.Prefix + "§3Du bist im §2SPECTATOR §3Modus");
                            return true;
                        } else {

                            // NoPerm \\

                            p.sendMessage(plugin.Prefix + "");
                        }
                    }
                } else {
                    p.sendMessage("");
                }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("0")) {
                    Player target = (Player) Bukkit.getPlayer(args[0]);
                    if (p.hasPermission("")) {
                        if (target != null) {
                            target.setGameMode(GameMode.SURVIVAL);
                            target.sendMessage(plugin.Prefix + "");
                            p.sendMessage(plugin.Prefix + "");
                            return true;
                        } else {
                            p.sendMessage(plugin.Prefix + "§cDer Spieler ist nicht Online!");
                        }
                    } else {

                        // NoPerm \\

                        p.sendMessage(plugin.Prefix + "");
                    }
                }
            } else {

            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("0")) {
                    Player target = (Player) Bukkit.getPlayer(args[1]);
                    if (p.hasPermission("")) {
                        if (target != null) {
                            target.setGameMode(GameMode.SURVIVAL);
                            target.sendMessage(plugin.Prefix + "");
                            p.sendMessage(plugin.Prefix + "");
                            return true;
                        } else {
                            p.sendMessage(plugin.Prefix + "§cDer Spieler ist nicht Online!");
                        }
                    } else {

                        // NoPerm \\

                        p.sendMessage(plugin.Prefix + "");
                    }
                }
            } else {

            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("1")) {
                    Player target = (Player) Bukkit.getPlayer(args[1]);
                    if (p.hasPermission("")) {
                        if (target != null) {
                            target.setGameMode(GameMode.CREATIVE);
                            target.sendMessage(plugin.Prefix + "");
                            p.sendMessage(plugin.Prefix + "");
                            return true;
                        } else {
                            p.sendMessage(plugin.Prefix + "§cDer Spieler ist nicht Online!");
                        }
                    } else {

                        // NoPerm \\

                        p.sendMessage(plugin.Prefix + "");
                    }
                }
            } else {

            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("2")) {
                    Player target = (Player) Bukkit.getPlayer(args[1]);
                    if (p.hasPermission("")) {
                        if (target != null) {
                            target.setGameMode(GameMode.ADVENTURE);
                            target.sendMessage(plugin.Prefix + "");
                            p.sendMessage(plugin.Prefix + "");
                            return true;
                        } else {
                            p.sendMessage(plugin.Prefix + "§cDer Spieler ist nicht Online!");
                        }
                    } else {

                        // NoPerm \\

                        p.sendMessage("");
                    }
                }
            } else {

            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("3")) {
                    Player target = (Player) Bukkit.getPlayer(args[1]);
                    if (p.hasPermission("")) {
                        if (target != null) {
                            target.setGameMode(GameMode.SPECTATOR);
                            target.sendMessage(plugin.Prefix + "");
                            p.sendMessage(plugin.Prefix + "");
                            return true;
                        } else {
                            p.sendMessage(plugin.Prefix + "§cDer Spieler ist nicht Online!");
                        }
                    } else {

                        // NoPerm \\

                        p.sendMessage(plugin.Prefix + "");
                    }
                }
            } else {

            }
        } else {
            Bukkit.getConsoleSender().sendMessage(plugin.Prefix + "§4ERROR: §cThis command is for the real player!");
        }
        return false;
    }
}