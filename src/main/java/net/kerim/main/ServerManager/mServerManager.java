package net.kerim.main.ServerManager;

import dev.perryplaysmc.dynamicjson.data.CColor;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import net.kerim.main.ServerManager.ClaimSystem.*;
import net.kerim.main.ServerManager.Essentials.*;
import net.kerim.main.ServerManager.Home.*;
import net.kerim.main.ServerManager.Management.commandKick;
import net.kerim.main.ServerManager.Spawn.commandSetSpawn;
import net.kerim.main.ServerManager.Spawn.commandSpawn;
import net.kerim.main.ServerManager.Spawn.listenerSpawn;
import net.kerim.main.ServerManager.Trader.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class mServerManager extends JavaPlugin {

    private static Economy econ = null;

    @Override
    public void onEnable() {

        if (!setupEconomy()) {
            System.out.println(CColor.translateGradient("Ekonomi plugini bulunamadı", CColor.RED, CColor.ORANGE));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        dataHome.setup();
        dataTrader.setup();
        dataClaim.setup();

        PluginManager manager = Bukkit.getPluginManager();

        manager.registerEvents(new listenerSpawn(this), this);
        manager.registerEvents(new listenerGod(new commandGod()), this);
        manager.registerEvents(new listenerHome(this), this);
        manager.registerEvents(new listenerTPTrader(this), this);
        manager.registerEvents(new listenerCreateTrader(this), this);
        manager.registerEvents(new listenerCreateClaim(this), this);
        manager.registerEvents(new listenerBreakCB(), this);
        manager.registerEvents(new listenerRightCB(new menuSubManageClaim(this, new menuPlayerManagement(), new menuChangeName(), new menuTime(this), new claimReSize(this))), this);
        manager.registerEvents(new listenerBack(new menuSubManageClaim(this,new menuPlayerManagement(),new menuChangeName(),new menuTime(this),new claimReSize(this))),this);
        manager.registerEvents(new listenerLog(), this);

        getCommand("SetSpawn").setExecutor(new commandSetSpawn(this));
        getCommand("Spawn").setExecutor(new commandSpawn(this));
        getCommand("Feed").setExecutor(new commandFeed());
        getCommand("Fly").setExecutor(new commandFly());
        getCommand("God").setExecutor(new commandGod());
        getCommand("Heal").setExecutor(new commandHeal());
        getCommand("PWeather").setExecutor(new commandPWeather());
        getCommand("Sun").setExecutor(new commandWeather());
        getCommand("Rain").setExecutor(new commandWeather());
        getCommand("Day").setExecutor(new commandTime());
        getCommand("Kick").setExecutor(new commandKick());
        getCommand("Ev").setExecutor(new commandHome(new menuHome(new menuNewHome())));
        getCommand("Pazar").setExecutor(new commandTrader(new menuTrader(new menuNewTrader(), this)));
        getCommand("Claim").setExecutor(new commandClaim(new menuManageClaim(this, new menuCreateClaim(), new menuListClaims(new menuSubManageClaim(this, new menuPlayerManagement(), new menuChangeName(), new menuTime(this), new claimReSize(this))))));
        getCommand("Test").setExecutor(new testkoomus());

        utillTime();

    }

    public void noPerm(CommandSender sender) {
        sender.sendMessage(CColor.translateGradient("Bunun için gerekli izine sahip değilsin.", CColor.RED, CColor.ORANGE));
    }

    public void noPlayer(CommandSender sender) {
        sender.sendMessage(CColor.translateGradient("Bu komut sadece bir oyuncu tarafından kullanılabilir", CColor.RED, CColor.ORANGE));
    }

    public void checkPlayer(CommandSender sender) {
        if (sender instanceof Player) {
        } else {
            sender.sendMessage(CColor.translateGradient("Bu komut sadece bir oyuncu tarafından kullanılabilir", CColor.RED, CColor.ORANGE));
        }
    }

    public void checkPerm(CommandSender sender) {
        if (!sender.isOp()) {
            sender.sendMessage(CColor.translateGradient("Bunun için gerekli izine sahip değilsin.", CColor.RED, CColor.ORANGE));
        }
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
    }

    @Override
    public void saveConfig() {
        super.saveConfig();
        super.saveDefaultConfig();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEcon() {
        return econ;
    }

    public void utillTime() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!(dataClaim.get().getConfigurationSection("").getKeys(false).size() == 0)) {
                    int s = dataClaim.get().getConfigurationSection("").getKeys(false).size();
                    int i = 0;
                    do {
                        int d = dataClaim.get().getInt(i + ".DeleteDate.D");
                        int h = dataClaim.get().getInt(i + ".DeleteDate.H");
                        int m = dataClaim.get().getInt(i + ".DeleteDate.M");
                        String owner = (String) dataClaim.get().get(i + ".Owner");
                        Hologram hologram = DHAPI.getHologram(owner + "_" + dataClaim.get().get(i + ".firstName"));
                        m--;
                        String delTime = "Kalan süre, %d Gün %h Saat %m Dk".replace("%d", String.valueOf(d)).replace("%h", String.valueOf(h)).replace("%m", String.valueOf(m));
                        if (m==-1) {
                            m=59;
                            h--;
                            delTime = "Kalan süre, %d Gün %h Saat %m Dk".replace("%d", String.valueOf(d)).replace("%h", String.valueOf(h)).replace("%m", String.valueOf(m));}
                        else if ((d==0) && (h==0)) {
                            delTime = "Kalan süre, %m Dk".replace("%m", String.valueOf(m));}
                        else if ((m==0) && (h==0) ) {
                            delTime = "Kalan süre, %d Gün".replace("%d", String.valueOf(d));}
                        else if (m==0) {
                            delTime = "Kalan süre, %d Gün %h Saat".replace("%d", String.valueOf(d)).replace("%h", String.valueOf(h));}
                        if (h==-1) {
                            h=24;
                            d--;
                            delTime = "Kalan süre, %d Gün %h Saat %m Dk".replace("%d", String.valueOf(d)).replace("%h", String.valueOf(h)).replace("%m", String.valueOf(m));}
                        else if (h==0) {
                            delTime = "Kalan süre, %d Gün %m Dk".replace("%d", String.valueOf(d)).replace("%m", String.valueOf(m));}
                        else if ((d==0) && (m==0)) {
                            delTime = "Kalan süre, %h Saat".replace("%h", String.valueOf(h));}
                        if (d==-1) {
                            Player player = Bukkit.getPlayer(owner);
                            World world = Bukkit.getWorld((String) dataClaim.get().get(i+".World"));
                            int x = dataClaim.get().getInt(i+".Block.x");
                            int y = dataClaim.get().getInt(i+".Block.y");
                            int z = dataClaim.get().getInt(i+".Block.z");
                            Location location = new Location(world,x,y,z);
                            location.getBlock().setType(Material.AIR);
                            DHAPI.removeHologram(owner + "_" + dataClaim.get().get(i + ".firstName"));
                            dataClaim.get().set(i+"",null);
                            player.sendMessage(CColor.translateGradient("Claimin kaldırıldı!",CColor.RED,CColor.ORANGE));
                            dataClaim.save();
                        } else {
                            DHAPI.setHologramLine(hologram, 5, CColor.translateGradient(delTime, CColor.RED, CColor.ORANGE));
                            dataClaim.get().set(i + ".DeleteDate.D",d);
                            dataClaim.get().set(i + ".DeleteDate.H",h);
                            dataClaim.get().set(i + ".DeleteDate.M",m);
                            dataClaim.save();
                        }
                        i++;
                    } while (i < s);
                }
            }
        }.runTaskTimer(this, 0L, 1200L);
    }
}