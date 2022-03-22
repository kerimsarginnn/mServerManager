package net.kerim.main.ServerManager.ClaimSystem;

import dev.perryplaysmc.dynamicjson.data.CColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class listenerBreakCB implements Listener {
    @EventHandler
    public void breaK(BlockBreakEvent event) {
        if (!(dataClaim.get().getConfigurationSection("").getKeys(false).size() == 0)) {
            Player p1 = event.getPlayer();
            Block block = event.getBlock();
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
                    event.setCancelled(true);
                    p1.sendMessage(CColor.translateGradient("Bu bir claim bloğu, bunu kıramazsın!",CColor.RED,CColor.ORANGE));
                    break;
                }
                i++;
            } while (i < s);
        }
    }
}
