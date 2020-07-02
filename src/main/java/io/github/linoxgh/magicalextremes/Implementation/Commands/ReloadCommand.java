package io.github.linoxgh.magicalextremes.Implementation.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import io.github.linoxgh.magicalextremes.MagicalExtremesPlugin;

public class ReloadCommand {

    protected static void sendCommand(CommandSender sender, boolean isPlayer) {
        String message = ChatColor.AQUA + "Reloading the plugin configuration...";
        sender.sendMessage(isPlayer ? message : ChatColor.stripColor(message));

        MagicalExtremesPlugin.getInstance().reloadConfig();
    }
}
