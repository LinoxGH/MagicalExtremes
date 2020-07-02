package io.github.linoxgh.magicalextremes.Implementation.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class HelpCommand {

    protected static void sendCommand(CommandSender sender, boolean isPlayer) {
        String[] messages = new String[2];

        String header = ChatColor.AQUA + ".*O*.-----{ " + ChatColor.LIGHT_PURPLE + "Magical " + ChatColor.GOLD + "Extremes " + ChatColor.AQUA + "}-----.*O*.";
        String reloadHelp = ChatColor.DARK_AQUA + ">> " + ChatColor.GREEN + "/me reload " + ChatColor.DARK_AQUA + ": " + ChatColor.GRAY + "Reloads the plugin configuration.";

        messages[0] = isPlayer ? header : ChatColor.stripColor(header);
        messages[1] = isPlayer ? reloadHelp : ChatColor.stripColor(reloadHelp);

        sender.sendMessage(messages);
    }
}
