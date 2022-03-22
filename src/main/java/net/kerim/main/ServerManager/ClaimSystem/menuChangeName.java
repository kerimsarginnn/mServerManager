package net.kerim.main.ServerManager.ClaimSystem;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.AnvilGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import dev.perryplaysmc.dynamicjson.data.CColor;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.*;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class menuChangeName {
    public void changeName(Player player,Integer no, String owner) {
        AnvilGui gui = new AnvilGui(CColor.ORANGE+"İsim Değiştir");

        StaticPane pane = new StaticPane(0,0,3,1);
        StaticPane pane1 = new StaticPane(0,0,3 ,1);
        pane.setOnClick(event -> event.setCancelled(true));
        pane1.setOnClick(event -> event.setCancelled(true));

        GuiItem first = new GuiItem(new ItemStack(Material.LODESTONE));
        ItemMeta mFirst = first.getItem().getItemMeta();
        mFirst.setDisplayName("İsim gir...");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(CColor.DARK_GRAY+" Claiminin yeni ismi ne");
        lore.add(CColor.DARK_GRAY+" olsun? Lütfen buraya yaz.");
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
                    dataClaim.get().set(no+".name",name);
                    Hologram hologram = DHAPI.getHologram(owner+"_"+dataClaim.get().get(no+".firstName"));
                    DHAPI.setHologramLine(hologram,0,CColor.translateGradient(name,CColor.RED,CColor.ORANGE));
                    hologram.updateAll();
                    player.sendTitle(CColor.translateGradient("İsim Değiştirildi.",CColor.RED,CColor.ORANGE),CColor.translateGradient(name,CColor.RED,CColor.ORANGE));
                    dataClaim.save();
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
