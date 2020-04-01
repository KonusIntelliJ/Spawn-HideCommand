package ru.konus.spawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

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

        configFile = new File(plugin.getDataFolder(), "config.yml");
        if(!configFile.exists()) return;

        double x = plugin.getConfig().getDouble("spawn.x");
        double y = plugin.getConfig().getDouble("spawn.y");
        double z = plugin.getConfig().getDouble("spawn.z");
        float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
        float pitch = (float) plugin.getConfig().getDouble("spawn.pitch");
        Location location = new Location(Bukkit.getWorld("world"), x, y, z, yaw, pitch);
        player.teleport(location);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        configFile = new File(plugin.getDataFolder(), "config.yml");
        if(!configFile.exists()) return;

        double x = plugin.getConfig().getDouble("spawn.x");
        double y = plugin.getConfig().getDouble("spawn.y");
        double z = plugin.getConfig().getDouble("spawn.z");
        float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
        float pitch = (float) plugin.getConfig().getDouble("spawn.pitch");
        Location location = new Location(Bukkit.getWorld("world"), x, y, z, yaw, pitch);
        event.setRespawnLocation(location);
    }

}
