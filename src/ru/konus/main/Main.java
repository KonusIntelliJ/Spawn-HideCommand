package ru.konus.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.konus.commands.Hide;
import ru.konus.spawn.SetSpawn;
import ru.konus.spawn.Spawn;
import ru.konus.spawn.SpawnListener;

import java.io.File;

public class Main extends JavaPlugin {
    
    private Location spawn;
    private Main plugin;
    
    public void onEnable() {
        plugin = this;
        getCommand("spawn").setExecutor(new Spawn(this));
        getCommand("setspawn").setExecutor(new SetSpawn(this));
        getCommand("hide").setExecutor(new Hide(this));
        Bukkit.getPluginManager().registerEvents(new SpawnListener(this), this);
        Bukkit.getPluginManager().registerEvents(new Hide(this), this);

        File file = new File(getDataFolder(), "config.yml");
        if (file.exists()) 
            loadSpawnLocation();
         else
             spawn = null;
        getLogger().info("§7[§6!§7] §fPlugin §6Spawn §fwas enabled!");
    }
    
    public void loadSpawnLocation() {
         FileConfiguration config = plugin.getConfig();
         String spawnStr = "spawn";
         double x = config.getDouble(spawnStr + ".x");
         double y = config.getDouble(spawnStr + ".y");
         double z = config.getDouble(spawnStr + ".z");
         float yaw = (float) config.getDouble(spawnStr + ".yaw");
         float pitch = (float) config.getDouble(spawnStr + ".pitch");
         spawn = new Location(Bukkit.getWorld("world"), x, y, z, yaw, pitch);
    }
    
    public Location getSpawnLocation() {
        return spawn;
    }

    public void onDisable(){
        getLogger().info("§7[§c!§7] §fPlugin §cSpawn §fwas disabled!");
    }

}
