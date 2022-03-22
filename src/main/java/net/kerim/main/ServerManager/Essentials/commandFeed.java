package net.kerim.main.ServerManager.Essentials;

import dev.perryplaysmc.dynamicjson.data.CColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandFeed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("Feed")) {
            if (sender.isOp()) {
                if (args.length > 0) {
                    Player player = Bukkit.getPlayer(args[0]);
                    player.sendMessage(CColor.translateGradient("Açlığın dolduruldu.",CColor.RED,CColor.ORANGE));
                    sender.sendMessage(CColor.translateGradient("%target% isimli oyuncunun açlığın dolduruldu.".replace("%target%", player.getDisplayName()),CColor.RED,CColor.ORANGE));
                    player.setFoodLevel(20);
                } else {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        player.sendMessage(CColor.translateGradient("Açlığın dolduruldu.",CColor.RED,CColor.ORANGE));
                        player.setFoodLevel(20);
                    }
                }
            }
        }

        return true;
    }
}