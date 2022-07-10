package ru.konus.spawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.konus.main.Main;

import java.io.File;

public class Spawn implements CommandExecutor {

    private Main plugin;

    public Spawn(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (!(s instanceof Player)){
            s.sendMessage("§cOnly Players!");
            return true;
        }

        Player player = (Player)s;

        if (args.length != 0){
            player.sendTitle("§cПредупреждение", "§7Используйте §c/spawn", 8, 10, 8);
            return true;
        }

        Location spawnLocation = plugin.getSpawnLocation();
        if (spawn == null) {
            player.sendTitle("§cПредупреждение", "§7Спавн не установлен", 8, 10, 8);
            return;
        }
       
        player.teleport(spawnLocation);
        player.sendTitle("§aТелепорт", "§7Вы телепортированы на спавн", 8, 15, 8);
        return true;
    }
}
