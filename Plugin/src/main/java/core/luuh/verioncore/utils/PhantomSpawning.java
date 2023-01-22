package core.luuh.verioncore.utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import core.luuh.verioncore.VerionCore;

import java.util.List;

public class PhantomSpawning implements Listener {
    private final VerionCore plugin;
    private static GeneralUtils settings = GeneralUtils.getInstance();

    public PhantomSpawning(final VerionCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPhantomSpawn(final EntitySpawnEvent e) {
        final Entity entity = e.getEntity();
        if (entity.getType() == EntityType.PHANTOM && settings.getBooleanFromConfig("REMOVE_PHANTOMS.enabled")) {
            if (settings.getBooleanFromConfig("REMOVE_PHANTOMS.every-world")) {
                e.setCancelled(true);
            } else {
                List<String> worlds = this.plugin.getConfig().getStringList("REMOVE_PHANTOMS.worlds");
                for (String world : worlds) {
                    if (e.getEntity().getWorld().getName().equals(world)) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
