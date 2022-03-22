package net.kerim.main.ServerManager.ClaimSystem;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import dev.perryplaysmc.dynamicjson.data.CColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class menuListClaims {
    private final menuSubManageClaim claim;
    List<GuiItem> is = new ArrayList<>();

    public menuListClaims(menuSubManageClaim claim) {
        this.claim = claim;
    }

    public void listClaims(Player player) {
        ChestGui gui = new ChestGui(3, CColor.translateGradient("Claimlerim",CColor.RED,CColor.ORANGE));
        gui.setOnGlobalClick(event -> event.setCancelled(true));
        int s = dataClaim.get().getConfigurationSection("").getKeys(false).size();
        int i = 0;
        do {
            if (dataClaim.get().get(i+".Owner").equals(player.getDisplayName())) {
                int x = dataClaim.get().getInt(i+".Block.x");
                int y = dataClaim.get().getInt(i+".Block.y");
                int z = dataClaim.get().getInt(i+".Block.z");
                List<String> lore = new ArrayList<>();
                World world = Bukkit.getWorld((String) dataClaim.get().get(i+".World"));
                lore.add(" ");
                String location = " Dünya X %x, Y %y, Z %z".replace("%x",String.valueOf(x)).replace("%y",String.valueOf(y)).replace("%z",String.valueOf(z));
                lore.add(CColor.translateGradient(location,CColor.DARK_GRAY,CColor.DARK_GRAY));
                lore.add(" ");
                LocalDateTime time = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss");
                String dateTime = formatter.format(time);
                String creator = "§8 Kurucu, %p".replace("%p",player.getDisplayName());
                String ctime = "§8 Kuruluş, %d".replace("%d",time.format(formatter));
                lore.add(creator);
                lore.add(ctime);
                int d = dataClaim.get().getInt(i+".DeleteDate.D");
                int h = dataClaim.get().getInt(i+".DeleteDate.H");
                int m = dataClaim.get().getInt(i+".DeleteDate.M");
                String delTime = "§8 Kalan süre, %d Gün %h Saat %m Dk".replace("%d",String.valueOf(d)).replace("%h",String.valueOf(h)).replace("%m",String.valueOf(m));
                lore.add(delTime);
                lore.add(" ");
                lore.add(CColor.translateGradient(" Sol tıkla ve yönet",CColor.DARK_GREEN,CColor.GREEN));
                lore.add(CColor.translateGradient(" Sağ tıkla ve ışınlan",CColor.RED,CColor.ORANGE));
                lore.add(" ");
                ItemStack stack = new ItemStack(Material.LODESTONE);
                ItemMeta meta = stack.getItemMeta();
                meta.setLore(lore);
                String name = (String) dataClaim.get().get(i+".firstName");
                meta.setDisplayName(CColor.translateGradient(name,CColor.RED,CColor.ORANGE));
                stack.setItemMeta(meta);
                int finalI = i;
                int finalI1 = i;
                int finalI2 = i;
                GuiItem item = new GuiItem(stack, event -> {
                    if (event.isLeftClick()) {
                        claim.subManage(player, finalI1,(String) dataClaim.get().get(finalI2 +".name"),(String) dataClaim.get().get(finalI2 +".Owner"));
                    } else if (event.isRightClick()) {
                        Location cloc = new Location(world,x+0.50,y+1,z+0.50);
                        player.teleport(cloc);
                        String s1 = (String) dataClaim.get().get(finalI +".name");
                        player.sendMessage(CColor.translateGradient("%s isimli claime ışınlandın!".replace("%s",s1),CColor.GREEN,CColor.YELLOW));
                    }
                });
                is.add(item);
            }
            i++;
        } while (i < s);
        PaginatedPane pages = new PaginatedPane(0, 0, 9, 5);
        pages.populateWithGuiItems(is);
        gui.addPane(pages);
        gui.show(player);
        gui.setOnClose(inventoryCloseEvent -> {
            pages.clear();
            is.clear();
        });
    }
}
