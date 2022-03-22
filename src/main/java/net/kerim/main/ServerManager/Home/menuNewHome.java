package net.kerim.main.ServerManager.Home;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.AnvilGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import dev.perryplaysmc.dynamicjson.data.CColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class menuNewHome {
    public void newHome(HumanEntity player) {
        AnvilGui gui = new AnvilGui(CColor.ORANGE+"Ev Kaydet");

        StaticPane pane = new StaticPane(0,0,3,1);
        StaticPane pane1 = new StaticPane(0,0,3 ,1);
        pane.setOnClick(event -> event.setCancelled(true));
        pane1.setOnClick(event -> event.setCancelled(true));

        GuiItem first = new GuiItem(new ItemStack(Material.PAPER));
        ItemMeta mFirst = first.getItem().getItemMeta();
        mFirst.setDisplayName("İsim gir...");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(CColor.DARK_GRAY+" Kaydetmek istediğin evin ismi");
        lore.add(CColor.DARK_GRAY+" ne olsun? Buraya yaz lütfen.");
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
                   if (dataHome.get().get(player1.getDisplayName()+".1") == null) {
                       dataHome.get().set(player1.getDisplayName()+".1."+".name",name);
                       dataHome.get().set(player1.getDisplayName()+".1."+".world",world);
                       dataHome.get().set(player1.getDisplayName()+".1."+".x",x);
                       dataHome.get().set(player1.getDisplayName()+".1."+".y",y);
                       dataHome.get().set(player1.getDisplayName()+".1."+".z",z);
                       dataHome.save();
                       player1.sendTitle(CColor.translateGradient("Ev Belirlendi!",CColor.DARK_GREEN,CColor.GREEN),CColor.translateGradient(subTitle,CColor.DARK_GREEN,CColor.GREEN));
                   } else if (dataHome.get().get(player1.getDisplayName()+".2") == null) {
                       dataHome.get().set(player1.getDisplayName()+".2."+".name",name);
                       dataHome.get().set(player1.getDisplayName()+".2."+".world",world);
                       dataHome.get().set(player1.getDisplayName()+".2."+".x",x);
                       dataHome.get().set(player1.getDisplayName()+".2."+".y",y);
                       dataHome.get().set(player1.getDisplayName()+".2."+".z",z);
                       dataHome.save();
                       player1.sendTitle(CColor.translateGradient("Ev Belirlendi!",CColor.DARK_GREEN,CColor.GREEN),CColor.translateGradient(subTitle,CColor.DARK_GREEN,CColor.GREEN));
                   } else if (dataHome.get().get(player1.getDisplayName()+".3") == null) {
                       dataHome.get().set(player1.getDisplayName()+".3."+".name",name);
                       dataHome.get().set(player1.getDisplayName()+".3."+".world",world);
                       dataHome.get().set(player1.getDisplayName()+".3."+".x",x);
                       dataHome.get().set(player1.getDisplayName()+".3."+".y",y);
                       dataHome.get().set(player1.getDisplayName()+".3."+".z",z);
                       dataHome.save();
                       player1.sendTitle(CColor.translateGradient("Ev Belirlendi!",CColor.DARK_GREEN,CColor.GREEN),CColor.translateGradient(subTitle,CColor.DARK_GREEN,CColor.GREEN));
                   } else {
                       player.sendMessage(CColor.translateGradient("Azami 3 ev kaydetebilirsin.",CColor.RED,CColor.ORANGE));
                   }
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
