package net.kerim.main.ServerManager.Spawn;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class eventSpawn extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Location spawn;
    private Player target;
    private int second;
    private CommandSender sender;

    public eventSpawn(Location spawn, Player target, int second,CommandSender sender) {
        this.spawn = spawn;
        this.target = target;
        this.second = second;
        this.sender = sender;
    }
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public CommandSender getSender() {
        return sender;
    }

    public void setSender(CommandSender sender) {
        this.sender = sender;
    }
}
