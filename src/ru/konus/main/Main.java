package ru.konus.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.konus.commands.Hide;
import ru.konus.spawn.SetSpawn;
import ru.konus.spawn.Spawn;
import ru.konus.spawn.SpawnListener;

public class Main extends JavaPlugin {

    public void onEnable(){

        getLogger().info("§aPlugin Enabled");
        getCommand("spawn").setExecutor(new Spawn(this));
        getCommand("setspawn").setExecutor(new SetSpawn(this));
        getCommand("hide").setExecutor(new Hide(this));
        Bukkit.getPluginManager().registerEvents(new SpawnListener(this), this);
        Bukkit.getPluginManager().registerEvents(new Hide(this), this);
    }

    public void onDisable(){
        getLogger().info("§cPlugin Disabled");
    }

}
