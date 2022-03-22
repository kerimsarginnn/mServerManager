package net.kerim.main.ServerManager.Essentials;

import dev.perryplaysmc.dynamicjson.data.CColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import java.util.ArrayList;
import java.util.List;

public class commandGod implements CommandExecutor {
    private final List<Player> god = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("God")) {
            if (sender.isOp()) {
                if (args.length > 0) {
                    Player player = Bukkit.getPlayer(args[0]);
                    if (!god.contains(player)) {
                        god.add(player);
                        player.sendMessage(CColor.translateGradient("Artık hasar almıyorsun.",CColor.GREEN,CColor.YELLOW));
                        sender.sendMessage(CColor.translateGradient("%target% artık hasar almıyor.".replace("%target%",player.getDisplayName()),CColor.GREEN,CColor.YELLOW));
                    } else {
                        god.remove(player);
                        player.sendMessage(CColor.translateGradient("Artık hasar alıyorsun.",CColor.RED,CColor.ORANGE));
                        sender.sendMessage(CColor.translateGradient("%target% artık hasar alıyor.".replace("%target%",player.getDisplayName()),CColor.RED,CColor.ORANGE));
                    }
                } else {
                    Player player = (Player) sender;
                    if (!god.contains(player)) {
                        god.add(player);
                        player.sendMessage(CColor.translateGradient("Artık hasar almıyorsun.",CColor.GREEN,CColor.YELLOW));
                    } else {
                        god.remove(player);
                        player.sendMessage(CColor.translateGradient("Artık hasar alıyorsun.",CColor.RED,CColor.ORANGE));
                    }
                }
            }
        }

        return true;
    }

    public List<Player> getGod() {
        return god;
    }
}
