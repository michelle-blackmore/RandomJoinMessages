package net.fauxfemale.randomjoinmessages;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/*
Main class for the plugin
 */

public final class RJMPlugin extends JavaPlugin implements Listener {

    public static final double VERSION = 1.0;

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "Started!");

        /*
        Check if there is a config file present, and if not, load the default config from plugin resources
         */
        getConfig().options().copyDefaults();

        /*
        If a config file is not present, save the loaded config to that directory.
         */
        saveDefaultConfig();

        /*
        Register listeners
         */
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new MessageSender(), this);
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Stopped!");
    }

    /*
    Interpret commands
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("rjm")) {
            if (sender.isOp()) {
                if (args.length == 0) {

                    String info = "RandomJoinMessages v" + VERSION + "by Shelly B";

                    // Show plugin info
                    if (sender instanceof ConsoleCommandSender) {
                        getLogger().log(Level.INFO, info);

                    } else if (sender instanceof Player) {
                        Player player = (Player) sender;
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a") + info);
                    }

                } else if (args[0].equalsIgnoreCase("reload")) {

                    // Reload config
                    reloadConfig();

                    if (sender instanceof ConsoleCommandSender) {
                        getLogger().log(Level.INFO, "Reloaded the RandomJoinMessages config!");

                    } else if (sender instanceof Player) {
                        Player player = (Player) sender;
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReloaded the RandomJoinMessages config!"));
                    }
                }
            } else {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to edit Random Join Messages!"));
            }
        }

        return true;
    }
}
