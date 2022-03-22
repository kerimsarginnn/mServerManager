package net.kerim.main.ServerManager.ClaimSystem;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import dev.perryplaysmc.dynamicjson.data.CColor;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import net.kerim.main.ServerManager.mServerManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class menuTime {
    private final mServerManager manager;

    public menuTime(mServerManager manager) {
        this.manager = manager;
    }

    public void time(Player player, Integer no) {
        ChestGui gui = new ChestGui(3, CColor.translateGradient("Süre Uzak",CColor.ORANGE,CColor.YELLOW));
        gui.setOnGlobalClick(event -> event.setCancelled(true));
        Pattern pattern = new Pattern(
                "000000000",
                "000010000",
                "000000000"
        );
        PatternPane pane = new PatternPane(0, 0, 9, 3, pattern);
        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack item = new ItemStack(Material.CLOCK);
        ItemMeta mItem = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(" §8Claimin süresini 30 güne uzatarak,");
        lore.add(" §8silinmesini önleyebilirsin, tabii");
        lore.add(" §8belirli bir ücret karşılığında.");
        lore.add("");
        ItemMeta meta = filler.getItemMeta();
        meta.setDisplayName(" ");
        filler.setItemMeta(meta);
        pane.bindItem('0', new GuiItem(filler));
        int d = 29-dataClaim.get().getInt(no + ".DeleteDate.D");
        int h = 23-dataClaim.get().getInt(no + ".DeleteDate.H");
        int m = 59-dataClaim.get().getInt(no + ".DeleteDate.M");
        String owner = (String) dataClaim.get().get(no + ".Owner");
        Player pOwner = Bukkit.getPlayer(owner);
        m = m/3;
        h = h*60/3;
        d = d*30*60/3;
        float t = m+h+d;
        if (dataClaim.get().get(no + ".Chunks") != null) {

        }
        lore.add(" §8Ücret, "+CColor.translateGradient("%e ec".replace("%e",String.valueOf(t)),CColor.RED,CColor.ORANGE));
        lore.add(" ");
        String artar = "§8 ücret %t% §8artar.".replace("%t%",CColor.translateGradient("%5",CColor.RED,CColor.ORANGE));
        lore.add(" §8Claimini her büyüttüğünde,");
        lore.add(artar);
        lore.add(" ");
        mItem.setLore(lore);
        mItem.setDisplayName(CColor.translateGradient("Süreyi Uzat",CColor.ORANGE,CColor.YELLOW));
        item.setItemMeta(mItem);
        Economy economy = mServerManager.getEcon();
        pane.bindItem('1', new GuiItem(item, event -> {
            if (economy.getBalance(player) >= t) {
                dataClaim.get().set(no + ".DeleteDate.D",29);
                dataClaim.get().set(no + ".DeleteDate.H",23);
                dataClaim.get().set(no + ".DeleteDate.M",59);
                dataClaim.save();
                economy.withdrawPlayer(player,t);
                Hologram hologram = DHAPI.getHologram(owner + "_" + dataClaim.get().get(no + ".firstName"));
                String delTime = "Kalan süre, %d Gün %h Saat %m Dk".replace("%d", String.valueOf(29)).replace("%h", String.valueOf(23)).replace("%m", String.valueOf(59));
                DHAPI.setHologramLine(hologram, 5, CColor.translateGradient(delTime, CColor.RED, CColor.ORANGE));
                player.sendMessage(CColor.translateGradient("%e ec karşılığında, claim süreni 30 güne uzattın!".replace("%e",String.valueOf(t)),CColor.RED,CColor.ORANGE));
                player.sendTitle(CColor.translateGradient("Claim Süresi Uzatıldı.",CColor.RED,CColor.ORANGE),CColor.translateGradient("Ücret, %e".replace("%e",String.valueOf(t)),CColor.RED,CColor.ORANGE));
                player.closeInventory();
            } else {
                player.sendTitle(CColor.translateGradient("Paran yetersiz!",CColor.DARK_RED,CColor.RED),"");
                player.sendMessage(CColor.translateGradient("Süreyi 30 güne uzatmak için %e ec daha lazım!".replace("%e",String.valueOf((int)t-(int) economy.getBalance(player))),CColor.RED,CColor.ORANGE));
                player.closeInventory();
            }
        }));
        gui.addPane(pane);
        gui.show(player);
        new BukkitRunnable() {
            @Override
            public void run() {
                gui.update();
            }
        }.runTaskTimer(manager,0L,1200L);
    }
}
