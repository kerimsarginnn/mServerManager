package net.kerim.main.ServerManager.ClaimSystem;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class listenerRightCB implements Listener {
    private final menuSubManageClaim claim;

    public listenerRightCB(menuSubManageClaim claim) {
        this.claim = claim;
    }

    @EventHandler
    public void clickEvent(PlayerInteractEvent event) {
        if (!(dataClaim.get().getConfigurationSection("").getKeys(false).size() == 0)) {
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Block block = event.getClickedBlock();
                Player player = event.getPlayer();
                Location location = block.getLocation();
                int s = dataClaim.get().getConfigurationSection("").getKeys(false).size();
                int i = 0;
                do {
                    int x = dataClaim.get().getInt(i+".Block.x");
                    int y = dataClaim.get().getInt(i+".Block.y");
                    int z = dataClaim.get().getInt(i+".Block.z");
                    World world = Bukkit.getWorld((String) Objects.requireNonNull(dataClaim.get().get(i + ".World")));
                    Location location1 = new Location(world,x,y,z);
                    if (location1.equals(location)) {
                        claim.subManage(player,i,(String) dataClaim.get().get(i+".firstName"),(String) dataClaim.get().get(i+".Owner"));
                        event.setCancelled(true);
                    }
                    i++;
                } while (i < s);
            }
        }
    }
}
