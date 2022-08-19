package core.luuh.verioncore.utils;

import core.luuh.verioncore.VerionCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class NoDamageForever implements Listener {

    private final VerionCore plugin;

    public NoDamageForever(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settings = GeneralUtils.getInstance();

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e){

        if(settings.getBooleanFromConfig("REMOVE_DAMAGE")) {
            if (e.getEntity() instanceof Player) {
                if(!(e.getCause() == EntityDamageEvent.DamageCause.VOID)) {
                    e.setCancelled(true);
                }
            }
        }

    }

}
