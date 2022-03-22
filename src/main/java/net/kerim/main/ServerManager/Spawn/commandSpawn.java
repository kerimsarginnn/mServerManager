package net.kerim.main.ServerManager.Spawn;

import net.kerim.main.ServerManager.mServerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandSpawn implements CommandExecutor {
    private final mServerManager manager;

    public commandSpawn(mServerManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Location spawn = manager.getConfig().getLocation("Locations.Spawn");

        if (command.getName().equalsIgnoreCase("Spawn")) {
            if (args.length > 0) {
                if (sender.isOp()) {
                    if (manager.getConfig().getLocation("Locations.Spawn") != null) {
                        Player player = Bukkit.getPlayer(args[0]);
                        Bukkit.getPluginManager().callEvent(new eventSpawn(spawn,player,0,sender));
                    }
                } else {manager.noPerm(sender);}
            } else {
                if (manager.getConfig().getLocation("Locations.Spawn") != null) {
                    Player player = (Player) sender;
                    Bukkit.getPluginManager().callEvent(new eventSpawn(spawn,player,3,sender));
                }
            }
        }

        return true;
    }
}
