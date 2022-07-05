package core.luuh.verioncore.utils;

import core.luuh.verioncore.VerionCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class VoidTP implements Listener {

    private final VerionCore plugin;

    public VoidTP(VerionCore plugin) {this.plugin = plugin;}

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            if (e.getCause() == EntityDamageEvent.DamageCause.VOID){
                e.setCancelled(true);
                Player p = (Player) e.getEntity();
                p.teleport(plugin.getConfig().getLocation("spawn.loc"));

            }
        }
    }


}
