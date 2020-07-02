package io.github.linoxgh.magicalextremes.Implementation.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        boolean isPlayer = sender instanceof Player;
        if (args.length == 1) {
            if (args[0].equals("reload") && sender.hasPermission("magicalextremes.commands.reload")) {
                ReloadCommand.sendCommand(sender, isPlayer);
                return true;
            }
        }
        HelpCommand.sendCommand(sender, isPlayer);
        return true;
    }
}
