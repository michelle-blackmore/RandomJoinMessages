package net.fauxfemale.randomjoinmessages;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.Random;

public class MessageSender implements Listener {
    private RJMPlugin plugin = RJMPlugin.getPlugin(RJMPlugin.class);

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e) {
        FileConfiguration config = plugin.getConfig();

        Player p = e.getPlayer();

        boolean joinEnabled = config.getBoolean("join-message-enabled");
        boolean welcomeEnabled = config.getBoolean("welcome-message-enabled");
        boolean welcomeOverride = config.getBoolean("welcome-override");

        // Welcome message
        if (!p.hasPlayedBefore() && welcomeEnabled) {
            sendRandomMessage(MessageType.WELCOME, p, config);
        }

        // Join message
        if (!(!p.hasPlayedBefore() && welcomeOverride) && joinEnabled) {
            sendRandomMessage(MessageType.JOIN, p, config);
        }
    }

    public void sendRandomMessage (MessageType type, Player player, FileConfiguration config) {
        String prefix = config.getString(type.string + "-message-prefix");
        List<String> messages = config.getStringList(type.string + "-message-list");

        Random random = new Random();
        int index = random.nextInt(messages.size());
        String message = prefix + messages.get(index);
        message = message
                .replace("%p", player.getDisplayName())
                .replace("%w", player.getWorld().getName());

        message = ChatColor.translateAlternateColorCodes('&',
                message);

        player.sendMessage(message);
    }
}
