package core.luuh.verioncore.utils;

import core.luuh.verioncore.VerionCore;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class NoFoodChange implements Listener {

    private final VerionCore plugin;

    public NoFoodChange(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settings = GeneralUtils.getInstance();

    @EventHandler
    public void foodChange(FoodLevelChangeEvent e){
        boolean foodchange = plugin.getConfig().getBoolean("REMOVE_FOOD");
        if(foodchange){
            e.setCancelled(true);
        }
    }

}