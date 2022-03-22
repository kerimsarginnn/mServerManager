package net.kerim.main.ServerManager.Spawn;

import dev.perryplaysmc.dynamicjson.data.CColor;
import net.kerim.main.ServerManager.mServerManager;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class listenerSpawn implements Listener {
    private final mServerManager manager;
    private final HashMap<Player,Integer> hastp = new HashMap<>();

    public listenerSpawn(mServerManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void spawnEvent(eventSpawn event) {
        Player player = event.getTarget();
        Location spawn = event.getSpawn();
        int sec = event.getSecond();
        CommandSender sender = event.getSender();

        if (sec == 0) {
            player.sendMessage(CColor.translateGradient("%sender% tarafından spawna ışınlandın!".replace("%sender%", sender.getName()),CColor.YELLOW,CColor.GREEN));
            sender.sendMessage(CColor.translateGradient("%target% isimli oyuncu spawna ışınlandı!".replace("%target%",player.getDisplayName()),CColor.YELLOW,CColor.GREEN));
            player.teleport(spawn);
        } else {
            hastp.put(player,1);
            player.sendMessage(CColor.translateGradient("Işınlanma başladı lütfen hareket etme.",CColor.YELLOW,CColor.GREEN));
            new BukkitRunnable() {
                int i = 4;
                @Override
                public void run() {
                    try {
                        if (i == 4) {
                            player.sendTitle(CColor.translateGradient("Işınlanmaya,",CColor.DARK_PURPLE,CColor.LIGHT_PURPLE),CColor.translateGradient("3",CColor.RED,CColor.ORANGE));
                        } if (i == 3) {
                            player.sendTitle(CColor.translateGradient("Işınlanmaya,",CColor.DARK_PURPLE,CColor.LIGHT_PURPLE),CColor.translateGradient("2",CColor.ORANGE,CColor.YELLOW));
                        } if (i == 2) {
                            player.sendTitle(CColor.translateGradient("Işınlanmaya,",CColor.DARK_PURPLE,CColor.LIGHT_PURPLE),CColor.translateGradient("1",CColor.YELLOW,CColor.GREEN));
                        }
                        i--;
                        if (hastp.get(player) == 2) {
                            player.sendMessage(CColor.translateGradient("Hareket ettiğin için ışınlanma iptal edildi.",CColor.RED,CColor.ORANGE));
                            player.sendTitle(CColor.translateGradient("İptal Edildi!",CColor.DARK_RED,CColor.RED),"");
                            hastp.remove(player,2);
                            cancel();
                        }
                        if (hastp.get(player) == 3) {
                            player.sendMessage(CColor.translateGradient("Hasar adlığın için ışınlanma iptal edildi.",CColor.RED,CColor.ORANGE));
                            player.sendTitle(CColor.translateGradient("İptal Edildi!",CColor.DARK_RED,CColor.RED),"");
                            hastp.remove(player,3);
                            cancel();
                        }
                        if (i == 0) {
                            player.teleport(spawn);
                            player.sendTitle(CColor.translateGradient("Işınlanma Başarılı!",CColor.DARK_GREEN,CColor.GREEN),"");
                            player.sendMessage(CColor.translateGradient("Başarıyla spawna ışınlandın!",CColor.YELLOW,CColor.GREEN));
                            hastp.remove(player,1);
                            cancel();
                        }
                    } catch (Exception e) {
                        //
                    }
                }
            }.runTaskTimer(manager,1L,20L);
        }
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
