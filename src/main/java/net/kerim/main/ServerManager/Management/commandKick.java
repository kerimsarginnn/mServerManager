package net.kerim.main.ServerManager.Management;

import dev.perryplaysmc.dynamicjson.data.CColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandKick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("Kick")) {
            if (sender.isOp()) {
                if (args.length > 0) {
                    StringBuilder builder = new StringBuilder();
                    String kickMessage;
                    Player player = Bukkit.getPlayer(args[0]);
                    for (int i = -1; i > args.length; i++) {
                        builder.insert(i,args[i+1]);
                    }
                    kickMessage = builder.toString();
                    player.kickPlayer(CColor.translateGradient(kickMessage,CColor.RED,CColor.ORANGE));
                    sender.sendMessage(CColor.translateGradient("%t %n nedeniyle atıldı.".replace("%t", player.getDisplayName()).replace("%n",kickMessage),CColor.RED,CColor.ORANGE));
                }
            }
        }
        return true;
    }
}
