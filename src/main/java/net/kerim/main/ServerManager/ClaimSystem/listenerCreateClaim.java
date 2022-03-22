package net.kerim.main.ServerManager.ClaimSystem;

import com.sun.org.apache.xpath.internal.operations.Bool;
import dev.perryplaysmc.dynamicjson.data.CColor;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import net.kerim.main.ServerManager.Home.dataHome;
import net.kerim.main.ServerManager.mServerManager;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class listenerCreateClaim implements Listener {
    private final mServerManager manager;

    public listenerCreateClaim(mServerManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void createClaim(eventCreateClaim event) {
        Player player = event.getPlayer();
        Chunk chunk = event.getChunk();
        Location location = event.getLocation();
        String name = event.getName();
        Boolean bo = false;

        String world = location.getWorld().getName();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        int cx = chunk.getX();
        int cz = chunk.getZ();

        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss");
        String dateTime = formatter.format(time);
        String creator = "Kurucu, %p".replace("%p",player.getDisplayName());
        String ctime = "Kuruluş, %d".replace("%d",time.format(formatter));

        new BukkitRunnable() {
            int j = 0;
            @Override
            public void run() {
                if (dataClaim.get().get(String.valueOf(j)) == null) {
                    dataClaim.get().set(j+".Owner",player.getDisplayName());
                    dataClaim.get().set(j+".name",name);
                    dataClaim.get().set(j+".firstName",name);
                    dataClaim.get().set(j+".World",world);
                    dataClaim.get().set(j+".Block.x",x);
                    dataClaim.get().set(j+".Block.y",y);
                    dataClaim.get().set(j+".Block.z",z);
                    dataClaim.get().set(j+".Chunk.x",cx);
                    dataClaim.get().set(j+".Chunk.z",cz);
                    dataClaim.get().set(j+".CreateDate",ctime);
                    dataClaim.get().set(j+".DeleteDate.D",29);
                    dataClaim.get().set(j+".DeleteDate.H",23);
                    dataClaim.get().set(j+".DeleteDate.M",59);
                    dataClaim.save();
                    cancel();
                }else {
                    j++;
                }
            }
        }.runTaskTimer(manager,0L,0L);

        Location holoLoc = new Location(Bukkit.getWorld(world),x+0.50,y+3.3,z+0.50);
        String delTime = "Kalan süre, %d Gün %h Saat %m Dk".replace("%d",String.valueOf(29)).replace("%h",String.valueOf(23)).replace("%m",String.valueOf(59));

        List<String> lines = new ArrayList<>();
        lines.add(CColor.translateGradient(name,CColor.RED,CColor.ORANGE));
        lines.add(" ");
        lines.add(CColor.translateGradient(creator,CColor.RED,CColor.ORANGE));
        lines.add(CColor.translateGradient(ctime,CColor.RED,CColor.ORANGE));
        lines.add(" ");
        lines.add(CColor.translateGradient(delTime,CColor.RED,CColor.ORANGE));

        Hologram hologram = DHAPI.createHologram(player.getDisplayName()+"_"+name,holoLoc,true,lines);
        location.getBlock().setType(Material.LODESTONE);

        String subTitle = "Dünya %x %y %z".replace("%x",String.valueOf(x)).replace("%y",String.valueOf(y)).replace("%z",String.valueOf(z));
        player.sendTitle(CColor.translateGradient("Claim Oluşturuldu!",CColor.DARK_GREEN,CColor.GREEN),CColor.translateGradient(subTitle,CColor.DARK_GREEN,CColor.GREEN));
    }
}
