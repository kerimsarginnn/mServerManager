package net.kerim.main.ServerManager.Home;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import dev.perryplaysmc.dynamicjson.data.CColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class menuHome {
    private final menuNewHome home;

    public menuHome(menuNewHome home) {
        this.home = home;
    }

    public void menu(HumanEntity player) {
        ChestGui gui = new ChestGui(2,CColor.translateGradient("Ev",CColor.ORANGE,CColor.YELLOW));
        gui.setOnGlobalClick(event -> {
            event.setCancelled(true);
        });
        Pattern pattern = new Pattern(
                "abcdefgkl",
                "000020000",
                "333333333"
        );
        ItemStack newHome = new ItemStack(Material.ANVIL);
        ItemStack homes = new ItemStack(Material.WHITE_SHULKER_BOX);
        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta fillerMeta = filler.getItemMeta();
        ItemMeta newMeta = newHome.getItemMeta();
        ItemMeta homeMeta = homes.getItemMeta();
        List<String> newLore = new ArrayList<>();
        newLore.add(" ");
        newLore.add(CColor.DARK_GRAY+"Buraya tıkla yeni ev kaydet.");
        newLore.add(" ");
        homeMeta.setLore(newLore);
        newMeta.setDisplayName(CColor.translateGradient("Ev Kaydet",CColor.RED,CColor.ORANGE));
        newHome.setItemMeta(newMeta);
        fillerMeta.setDisplayName(" ");
        filler.setItemMeta(fillerMeta);

        PatternPane pane = new PatternPane(0, 0, 9, 3, pattern);
        if (dataHome.get().get(player.getName()+".1") != null) {
            ItemStack item = new ItemStack(Material.WHITE_SHULKER_BOX);
            ItemMeta itemMeta = item.getItemMeta();
            String homeName = String.valueOf(dataHome.get().get(player.getName()+".1.name"));
            itemMeta.setDisplayName(CColor.translateGradient(homeName,CColor.RED,CColor.ORANGE));
            int x = dataHome.get().getInt(player.getName()+".1.x");
            int y = dataHome.get().getInt(player.getName()+".1.y");
            int z = dataHome.get().getInt(player.getName()+".1.z");
            World world = Bukkit.getWorld(dataHome.get().getString(player.getName()+".1.world"));
            Location home = new Location(world,x,y,z);
            List<String> lore = new ArrayList<>();
            lore.add(" ");
            String location = " Dünya X %x, Y %y, Z %z".replace("%x",String.valueOf(x)).replace("%y",String.valueOf(y)).replace("%z",String.valueOf(z));
            lore.add(CColor.translateGradient(location,CColor.DARK_GRAY,CColor.DARK_GRAY));
            lore.add(" ");
            lore.add(CColor.translateGradient(" Sol tıkla ve ışınlan",CColor.DARK_GREEN,CColor.GREEN));
            lore.add(CColor.translateGradient(" Shift Sağ tıkla ve sil",CColor.RED,CColor.ORANGE));
            lore.add(" ");
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            pane.bindItem('a',new GuiItem(item, event -> {
                if (event.isLeftClick()) {
                    Player player1 = (Player) player;
                    player.closeInventory();
                    Bukkit.getPluginManager().callEvent(new eventHome(3,home,player1));
                } else if (event.isRightClick()) {
                    if (event.isShiftClick()) {
                        dataHome.get().set(player.getName()+".1",null);
                        player.sendMessage(CColor.translateGradient("%n isimli ev başarıyla silindi!".replace("%n",homeName),CColor.RED,CColor.ORANGE));
                        player.closeInventory();
                        dataHome.save();
                        event.setCancelled(true);
                    }
                }
            }));
        }
        if (dataHome.get().get(player.getName()+".2") != null) {
            ItemStack item = new ItemStack(Material.WHITE_SHULKER_BOX);
            ItemMeta itemMeta = item.getItemMeta();
            String homeName = String.valueOf(dataHome.get().get(player.getName()+".2.name"));
            itemMeta.setDisplayName(CColor.translateGradient(homeName,CColor.RED,CColor.ORANGE));
            int x = dataHome.get().getInt(player.getName()+".2.x");
            int y = dataHome.get().getInt(player.getName()+".2.y");
            int z = dataHome.get().getInt(player.getName()+".2.z");
            World world = Bukkit.getWorld(dataHome.get().getString(player.getName()+".2.world"));
            Location home = new Location(world,x,y,z);
            List<String> lore = new ArrayList<>();
            lore.add(" ");
            String location = " Dünya X %x, Y %y, Z %z".replace("%x",String.valueOf(x)).replace("%y",String.valueOf(y)).replace("%z",String.valueOf(z));
            lore.add(CColor.translateGradient(location,CColor.DARK_GRAY,CColor.DARK_GRAY));
            lore.add(" ");
            lore.add(CColor.translateGradient(" Sol tıkla ve ışınlan",CColor.DARK_GREEN,CColor.GREEN));
            lore.add(CColor.translateGradient(" Shift Sağ tıkla ve sil",CColor.RED,CColor.ORANGE));
            lore.add(" ");
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            itemMeta.setLore(lore);
            pane.bindItem('b',new GuiItem(item, event -> {
                if (event.isLeftClick()) {
                    Player player1 = (Player) player;
                    player.closeInventory();
                    Bukkit.getPluginManager().callEvent(new eventHome(3,home,player1));
                } else if (event.isRightClick()) {
                    if (event.isShiftClick()) {
                        dataHome.get().set(player.getName()+".2",null);
                        player.sendMessage(CColor.translateGradient("%n isimli ev başarıyla silindi!".replace("%n",homeName),CColor.RED,CColor.ORANGE));
                        player.closeInventory();
                        dataHome.save();
                        event.setCancelled(true);
                    }
                }
            }));
        }
        if (dataHome.get().get(player.getName()+".3") != null) {
            ItemStack item = new ItemStack(Material.WHITE_SHULKER_BOX);
            ItemMeta itemMeta = item.getItemMeta();
            String homeName = String.valueOf(dataHome.get().get(player.getName()+".3.name"));
            itemMeta.setDisplayName(CColor.translateGradient(homeName,CColor.RED,CColor.ORANGE));
            int x = dataHome.get().getInt(player.getName()+".3.x");
            int y = dataHome.get().getInt(player.getName()+".3.y");
            int z = dataHome.get().getInt(player.getName()+".3.z");
            World world = Bukkit.getWorld(dataHome.get().getString(player.getName()+".3.world"));
            Location home = new Location(world,x,y,z);
            List<String> lore = new ArrayList<>();
            lore.add(" ");
            String location = " Dünya X %x, Y %y, Z %z".replace("%x",String.valueOf(x)).replace("%y",String.valueOf(y)).replace("%z",String.valueOf(z));
            lore.add(CColor.translateGradient(location,CColor.DARK_GRAY,CColor.DARK_GRAY));
            lore.add(" ");
            lore.add(CColor.translateGradient(" Sol tıkla ve ışınlan",CColor.DARK_GREEN,CColor.GREEN));
            lore.add(CColor.translateGradient(" Shift Sağ tıkla ve sil",CColor.RED,CColor.ORANGE));
            lore.add(" ");
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            pane.bindItem('c',new GuiItem(item, event -> {
                if (event.isLeftClick()) {
                    Player player1 = (Player) player;
                    player.closeInventory();
                    Bukkit.getPluginManager().callEvent(new eventHome(3,home,player1));
                } else if (event.isRightClick()) {
                    if (event.isShiftClick()) {
                        dataHome.get().set(player.getName()+".3",null);
                        player.sendMessage(CColor.translateGradient("%n isimli ev başarıyla silindi!".replace("%n",homeName),CColor.RED,CColor.ORANGE));
                        player.closeInventory();
                        dataHome.save();
                        event.setCancelled(true);
                    }
                }
            }));
        }
        pane.bindItem('2',new GuiItem(newHome, event -> {
            home.newHome(player);
        }));
        pane.bindItem('1',new GuiItem(homes));
        pane.bindItem('0',new GuiItem(filler));
        gui.addPane(pane);
        gui.show(player);
    }
}