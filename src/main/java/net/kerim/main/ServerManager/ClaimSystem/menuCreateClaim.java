package net.kerim.main.ServerManager.ClaimSystem;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.AnvilGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import dev.perryplaysmc.dynamicjson.data.CColor;
import net.kerim.main.ServerManager.Trader.dataTrader;
import net.kerim.main.ServerManager.Trader.eventCrateTrader;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class menuCreateClaim {
    public void createClaim(Player player) {
        AnvilGui gui = new AnvilGui(CColor.ORANGE+"Yeni Claim");

        StaticPane pane = new StaticPane(0,0,3,1);
        StaticPane pane1 = new StaticPane(0,0,3 ,1);
        pane.setOnClick(event -> event.setCancelled(true));
        pane1.setOnClick(event -> event.setCancelled(true));

        GuiItem first = new GuiItem(new ItemStack(Material.LODESTONE));
        ItemMeta mFirst = first.getItem().getItemMeta();
        mFirst.setDisplayName("İsim gir...");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(CColor.DARK_GRAY+" Oluşturmak istediğin claimin ismi");
        lore.add(CColor.DARK_GRAY+" ne olsun? Lütfen buraya yaz.");
        lore.add(" ");
        mFirst.setLore(lore);
        first.getItem().setItemMeta(mFirst);
        ItemStack verify = new ItemStack(Material.PAPER);
        ItemMeta paperMeta = verify.getItemMeta();
        paperMeta.setDisplayName(CColor.translateGradient("Onayla",CColor.RED,CColor.ORANGE));
        verify.setItemMeta(paperMeta);
        GuiItem result = new GuiItem(verify, event -> {
            HumanEntity humanEntity = event.getWhoClicked();
            if (event.isShiftClick()) {
                event.setCancelled(true);
            } else {
                String name = gui.getRenameText();
                if (!name.equalsIgnoreCase("İsim gir...")) {
                    Player player1 = (Player) humanEntity;
                    Location location = player1.getLocation();
                    Chunk chunk = location.getChunk();
                    Bukkit.getPluginManager().callEvent(new eventCreateClaim(name,player,chunk,location));
                    humanEntity.closeInventory();
                }
            }
        });
        pane1.addItem(result,0,0);
        pane.addItem(first,0,0);

        gui.getResultComponent().addPane(pane1);
        gui.getFirstItemComponent().addPane(pane);
        gui.getFirstItemComponent().display();
        gui.getResultComponent().display();

        gui.show(player);
    }
}
