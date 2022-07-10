package ru.konus.spawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.konus.main.Main;

public class SetSpawn implements CommandExecutor {

    private Main plugin;

    public SetSpawn(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(!(s instanceof Player)){
            s.sendMessage("§cOnly players!");
            return true;
        }

        Player player = (Player)s;

        if(!player.hasPermission("spawn.admin")){
            player.sendTitle("§cОшибка", "§7Недостаточно прав", 8, 10, 8);
            return true;
        }

        if(args.length == 1 && args[0].equalsIgnoreCase("reload")){
            plugin.reloadConfig();
            player.sendTitle("§aПлагин", "§7Был перезагружен", 8, 10, 8);
            return true;
        }

        if(args.length != 0){
            player.sendTitle("§cПредупреждение", "§7Используйте §c/setspawn", 8, 10, 8);
            return true;
        }

        Location location = player.getLocation();	
	FileConfiguration config = plugin.getConfig();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
	float yaw = location.getYaw();
	float pitch = location.getPitch();
        String spawnStr = "spawn";
	    
        config.set(spawnStr + ".x", x);
        config.set(spawnStr + ".y", y);
        config.set(spawnStr + ".z", z);
        config.set(spawnStr + ".yaw", yaw);
        config.set(spawnStr + ".pitch", pitch);
        plugin.saveConfig();

        player.sendMessage("§7Координаты: §eX: §b" + (int) x +
                "§7, §eY: §b" + (int) y +
                "§7, §eZ: §b" + (int) z) +
                "§7, §eYaw: §b" + (int) yaw +
                "§7, §ePitch: §b" + (int) pitch);
        player.sendTitle("§aСпавн", "§7Вы установили спавн", 8, 15, 8);

        return true;
    }
}
