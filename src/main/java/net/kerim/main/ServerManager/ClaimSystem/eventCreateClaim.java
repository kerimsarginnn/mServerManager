package net.kerim.main.ServerManager.ClaimSystem;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class eventCreateClaim extends Event {
    private static final HandlerList handlers = new HandlerList();
    private String name;
    private Player player;
    private Chunk chunk;
    private Location location;

    public eventCreateClaim(String name,Player player,Chunk chunk,Location location){
        this.name = name;
        this.player = player;
        this.chunk = chunk;
        this.location = location;
    }
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Chunk getChunk() {
        return chunk;
    }

    public void setChunk(Chunk chunk) {
        this.chunk = chunk;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
