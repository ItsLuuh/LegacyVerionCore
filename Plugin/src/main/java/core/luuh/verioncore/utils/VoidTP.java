package core.luuh.verioncore.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import core.luuh.verioncore.VerionCore;

import java.util.List;

public class VoidTP implements Listener {

    private final VerionCore plugin;

    public VoidTP(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settings = GeneralUtils.getInstance();
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                Player p = (Player) e.getEntity();
                if (settings.getBooleanFromConfig("VOIDTP.enabled")) {
                    if (settings.getBooleanFromConfig("VOIDTP.every-world")) {
                        e.setCancelled(true);
                        p.teleport(plugin.getConfig().getLocation("spawn.loc"));
                    } else {
                        List<String> worlds = plugin.getConfig().getStringList("VOIDTP.worlds");
                        for(String world : worlds){
                            if(p.getWorld().getName().equals(world)){
                                e.setCancelled(true);
                                p.teleport(plugin.getConfig().getLocation("spawn.loc"));
                            }
                        }
                    }
                }
            }
        }
    }


}
