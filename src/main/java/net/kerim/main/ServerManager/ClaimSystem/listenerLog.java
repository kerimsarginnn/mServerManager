package net.kerim.main.ServerManager.ClaimSystem;

import dev.perryplaysmc.dynamicjson.data.CColor;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class listenerLog implements Listener {
    @EventHandler
    public void walkEvent(PlayerMoveEvent event){
        if (!(dataClaim.get().getConfigurationSection("").getKeys(false).size() == 0)) {
            Player player = event.getPlayer();
            int s = dataClaim.get().getConfigurationSection("").getKeys(false).size();
            int i = 0;
            do {
                World world = Bukkit.getWorld(String.valueOf(dataClaim.get().get(i+".World")));
                int x = dataClaim.get().getInt(i+".Block.x");
                int y = dataClaim.get().getInt(i+".Block.y");
                int z = dataClaim.get().getInt(i+".Block.z");
                Location location = new Location(world,x,y,z);
                Chunk chunk = location.getChunk();
                if (event.getTo().getChunk().equals(chunk)) {
                    if (!dataClaim.get().get(i+".Owner").equals(player.getDisplayName())){
                        event.setCancelled(true);
                        player.sendMessage(CColor.translateGradient("Bu claime girmek için gerekli izne sahip değilsin!",CColor.RED,CColor.ORANGE));
                    }
                }
                i++;
            } while (i < s);
        }
    }
    @EventHandler
    public void breakEvent(BlockBreakEvent event) {
        if (!(dataClaim.get().getConfigurationSection("").getKeys(false).size() == 0)) {
            Player player = event.getPlayer();
            Block block = event.getBlock();
            int s = dataClaim.get().getConfigurationSection("").getKeys(false).size();
            int i = 0;
            do {
                World world = Bukkit.getWorld((String) Objects.requireNonNull(dataClaim.get().get(i + ".World")));
                int x = dataClaim.get().getInt(i+".Block.x");
                int y = dataClaim.get().getInt(i+".Block.y");
                int z = dataClaim.get().getInt(i+".Block.z");
                Location location = new Location(world,x,y,z);
                Chunk chunk = location.getChunk();
                if (block.getLocation().getChunk().equals(chunk)) {
                    if (!dataClaim.get().get(i+".Owner").equals(player.getDisplayName())){
                        event.setCancelled(true);
                        player.sendMessage(CColor.translateGradient("Bu claimde blok kırmak için gerekli izne sahip değilsin!",CColor.RED,CColor.ORANGE));
                    }
                }
                i++;
            } while (i < s);
        }
    }
    @EventHandler
    public void placeBlock(BlockPlaceEvent event) {
        if (!(dataClaim.get().getConfigurationSection("").getKeys(false).size() == 0)) {
            Player player = event.getPlayer();
            Block block = event.getBlock();
            int s = dataClaim.get().getConfigurationSection("").getKeys(false).size();
            int i = 0;
            do {
                World world = Bukkit.getWorld((String) Objects.requireNonNull(dataClaim.get().get(i + ".World")));
                int x = dataClaim.get().getInt(i+".Block.x");
                int y = dataClaim.get().getInt(i+".Block.y");
                int z = dataClaim.get().getInt(i+".Block.z");
                Location location = new Location(world,x,y,z);
                Chunk chunk = location.getChunk();
                if (block.getLocation().getChunk().equals(chunk)) {
                    if (!dataClaim.get().get(i+".Owner").equals(player.getDisplayName())){
                        event.setCancelled(true);
                        player.sendMessage(CColor.translateGradient("Bu claime blok koymak için gerekli izne sahip değilsin!",CColor.RED,CColor.ORANGE));
                    }
                }
                i++;
            } while (i < s);
        }
    }
    @EventHandler
    public void dropE(PlayerDropItemEvent event) {
        if (!(dataClaim.get().getConfigurationSection("").getKeys(false).size() == 0)) {
            Player player = event.getPlayer();
            Location locatio1n = player.getLocation();
            int s = dataClaim.get().getConfigurationSection("").getKeys(false).size();
            int i = 0;
            do {
                World world = Bukkit.getWorld((String) Objects.requireNonNull(dataClaim.get().get(i + ".World")));
                int x = dataClaim.get().getInt(i+".Block.x");
                int y = dataClaim.get().getInt(i+".Block.y");
                int z = dataClaim.get().getInt(i+".Block.z");
                Location location = new Location(world,x,y,z);
                Chunk chunk = location.getChunk();
                if (locatio1n.getChunk().equals(chunk)) {
                    if (!dataClaim.get().get(i+".Owner").equals(player.getDisplayName())){
                        event.setCancelled(true);
                        player.sendMessage(CColor.translateGradient("Bu claime eşya atmak için gerekli izne sahip değilsin!",CColor.RED,CColor.ORANGE));
                    }
                }
                i++;
            } while (i < s);
        }
    }
}
