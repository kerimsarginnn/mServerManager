package net.kerim.main.ServerManager.ClaimSystem;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import dev.perryplaysmc.dynamicjson.data.CColor;
import net.kerim.main.ServerManager.mServerManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class menuManageClaim {
    private final mServerManager manager;
    private final menuCreateClaim claim;
    private final menuListClaims list;

    public menuManageClaim(mServerManager manager, menuCreateClaim claim, menuListClaims list) {
        this.manager = manager;
        this.claim = claim;
        this.list = list;
    }

    public void manageClaim(Player player) {
        ChestGui gui = new ChestGui(3, CColor.translateGradient("Claim",CColor.RED,CColor.ORANGE));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        Pattern pattern = new Pattern(
                "000000000",
                "010020030",
                "000000000"
        );
        PatternPane pane = new PatternPane(0, 0, 9, 3, pattern);

        ItemStack newClaim = new ItemStack(Material.LODESTONE);
        ItemStack listClaim = new ItemStack(Material.GRASS_BLOCK);
        ItemStack teleportClaim = new ItemStack(Material.ENDER_EYE);
        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

        ItemMeta mnClaim = newClaim.getItemMeta();
        ItemMeta mlClaim = listClaim.getItemMeta();
        ItemMeta mtClaim = teleportClaim.getItemMeta();

        List<String> lnClaim = new ArrayList<>();
        List<String> llClaim = new ArrayList<>();
        List<String> tlCliam = new ArrayList<>();

        lnClaim.add(" ");
        lnClaim.add(CColor.DARK_GRAY+" Buraya tıkla ve 12000 ec karşılığında");
        lnClaim.add(CColor.DARK_GRAY+" yeni bir claim oluştur.");
        lnClaim.add(" ");
        llClaim.add(" ");
        llClaim.add(CColor.DARK_GRAY+" Buraya tıkla ve ekli olduğun");
        llClaim.add(CColor.DARK_GRAY+" claimleri görüntüle.");
        llClaim.add(" ");
        tlCliam.add(" ");
        tlCliam.add(CColor.DARK_GRAY+" Buraya tıkla ve claimine ışınlan.");
        tlCliam.add(" ");

        mnClaim.setLore(lnClaim);
        mlClaim.setLore(llClaim);
        mtClaim.setLore(tlCliam);

        mtClaim.setDisplayName(CColor.translateGradient("Claime Işınlan",CColor.DARK_GREEN,CColor.GREEN));
        mlClaim.setDisplayName(CColor.translateGradient("Claimlerim",CColor.GREEN,CColor.YELLOW));
        mnClaim.setDisplayName(CColor.translateGradient("Claim Oluştur",CColor.RED,CColor.ORANGE));
        filler.getItemMeta().setDisplayName(" ");

        newClaim.setItemMeta(mnClaim);
        listClaim.setItemMeta(mlClaim);
        teleportClaim.setItemMeta(mtClaim);

        pane.bindItem('0',new GuiItem(filler));
        pane.bindItem('1',new GuiItem(newClaim, event -> {
            Economy economy = mServerManager.getEcon();
            if (economy.getBalance(player) >= 12000) {
                player.closeInventory();
                claim.createClaim(player);
            } else {
                int i = (int) (12000-economy.getBalance(player));
                player.sendMessage(CColor.translateGradient("Claim oluşturman için %e ec daha lazım!".replace("%e",String.valueOf(i)),CColor.RED,CColor.ORANGE));
            }
        }));
        pane.bindItem('2',new GuiItem(listClaim, event -> {
            list.listClaims(player);
        }));
        pane.bindItem('3',new GuiItem(teleportClaim, event -> {
            int s = Objects.requireNonNull(dataClaim.get().getConfigurationSection("")).getKeys(false).size();
            int i = 0;
            do {
                if (dataClaim.get().get(i+".Owner").equals(player.getDisplayName())) {
                    World world = Bukkit.getWorld((String) Objects.requireNonNull(dataClaim.get().get(i + ".World")));
                    double x = dataClaim.get().getInt(i+".Block.x")+0.50;
                    int y = dataClaim.get().getInt(i+".Block.y")+1;
                    double z = dataClaim.get().getInt(i+".Block.z")+0.50;
                    Location location = new Location(world,x,y,z);
                    player.teleport(location);
                    String s1 = (String) dataClaim.get().get(i+".name");
                    player.sendMessage(CColor.translateGradient("%s isimli claime ışınlandın!".replace("%s",s1),CColor.GREEN,CColor.YELLOW));
                }
                if (i == s) {
                    player.sendMessage(CColor.translateGradient("Bir claimin yok!",CColor.RED,CColor.ORANGE));
                }
                i++;
            } while (i < s);
        }));
        gui.addPane(pane);
        gui.show(player);
    }
}
