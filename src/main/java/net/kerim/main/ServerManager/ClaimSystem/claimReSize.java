package net.kerim.main.ServerManager.ClaimSystem;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import dev.perryplaysmc.dynamicjson.data.CColor;
import net.kerim.main.ServerManager.mServerManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class claimReSize {
    private final mServerManager manager;
    ArrayList<Chunk> chunks = new ArrayList<>();
    ArrayList<Chunk> secLayer = new ArrayList<>();

    public claimReSize(mServerManager manager) {
        this.manager = manager;
    }

    public void reSize(Player player, Integer no, Location location, String name, String owner) {

        StaticPane pane1 = new StaticPane(0, 0, 9, 5, Pane.Priority.LOW);

        World world = location.getWorld();

        int x = dataClaim.get().getInt(no+".Chunk.x");
        int z = dataClaim.get().getInt(no+".Chunk.z");

        Chunk mainChunk = world.getChunkAt(x,z);

        chunks.add(world.getChunkAt(x+1,z-1));
        chunks.add(world.getChunkAt(x+1,z));
        chunks.add(world.getChunkAt(x+1,z+1));
        chunks.add(world.getChunkAt(x,z-1));
        chunks.add(mainChunk);
        chunks.add(world.getChunkAt(x,z+1));
        chunks.add(world.getChunkAt(x-1,z-1));
        chunks.add(world.getChunkAt(x-1,z));
        chunks.add(world.getChunkAt(x+1,z+1));

        secLayer.add(world.getChunkAt(x+2,z-2));
        secLayer.add(world.getChunkAt(x+2,z-1));
        secLayer.add(world.getChunkAt(x+2,z));
        secLayer.add(world.getChunkAt(x+2,z+1));
        secLayer.add(world.getChunkAt(x+2,z+2));
        secLayer.add(world.getChunkAt(x+1,z-2));
        secLayer.add(world.getChunkAt(x+1,z+2));
        secLayer.add(world.getChunkAt(x,z-2));
        secLayer.add(world.getChunkAt(x,z+2));
        secLayer.add(world.getChunkAt(x-1,z-2));
        secLayer.add(world.getChunkAt(x-1,z+2));
        secLayer.add(world.getChunkAt(x-2,z+2));
        secLayer.add(world.getChunkAt(x-2,z-2));
        secLayer.add(world.getChunkAt(x-2,z-1));
        secLayer.add(world.getChunkAt(x-2,z));
        secLayer.add(world.getChunkAt(x-2,z+1));
        secLayer.add(world.getChunkAt(x-2,z+2));
        System.out.println(secLayer);

        int b = 3;
        int c = 1;

        for (Chunk chunk:
             chunks) {
            ItemStack item = new ItemStack(Material.ORANGE_CONCRETE);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(CColor.translateGradient("Alınabilir Bölge",CColor.RED,CColor.ORANGE));
            List<String> lore = new ArrayList<>();
            int xx = chunk.getBlock(7,14,7).getLocation().getBlockX();
            int zz = chunk.getBlock(7,14,7).getLocation().getBlockZ();
            lore.add(" ");
            final String replace = " Kordinat, X %x, Z %z".replace("%x", String.valueOf(xx)).replace("%z", String.valueOf(zz));
            lore.add(" §8İstersen bu chunku claimine katabilirsin,");
            lore.add(" §8tabii belirli bir ücret karşılığında");
            lore.add(" ");
            lore.add(CColor.translateGradient(replace,CColor.RED,CColor.ORANGE));
            lore.add(" ");
            meta.setLore(lore);
            item.setItemMeta(meta);
            if (b==6) {
                c++;
                b=3;
            }
            pane1.addItem(new GuiItem(item),b,c);
            b++;
        }
        int f = 0;
        int e = 2;
        for (Chunk chunk :
                secLayer) {
            ItemStack item = new ItemStack(Material.RED_CONCRETE);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(CColor.translateGradient("Alınamaz Bölge",CColor.DARK_RED,CColor.RED));
            List<String> lore = new ArrayList<>();
            int xx = chunk.getBlock(7,14,7).getLocation().getBlockX();
            int zz = chunk.getBlock(7,14,7).getLocation().getBlockZ();
            lore.add(" ");
            final String replace = " Kordinat, X %x, Z %z".replace("%x", String.valueOf(xx)).replace("%z", String.valueOf(zz));
            lore.add(" §8Bu bölgeden önceki chunku al.");
            lore.add(" ");
            lore.add(CColor.translateGradient(replace,CColor.RED,CColor.ORANGE));
            lore.add(" ");
            meta.setLore(lore);
            item.setItemMeta(meta);
            pane1.addItem(new GuiItem(item),e,f);
            if ((f==1) && (e==2)) {
                e=5;
            }
            if ((f==2) && (e==2)) {
                e=5;
            }
            if ((f==3) && (e==2)) {
                e=5;
            }
            e++;
            if (e==7) {
                e=2;
                f++;
            }
        }

        ItemStack mainClaimItem = new ItemStack(Material.LODESTONE);
        ItemMeta CImeta = mainClaimItem.getItemMeta();
        List<String> CIList = new ArrayList<>();
        CIList.add(" ");
        String locm = " §8Kordinat, X %x, Y %y, Z %z".replace("%x",String.valueOf(location.getBlockX())).replace("%y",String.valueOf(location.getBlockY())).replace("%z"
                ,String.valueOf(location.getBlockZ()));
        CIList.add(locm);
        CIList.add(" ");
        CImeta.setDisplayName(CColor.translateGradient("Ana Chunk",CColor.RED,CColor.ORANGE));
        CImeta.setLore(CIList);
        mainClaimItem.setItemMeta(CImeta);
        pane1.removeItem(4,2);
        pane1.addItem(new GuiItem(mainClaimItem),4,2);
        ChestGui gui = new ChestGui(5, CColor.translateGradient("Claim Genişlet",CColor.ORANGE,CColor.YELLOW));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta mFiller = filler.getItemMeta();
        mFiller.setDisplayName(" ");
        filler.setItemMeta(mFiller);

        Pattern pattern = new Pattern(
                "010000012",
                "010000010",
                "010000010",
                "010000010",
                "310000010"
        );
        PatternPane pane = new PatternPane(0, 0, 9, 5, Pane.Priority.HIGH,pattern);
        pane.bindItem('1',new GuiItem(filler));

        ItemStack comp = new ItemStack(Material.COMPASS);
        ItemMeta cMeta = comp.getItemMeta();
        cMeta.setDisplayName(CColor.translateGradient("Güney",CColor.ORANGE,CColor.YELLOW));
        comp.setItemMeta(cMeta);

        pane.bindItem('2',new GuiItem(comp));

        ItemStack exit = new ItemStack(Material.ARROW);
        ItemMeta eMeta = exit.getItemMeta();
        List<String> eLore = new ArrayList<>();
        eLore.add(" ");
        eLore.add(" §8Buraya tıkla ve geri dön.");
        eLore.add(" ");
        eMeta.setLore(eLore);
        eMeta.setDisplayName(CColor.translateGradient("Geri dön",CColor.ORANGE,CColor.YELLOW));
        exit.setItemMeta(eMeta);

        pane.bindItem('3', new GuiItem(exit, event -> {
            Bukkit.getPluginManager().callEvent(new eventBack(player,no,name,owner));
        }));

        gui.addPane(pane1);
        gui.addPane(pane);
        gui.show(player);
        gui.setOnClose(inventoryCloseEvent -> {
            chunks.clear();
            gui.getItems().clear();
        });
    }
}
