package core.luuh.verioncore.utils;

import core.luuh.verioncore.VerionCore;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class NoFoodChange implements Listener {

    private final VerionCore plugin;

    public NoFoodChange(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settings = GeneralUtils.getInstance();

    @EventHandler
    public void foodChange(FoodLevelChangeEvent e){
        if(settings.getBooleanFromConfig("REMOVE_FOOD.enabled")) {
            if (settings.getBooleanFromConfig("REMOVE_FOOD.every-world")) {
                e.setCancelled(true);
            } else {
                List<String> worlds = plugin.getConfig().getStringList("REMOVE_FODD.worlds");
                for (String world : worlds) {
                    if (e.getEntity().getWorld().getName().equals(world)) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

}