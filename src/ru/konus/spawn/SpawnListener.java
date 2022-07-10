package ru.konus.spawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import ru.konus.main.Main;

import java.io.File;

public class SpawnListener implements Listener {

    private Main plugin;
    File configFile;

    public SpawnListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Location spawnLocation = plugin.getSpawnLocation();
        if (spawnLocation == null) 
            return;
        player.teleport(spawnLocation);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        Location spawnLocation = plugin.getSpawnLocation();
        if (spawnLocation == null) 
            return;
        event.setRespawnLocation(spawnLocation);
    }

}
