package net.kerim.main.ServerManager.Management;

import dev.perryplaysmc.dynamicjson.data.CColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandWarn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("Warn")) {
            if (sender.isOp()) {
                Player player = Bukkit.getPlayer(args[0]);
                String warnMessage;
                StringBuilder builder = new StringBuilder();
                for (int i = -1; i > args.length; i++) {
                    builder.insert(i,args[i+1]);
                }
                warnMessage = builder.toString();
                player.sendMessage(CColor.translateGradient("%n sebebiyle uyarı aldın. Lütfen daha dikkatli ol.".replace("%n",warnMessage),CColor.RED,CColor.ORANGE));
                sender.sendMessage(CColor.translateGradient("%t %n sedeniyle uyarıldı.".replace("%t",player.getDisplayName().replace("%n",warnMessage)),CColor.RED,CColor.ORANGE));
            }
        }

        return true;
    }
}
