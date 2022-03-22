package net.kerim.main.ServerManager.Trader;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import dev.perryplaysmc.dynamicjson.data.CColor;
import net.kerim.main.ServerManager.mServerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class menuTrader {
    private final menuNewTrader trader;
    private final mServerManager manager;

    public menuTrader(menuNewTrader trader, mServerManager manager) {
        this.trader = trader;
        this.manager = manager;
    }

    public void traderMenu(Player player) {
        ChestGui gui = new ChestGui(3, CColor.translateGradient("Pazar",CColor.RED,CColor.ORANGE));
        gui.setOnGlobalClick(event -> event.setCancelled(true));
        Pattern pattern = new Pattern(
                "333333333",
                "331333233",
                "333333333"
        );
        PatternPane pane = new PatternPane(0, 0, 9, 3, pattern);
        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack tpPazar = new ItemStack(Material.ENDER_EYE);
        ItemStack cPazar = new ItemStack(Material.ENCHANTING_TABLE);
        filler.getItemMeta().setDisplayName(" ");
        ItemMeta tpMeta = tpPazar.getItemMeta();
        ItemMeta cMeta = cPazar.getItemMeta();
        List<String> ltp = new ArrayList<>();
        ltp.add(" ");
        ltp.add(CColor.DARK_GRAY+" Buraya tıkla ve pazar alnına ışınlan");
        ltp.add(" ");
        tpMeta.setLore(ltp);
        tpMeta.setDisplayName(CColor.translateGradient("Pazar Alanına Işınlan",CColor.DARK_GREEN,CColor.GREEN));
        tpPazar.setItemMeta(tpMeta);
        List<String> lcp = new ArrayList<>();
        lcp.add(" ");
        lcp.add(CColor.DARK_GRAY+" Buraya tıkla ve 2000 ec karşılığında");
        lcp.add(CColor.DARK_GRAY+" yeni bir pazar oluştur");
        lcp.add(" ");
        cMeta.setDisplayName(CColor.translateGradient("Pazar Oluştur",CColor.RED,CColor.ORANGE));
        cMeta.setLore(lcp);
        cPazar.setItemMeta(cMeta);
        pane.bindItem('3',new GuiItem(filler));
        pane.bindItem('1',new GuiItem(cPazar, event -> {
            player.closeInventory();
            trader.newTrader(player);
        }));
        pane.bindItem('2',new GuiItem(tpPazar,event -> {
            player.closeInventory();
            Bukkit.getPluginManager().callEvent(new eventTPTrader(player));
        }));
        gui.addPane(pane);
        gui.show(player);
    }
}
