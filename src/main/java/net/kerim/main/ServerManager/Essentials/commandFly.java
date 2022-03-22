package net.kerim.main.ServerManager.Essentials;

import dev.perryplaysmc.dynamicjson.data.CColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class commandFly implements CommandExecutor {
    private final List<Player> fly = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("Fly")) {
            if (sender.isOp()) {
                if (args.length > 0) {
                    Player player = Bukkit.getPlayer(args[0]);
                    if (!fly.contains(player)) {
                        fly.add(player);
                        player.setAllowFlight(true);
                        player.sendMessage(CColor.translateGradient("Uçuş modu aktif edildi.",CColor.GREEN,CColor.YELLOW));
                        sender.sendMessage(CColor.translateGradient("%target% için uçuş modu aktif.".replace("%target%",player.getDisplayName()),Color.GREEN,Color.YELLOW));
                    } else {
                        fly.remove(player);
                        player.setAllowFlight(false);
                        player.sendMessage(CColor.translateGradient("Uçuş modu deaktif edildi.",CColor.RED,CColor.ORANGE));
                        sender.sendMessage(CColor.translateGradient("%target% için uçuş modu deaktif.".replace("%target%",player.getDisplayName()),Color.RED,Color.ORANGE));
                    }
                } else {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        if (!fly.contains(player)) {
                            fly.add(player);
                            player.setAllowFlight(true);
                            player.sendMessage(CColor.translateGradient("Uçuş modu aktif edildi.",CColor.GREEN,CColor.YELLOW));
                        } else {
                            fly.remove(player);
                            player.setAllowFlight(false);
                            player.sendMessage(CColor.translateGradient("Uçuş modu deaktif edildi.",CColor.RED,CColor.ORANGE));
                        }
                    }
                }
            }
        }

        return true;
    }
}
