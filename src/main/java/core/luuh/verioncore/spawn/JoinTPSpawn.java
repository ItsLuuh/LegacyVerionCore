package core.luuh.verioncore.spawn;

import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.GeneralUtils;
import core.luuh.verioncore.utils.chatcolor;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinTPSpawn implements Listener {

    private final VerionCore plugin;

    public JoinTPSpawn(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settings = GeneralUtils.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        boolean jointpspawn = plugin.getConfig().getBoolean("spawn.join-tp-spawn");
        if(jointpspawn){
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.teleport(plugin.getConfig().getLocation("spawn.loc"));
                }
            }.runTaskLater(plugin, 2L);
        }
        boolean titles = plugin.getConfig().getBoolean("spawn.title.join-title");
        if(titles) {
            player.sendMessage("TWEWEADWADS");
            String t = chatcolor.chat(chatcolor.hex(settings.getFromConfigS("spawn.title.join-title-msg")));
            String st = chatcolor.chat(chatcolor.hex(settings.getFromConfigS("spawn.title.join-subtitle-msg")));

            //PlaceholderAPI.setPlaceholders(e.getPlayer(), t),PlaceholderAPI.setPlaceholders(e.getPlayer(), st)
            player.sendTitle("t","w", Integer.parseInt(settings.getFromConfigS("spawn.title.fadeIn")), Integer.parseInt(settings.getFromConfigS("spawn.title.stay")), Integer.parseInt(settings.getFromConfigS("spawn.title.fadeOut")));
        }
    }

}
