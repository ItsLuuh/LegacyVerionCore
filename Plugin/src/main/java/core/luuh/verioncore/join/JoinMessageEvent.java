package core.luuh.verioncore.join;

import core.luuh.verioncore.VerionAPI;
import core.luuh.verioncore.utils.SettingsManager;
import core.luuh.verioncore.utils.chatcolor;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.GeneralUtils;

import java.util.List;
import java.util.UUID;

import static core.luuh.verioncore.VerionAPI.*;

public class JoinMessageEvent implements Listener {

    private final VerionCore plugin;

    public JoinMessageEvent(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settings = GeneralUtils.getInstance();
    private final SettingsManager settingsm = SettingsManager.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        String prefix = plugin.getConfig().getString("prefix");
        Player player = e.getPlayer();
        String playername = player.getDisplayName();
        int joins = plugin.getConfig().getInt("joins");
        String joinmessage = settings.getFromConfig("joinmessage", player).replaceAll("%joins%", String.valueOf(joins));
        if(player.hasPlayedBefore()){
            e.setJoinMessage(chatcolor.chat(settings.getFromConfig("JOIN_MSG",player)));
            UUID puid = player.getUniqueId();
            settingsm.getData().set(puid+".name", playername);

            if(settingsm.getData().getString(puid+".nickcolor") == null){
                setNickColor(ChatColor.WHITE, player);
            }

            if(settingsm.getData().getString(puid+".chatcolor") == null){
                setChatColor(ChatColor.WHITE, player);}
            settingsm.saveData();
        } else{


            e.setJoinMessage(chatcolor.chat(joinmessage));
            plugin.getConfig().set("joins", joins+1);
            plugin.saveConfig();
            UUID puid = player.getUniqueId();
            settingsm.getData().set(puid+".name", playername);
            setNickColor(ChatColor.WHITE, player);
            setChatColor(ChatColor.WHITE, player);
            setChatSpecial("bold", player);
            setChatSpecial("italic", player);
            setChatSpecial("underlined", player);
            setChatSpecial("strikethrough", player);
            settingsm.saveData();
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
