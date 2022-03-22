package net.kerim.main.ServerManager.Trader;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.AnvilGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import dev.perryplaysmc.dynamicjson.data.CColor;
import net.kerim.main.ServerManager.Home.dataHome;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class menuNewTrader {
    public void newTrader(Player player) {
        AnvilGui gui = new AnvilGui(CColor.ORANGE+"Pazar Oluştur");

        StaticPane pane = new StaticPane(0,0,3,1);
        StaticPane pane1 = new StaticPane(0,0,3 ,1);
        pane.setOnClick(event -> event.setCancelled(true));
        pane1.setOnClick(event -> event.setCancelled(true));

        GuiItem first = new GuiItem(new ItemStack(Material.CHEST));
        ItemMeta mFirst = first.getItem().getItemMeta();
        mFirst.setDisplayName("İsim gir...");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(CColor.DARK_GRAY+" Oluşturmak istediğin pazırın ismi");
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
                    int x = location.getBlockX();
                    int y = location.getBlockY();
                    int z = location.getBlockZ();
                    String world = location.getWorld().getName();
                    String subTitle = "Dünya %x %y %z".replace("%x",String.valueOf(x)).replace("%y",String.valueOf(y)).replace("%z",String.valueOf(z));
                    dataTrader.get().set(player1.getDisplayName()+".1.name",name);
                    dataTrader.get().set(player1.getDisplayName()+".1.World",world);
                    dataTrader.get().set(player1.getDisplayName()+".1.x",x);
                    dataTrader.get().set(player1.getDisplayName()+".1.y",y);
                    dataTrader.get().set(player1.getDisplayName()+".1.z",z);
                    Bukkit.getPluginManager().callEvent(new eventCrateTrader(player,location,name));
                    player1.sendTitle(CColor.translateGradient("Pazar Oluşturuldu!",CColor.DARK_GREEN,CColor.GREEN),CColor.translateGradient(subTitle,CColor.DARK_GREEN,CColor.GREEN));
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
