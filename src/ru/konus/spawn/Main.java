package ru.konus.spawn;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable(){

        getLogger().info("§aPlugin Enabled");
        getCommand("spawn").setExecutor(new Spawn(this));
        getCommand("setspawn").setExecutor(new SetSpawn(this));
        Bukkit.getPluginManager().registerEvents(new SpawnListener(this), this);
    }

    public void onDisable(){
        getLogger().info("§cPlugin Disabled");
    }

}
