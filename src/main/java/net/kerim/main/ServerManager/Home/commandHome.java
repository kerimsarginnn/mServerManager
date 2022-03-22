package net.kerim.main.ServerManager.Home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandHome implements CommandExecutor {
    private final menuHome home;

    public commandHome(menuHome home) {
        this.home = home;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("Ev")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                home.menu(player);
            }
        }

        return true;
    }
}
