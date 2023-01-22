package core.luuh.verioncore.spawn;

import core.luuh.verioncore.utils.chatcolor;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.GeneralUtils;

import java.util.List;

public class JoinTPSpawn implements Listener {

    private final VerionCore plugin;

    public JoinTPSpawn(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settings = GeneralUtils.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        boolean jointpspawn = settings.getBooleanFromConfig("spawn.join-tp-spawn");
        if(jointpspawn){
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.teleport(plugin.getConfig().getLocation("spawn.loc"));
                }
            }.runTaskLater(plugin, 2L);
        }
    }

}
