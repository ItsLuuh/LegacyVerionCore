package core.luuh.verioncore.utils;

import core.luuh.verioncore.VerionCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.List;

public class NoRainForever implements Listener {

    private final VerionCore plugin;

    private static GeneralUtils settings = GeneralUtils.getInstance();

    public NoRainForever(VerionCore plugin) {this.plugin = plugin;}

    @EventHandler
    public void onRainEvent(WeatherChangeEvent e){
        if(settings.getBooleanFromConfig("REMOVE_RAIN.enabled")) {
            if (settings.getBooleanFromConfig("REMOVE_RAIN.every-world")) {
                e.setCancelled(e.toWeatherState());
            } else {
                List<String> worlds = plugin.getConfig().getStringList("REMOVE_RAIN.worlds");
                for (String world : worlds) {
                    if (e.getWorld().getName().equals(world)) {
                        e.setCancelled(e.toWeatherState());
                    }
                }
            }
        }
    }
}
