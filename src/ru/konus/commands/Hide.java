package ru.konus.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerKickEvent;
import ru.konus.main.Main;

import java.util.ArrayList;
import java.util.List;

public class Hide implements CommandExecutor, Listener {

    public static List<String> toggle = new ArrayList<>();
    private Main plugin;

    public Hide(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (!(s instanceof Player)){
            s.sendMessage("§cOnly Players!");
            return true;
        }

        Player p = (Player)s;

        if (args.length != 0){
            p.sendTitle("§cПредупреждение", "§fИспользуйте §c/hide", 8, 20, 8);
            return true;
        }

        String namePlayer = p.getName();
        if (toggle.contains(namePlayer)){
            for (Player players : Bukkit.getOnlinePlayers())
                p.showPlayer(plugin, players);
            toggle.remove(namePlayer);
            p.sendTitle("§cСкрытие", "§fВы расскрыли всех игроков", 8, 18, 8);
        } else {
            for (Player players : Bukkit.getOnlinePlayers())
                p.hidePlayer(plugin, players);
            toggle.add(namePlayer);
            p.sendTitle("§aСкрытие", "§fВы скрыли всех игроков", 8, 18, 8);
        }

        return true;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        toggle.remove(e.getPlayer().getName());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        toggle.remove(e.getPlayer().getName());
    }
    
    @EventHandler
    public void onKick(PlayerKickEvent e){
        toggle.remove(e.getPlayer().getName());
    }

}
