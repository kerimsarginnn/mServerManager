package net.kerim.main.ServerManager.Essentials;

import dev.perryplaysmc.dynamicjson.data.CColor;
import org.bukkit.Bukkit;
import org.bukkit.WeatherType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandPWeather implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("PTime")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("sun")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        player.setPlayerWeather(WeatherType.CLEAR);
                    }
                } else if (args[0].equalsIgnoreCase("weather")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        player.setPlayerWeather(WeatherType.DOWNFALL);
                    }
                } else {
                    Player player = Bukkit.getPlayer(args[0]);
                    if (Bukkit.getOnlinePlayers().contains(player)) {
                        if (args[1].equalsIgnoreCase("sun")) {
                            player.setPlayerWeather(WeatherType.CLEAR);
                        } else if (args[1].equalsIgnoreCase("weather")) {
                            player.setPlayerWeather(WeatherType.DOWNFALL);
                        }
                    }
                }
            } else {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    String weather = String.valueOf(player.getPlayerWeather());
                    player.sendMessage(CColor.translateGradient("Hava durumun, "+weather,CColor.RED,CColor.ORANGE));
                }
            }
        }

        return true;
    }
}
