package net.kerim.main.ServerManager.ClaimSystem;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class eventBack extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Integer no;
    String name;
    String owner;

    public eventBack(Player player, Integer no, String name, String owner) {
        this.player = player;
        this.no = no;
        this.name = name;
        this.owner = owner;
    }
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
