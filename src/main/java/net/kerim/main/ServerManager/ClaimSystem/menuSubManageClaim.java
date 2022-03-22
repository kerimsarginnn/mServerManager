package net.kerim.main.ServerManager.ClaimSystem;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import dev.perryplaysmc.dynamicjson.data.CColor;
import eu.decentsoftware.holograms.api.DHAPI;
import net.kerim.main.ServerManager.mServerManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class menuSubManageClaim {
    private final mServerManager manager;
    private final menuPlayerManagement menuPlayerManagement;
    private final menuChangeName menuChangeName;
    private final menuTime menuTime;
    private final claimReSize reSize;

    public menuSubManageClaim(mServerManager manager, net.kerim.main.ServerManager.ClaimSystem.menuPlayerManagement menuPlayerManagement, menuChangeName menuChangeName, menuTime menuTime, claimReSize reSize) {
        this.manager = manager;
        this.menuPlayerManagement = menuPlayerManagement;
        this.menuChangeName = menuChangeName;
        this.menuTime = menuTime;
        this.reSize = reSize;
    }

    public void subManage(Player player, Integer no, String name, String owner){
        // 1
        ChestGui gui = new ChestGui(3, CColor.translateGradient("Claimi Yönet",CColor.RED,CColor.ORANGE));
        gui.setOnGlobalClick(event -> event.setCancelled(true));
        Pattern pattern = new Pattern(
                "012345670",
                "000000000",
                "000000008"
        );
        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta fMeta = filler.getItemMeta();
        fMeta.setDisplayName(" ");
        filler.setItemMeta(fMeta);
        filler.getItemMeta().setDisplayName(" ");
        ItemStack del = new ItemStack(Material.REDSTONE_BLOCK);
        List<String> lDel = new ArrayList<>();
        lDel.add(" ");
        lDel.add("§8 Shift sağ tıkla ve claimi %s§8.".replace("%s",CColor.translateGradient("sil",CColor.DARK_RED,CColor.RED)));
        lDel.add("§8 bu işlem sonucunda claim oluşturma");
        lDel.add("§8 parasının %m§8'i iade edilir.".replace("%m",CColor.translateGradient("%25",CColor.RED,CColor.ORANGE)));
        lDel.add(" ");
        ItemMeta mDel = del.getItemMeta();
        mDel.setDisplayName(CColor.translateGradient("Sil",CColor.DARK_RED,CColor.RED));
        mDel.setLore(lDel);
        del.setItemMeta(mDel);
        PatternPane pane = new PatternPane(0, 0, 9, 3, pattern);
        pane.bindItem('0',new GuiItem(filler));
        pane.bindItem('8',new GuiItem(del, event -> {
            if (event.isShiftClick()) {
                if (event.isRightClick()) {
                    Economy economy = mServerManager.getEcon();
                    economy.depositPlayer(player,3000);
                    World world = Bukkit.getWorld((String) dataClaim.get().get(no+".World"));
                    int x = dataClaim.get().getInt(no+".Block.x");
                    int y = dataClaim.get().getInt(no+".Block.y");
                    int z = dataClaim.get().getInt(no+".Block.z");
                    Location location = new Location(world,x,y,z);
                    location.getBlock().setType(Material.AIR);
                    dataClaim.get().set(no+"",null);
                    dataClaim.save();
                    DHAPI.removeHologram(owner+"_"+name);
                    player.closeInventory();
                    player.sendMessage(CColor.translateGradient("Claim başarıyla kaldırıldı, 3000 ec iade edildi.",CColor.RED,CColor.ORANGE));
                }
            }
        }));

        ItemStack pManage = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skull = (SkullMeta) pManage.getItemMeta();
        skull.setOwner(player.getName());
        List<String> sLore = new ArrayList<>();
        sLore.add(" ");
        sLore.add(" §8Buraya tıklayarak claime oyuncu %e,".replace("%e",CColor.translateGradient("ekleyebilir",CColor.RED,CColor.ORANGE)));
        sLore.add(" "+CColor.translateGradient("silebilir",CColor.RED,CColor.ORANGE)+" §8veya "+CColor.translateGradient("izinlerini yönetebilisin",CColor.RED,CColor.ORANGE)+"§8.");
        sLore.add(" ");
        skull.setLore(sLore);
        skull.setDisplayName(CColor.translateGradient("Oyuncu Yönetimi",CColor.RED,CColor.ORANGE));
        pManage.setItemMeta(skull);
        pane.bindItem('1',new GuiItem(pManage, event -> {
            menuPlayerManagement.playerManager(player, owner, name);
        }));

        ItemStack cName = new ItemStack(Material.NAME_TAG);
        ItemMeta mCName = cName.getItemMeta();
        mCName.setDisplayName(CColor.translateGradient("İsim Değiştir",CColor.GREEN, CColor.YELLOW));
        List<String> lorec = new ArrayList<>();
        lorec.add(" ");
        lorec.add(" §8Buraya tıkla ve claimin %s§8.".replace("%s",CColor.translateGradient("ismini değiştir", CColor.GREEN,CColor.YELLOW)));
        lorec.add(" ");
        mCName.setLore(lorec);
        cName.setItemMeta(mCName);
        pane.bindItem('2', new GuiItem(cName,event -> {
            menuChangeName.changeName(player,no,owner);
        }));
        ItemStack time = new ItemStack(Material.CLOCK);
        ItemMeta mTime = time.getItemMeta();
        List<String> loreTime = new ArrayList<>();
        loreTime.add(" ");
        loreTime.add(" §8Claimin, süresi dolduğunda %s".replace("%s",CColor.translateGradient("silinir!",CColor.DARK_RED,CColor.RED)));
        loreTime.add(" §8Bunu engellemek için süreyi uzatmalısın.");
        loreTime.add(" ");
        loreTime.add(" §8Buraya tıkla ve menüyü aç.");
        loreTime.add(" ");
        mTime.setLore(loreTime);
        mTime.setDisplayName(CColor.translateGradient("Süreyi Uzat",CColor.ORANGE,CColor.YELLOW));
        time.setItemMeta(mTime);
        pane.bindItem('3',new GuiItem(time, event -> {
            menuTime.time(player,no);
        }));
        ItemStack size = new ItemStack(Material.MAP);
        ItemMeta mSize = size.getItemMeta();
        List<String> loreSize = new ArrayList<>();
        loreSize.add(" ");
        loreSize.add(" §8Claimi belirli bir ücret");
        loreSize.add(" §8karşılığında büyütebilirsin.");
        loreSize.add(" ");
        loreSize.add(" §8Buraya tıkla ve menüyü aç.");
        loreSize.add(" ");
        mSize.setDisplayName(CColor.translateGradient("Claim Genişlet",CColor.ORANGE,CColor.YELLOW));
        mSize.setLore(loreSize);
        size.setItemMeta(mSize);
        pane.bindItem('4', new GuiItem(size, event -> {
            World world = Bukkit.getWorld((String) dataClaim.get().get(no+".World"));
            int x = dataClaim.get().getInt(no+".Block.x");
            int y = dataClaim.get().getInt(no+".Block.y");
            int z = dataClaim.get().getInt(no+".Block.z");
            Location location = new Location(world,x,y,z);
            Chunk chunk = location.getChunk();
            reSize.reSize(player,no,location,name,owner);
        }));

        gui.addPane(pane);
        gui.show(player);
    }
}
