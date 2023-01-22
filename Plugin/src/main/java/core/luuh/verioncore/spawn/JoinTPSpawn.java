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
        boolean onjoinmessage = settings.getBooleanFromConfig("spawn.on-join-message.enabled");
        if(onjoinmessage) {
            List<String> messages = plugin.getConfig().getStringList("spawn.on-join-message.message");
            for (String message : messages) {
                player.sendMessage(chatcolor.chat(chatcolor.hex(PlaceholderAPI.setPlaceholders(e.getPlayer(), message.replaceAll("%prefix%", plugin.getConfig().getString("prefix"))))));
            }
        }

        boolean titles = settings.getBooleanFromConfig("spawn.title.enabled");
        if(titles) {
            String t = chatcolor.chat(chatcolor.hex(settings.getFromConfigS("spawn.title.join-title-msg")));
            String st = chatcolor.chat(chatcolor.hex(settings.getFromConfigS("spawn.title.join-subtitle-msg")));

            player.sendTitle(PlaceholderAPI.setPlaceholders(e.getPlayer(), t),PlaceholderAPI.setPlaceholders(e.getPlayer(), st), Integer.parseInt(settings.getFromConfigS("spawn.title.fadeIn")), Integer.parseInt(settings.getFromConfigS("spawn.title.stay")), Integer.parseInt(settings.getFromConfigS("spawn.title.fadeOut")));
        }
    }

}
