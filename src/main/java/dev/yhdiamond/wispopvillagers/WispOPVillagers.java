package dev.yhdiamond.wispopvillagers;

import dev.yhdiamond.wispopvillagers.bstats.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class WispOPVillagers extends JavaPlugin {
    public static WispOPVillagers plugin;
    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new SpawnListener(), this);
        saveDefaultConfig();
        getConfig().options().copyDefaults();
        new Metrics(this, 10786);
    }

}
