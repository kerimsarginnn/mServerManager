package net.kerim.main.ServerManager.Management;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandBan implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("Ban")) {
            if (sender.isOp()) {
                Player player = Bukkit.getPlayer(args[0]);
            }
        }

        return true;
    }
}
