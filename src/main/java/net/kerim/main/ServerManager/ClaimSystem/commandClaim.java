package net.kerim.main.ServerManager.ClaimSystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandClaim implements CommandExecutor {
    private final menuManageClaim claim;

    public commandClaim(menuManageClaim claim) {
        this.claim = claim;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("Claim")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                claim.manageClaim(player);
            }
        }

        return true;
    }
}
