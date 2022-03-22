package net.kerim.main.ServerManager.Trader;

import dev.perryplaysmc.dynamicjson.data.CColor;
import net.kerim.main.ServerManager.mServerManager;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class listenerTPTrader implements Listener {
    private final HashMap<Player,Integer> hastp = new HashMap<>();
    private final mServerManager manager;
    int secs = 3;

    public listenerTPTrader(mServerManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void evnetTp(eventTPTrader event) {
        Player player = event.getPlayer();
        Location location = manager.getConfig().getLocation("Locations.Spawn");
        hastp.put(player,1);
        player.sendMessage(CColor.translateGradient("Işınlanma başladı lütfen hareket etme.",CColor.YELLOW,CColor.GREEN));

        new BukkitRunnable() {
            int i = secs+1;
            @Override
            public void run() {
                try {
                    i--;
                    String teleporting = "Işınlanılıyor, %s".replace("%s",String.valueOf(i));
                    player.sendTitle(CColor.translateGradient(teleporting,CColor.RED,CColor.ORANGE),"");
                    if (hastp.get(player) == 2) {
                        player.sendMessage(CColor.translateGradient("Hareket ettiğin için ışınlanma iptal edildi.",CColor.RED,CColor.ORANGE));
                        player.sendTitle(CColor.translateGradient("Işınlanma İptal Edildi!",CColor.DARK_RED,CColor.RED),"");
                        hastp.remove(player);
                        cancel();
                    }
                    if (hastp.get(player) == 3) {
                        player.sendMessage(CColor.translateGradient("Hasar adlığın için ışınlanma iptal edildi.",CColor.RED,CColor.ORANGE));
                        player.sendTitle(CColor.translateGradient("Işınlanma İptal Edildi!",CColor.DARK_RED,CColor.RED),"");
                        hastp.remove(player);
                        cancel();
                    }
                    if (i == 0) {
                        player.teleport(location);
                        player.sendTitle(CColor.translateGradient("Işınlanma Başarılı!",CColor.DARK_GREEN,CColor.GREEN),"");
                        player.sendMessage(CColor.translateGradient("Pazar alanına başarıyla ışınlandın!",CColor.YELLOW,CColor.GREEN));
                        hastp.remove(player);
                        cancel();
                    }
                } catch (Exception e) {
                    //
                }
            }
        }.runTaskTimer(manager,1L,20L);

    }
    @EventHandler
    public void moveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (hastp.containsKey(player)) {
            hastp.replace(player,2);
        }
    }
    @EventHandler
    public void damageEvent(EntityDamageEvent event) {
        if (event.getEntity().getType().equals(EntityType.PLAYER)) {
            Player player = (Player) event.getEntity();
            if (hastp.containsKey(player)) {
                hastp.replace(player,3);
            }
        }
    }
}
