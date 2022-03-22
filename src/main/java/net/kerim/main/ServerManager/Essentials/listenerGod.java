package net.kerim.main.ServerManager.Essentials;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class listenerGod implements Listener {
    private final commandGod god;

    public listenerGod(commandGod god) {
        this.god = god;
    }
    @EventHandler
    public void godListen(EntityDamageEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            if (god.getGod().contains(player)){
                event.setCancelled(true);
            }
        }
    }
}
