package net.kerim.main.ServerManager.ClaimSystem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class listenerBack implements Listener {
    private final menuSubManageClaim claim;

    public listenerBack(menuSubManageClaim claim) {
        this.claim = claim;
    }

    @EventHandler
    public void listen(eventBack event) {
        Player player = event.getPlayer();
        Integer no = event.getNo();
        String name = event.getName();
        String owner = event.getOwner();
        claim.subManage(player,no,name,owner);
    }
}
