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
        if(!cmd.getName().equalsIgnoreCase("setspawn")) return true;

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

        plugin.getConfig().set("spawn.x", player.getLocation().getX());
        plugin.getConfig().set("spawn.y", player.getLocation().getY());
        plugin.getConfig().set("spawn.z", player.getLocation().getZ());
        plugin.getConfig().set("spawn.yaw", player.getLocation().getYaw());
        plugin.getConfig().set("spawn.pitch", player.getLocation().getPitch());
        plugin.saveConfig();

        player.sendMessage("§7Координаты: §eX: §b" + (int) player.getLocation().getX() +
                "§7, §eY: §b" + (int) player.getLocation().getY() +
                "§7, §eZ: §b" + (int) player.getLocation().getZ() +
                "§7, §eYaw: §b" + (int) player.getLocation().getYaw() +
                "§7, §ePitch: §b" + (int) player.getLocation().getPitch());
        player.sendTitle("§aСпавн", "§7Вы установили спавн", 8, 15, 8);

        return true;
    }
}
