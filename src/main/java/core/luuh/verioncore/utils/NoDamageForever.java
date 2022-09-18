package core.luuh.verioncore.utils;

import core.luuh.verioncore.VerionCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.List;

public class NoDamageForever implements Listener {

    private final VerionCore plugin;

    public NoDamageForever(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settings = GeneralUtils.getInstance();

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (!(e.getCause() == EntityDamageEvent.DamageCause.VOID)) {
                if(settings.getBooleanFromConfig("REMOVE_DAMAGE.enabled")) {
                    if(settings.getBooleanFromConfig("REMOVE_DAMAGE.every-world")){
                        e.setCancelled(true);
                    } else {
                        List<String> worlds = plugin.getConfig().getStringList("REMOVE_DAMAGE.worlds");
                        for(String world : worlds){
                            if(e.getEntity().getWorld().getName().equals(world)){
                                e.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }

    }

}
