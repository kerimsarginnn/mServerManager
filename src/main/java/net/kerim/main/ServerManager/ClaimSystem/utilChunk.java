package net.kerim.main.ServerManager.ClaimSystem;

import org.bukkit.Location;
import org.bukkit.World;

public class utilChunk {

    static int x;
    static int z;

    static chunkStatus fromLocation(Location location, int no) {
        x = location.getChunk().getX();
        z = location.getChunk().getZ();
        World world = location.getWorld();
        for (int i = 0; i <26 ; i++) {
            if (dataClaim.get().get(no+".Chunks") != null) {
                dataClaim.get().get(no+".Chunks."+i);
            } else {

            }
        }
        return null;
    }
}
