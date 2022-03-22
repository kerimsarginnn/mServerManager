package net.kerim.main.ServerManager.Essentials;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class commandTime implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("Day")) {
            if (sender.isOp()) {
                Bukkit.getWorld("world").setTime(1000);
            }
        }

        return true;
    }
}
