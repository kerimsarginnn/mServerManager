package net.kerim.main.ServerManager.Trader;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class eventCrateTrader extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Location location;
    private String name;

    public eventCrateTrader(Player player, Location location, String name) {
        this.player = player;
        this.location = location;
        this.name = name;
    }
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
