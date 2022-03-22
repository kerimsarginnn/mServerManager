package net.kerim.main.ServerManager.Spawn;

import dev.perryplaysmc.dynamicjson.data.CColor;
import net.kerim.main.ServerManager.mServerManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandSetSpawn implements CommandExecutor {
    private final mServerManager manager;

    public commandSetSpawn(mServerManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("SetSpawn")) {
            if (sender instanceof Player) {
                if (sender.isOp()) {
                    Player player = (Player) sender;
                    Location spawn = player.getLocation();
                    String loc = "%world%, %x%, %y%, %z%".replace("%world%",spawn.getWorld().getName()).replace("%x%",String.valueOf(spawn.getBlockX()))
                                    .replace("%y%",String.valueOf(spawn.getBlockY())).replace("%z%",String.valueOf(spawn.getBlockY()));
                    player.sendTitle(CColor.translateGradient("Spawn Belirlendi!",CColor.DARK_PURPLE,CColor.LIGHT_PURPLE),CColor.translateGradient(loc,CColor.DARK_PURPLE,CColor.LIGHT_PURPLE));
                    player.sendMessage(CColor.translateGradient("%l% lokasyonuna spawn belirlendi".replace("%l%",loc),CColor.DARK_GREEN,CColor.GREEN));
                    manager.getConfig().set("Locations.Spawn",spawn);
                    manager.saveConfig();
                } else {manager.noPerm(sender);}
            } else {manager.noPlayer(sender);}
        }

        return true;
    }
}
