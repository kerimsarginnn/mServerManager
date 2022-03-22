package net.kerim.main.ServerManager.Essentials;

import org.bukkit.Bukkit;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class commandWeather implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("Sun")) {
            if (sender.isOp()) {
                WeatherType type = WeatherType.CLEAR;
            }
        } else if (command.getName().equalsIgnoreCase("Rain")) {
            Bukkit.getWorld("world").setStorm(true);
        }

        return true;
    }
}
