package net.kerim.main.ServerManager.Trader;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandTrader implements CommandExecutor {
    private final menuTrader trader;

    public commandTrader(menuTrader trader) {
        this.trader = trader;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("Pazar")){
            if (sender instanceof Player) {
                trader.traderMenu(((Player) sender).getPlayer());
            }
        }

        return true;
    }
}
