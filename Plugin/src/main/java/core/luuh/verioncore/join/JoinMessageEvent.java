package core.luuh.verioncore.join;

import core.luuh.verioncore.utils.chatcolor;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.GeneralUtils;

import java.util.List;

public class JoinMessageEvent implements Listener {

    private final VerionCore plugin;

    public JoinMessageEvent(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settings = GeneralUtils.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        String prefix = plugin.getConfig().getString("prefix");
        Player player = e.getPlayer();
        String playername = player.getDisplayName();
        int joins = plugin.getConfig().getInt("joins");
        String joinmessage = settings.getFromConfig("joinmessage", player).replaceAll("%joins%", String.valueOf(joins));
        if(player.hasPlayedBefore()){
            e.setJoinMessage(chatcolor.chat(settings.getFromConfig("JOIN_MSG",player)));
        } else{
            e.setJoinMessage(chatcolor.chat(joinmessage));
            plugin.getConfig().set("joins", joins+1);
            plugin.saveConfig();
        }
        boolean onjoinmessage = settings.getBooleanFromConfig("spawn.on-join-message.enabled");
        if(onjoinmessage) {
            List<String> messages = plugin.getConfig().getStringList("spawn.on-join-message.message");
            for (String message : messages) {
                player.sendMessage(chatcolor.chat(chatcolor.hex(PlaceholderAPI.setPlaceholders(e.getPlayer(), message.replaceAll("%prefix%", plugin.getConfig().getString("prefix")).replaceAll("%player%", playername)))));
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
