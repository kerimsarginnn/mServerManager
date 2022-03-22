package net.kerim.main.ServerManager.Trader;

import dev.perryplaysmc.dynamicjson.data.CColor;
import net.kerim.main.ServerManager.mServerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class listenerCreateTrader implements Listener {
    private final mServerManager manager;

    public listenerCreateTrader(mServerManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void createTrader(eventCrateTrader event) {
        String pname = event.getPlayer().getDisplayName();
        Location location = event.getLocation();
        String name = event.getName();
        double y = location.getBlockY()-0.9;
        location.setY(y);
        location.add(0,1.1,0).getBlock().setType(Material.CHEST);
    }
}
