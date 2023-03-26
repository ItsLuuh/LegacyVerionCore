package core.luuh.verioncore.join;

import core.luuh.verioncore.VerionAPIManager;
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

import static core.luuh.verioncore.VerionAPIManager.*;

public class JoinMessageEvent implements Listener {

    private final VerionCore plugin;

    public JoinMessageEvent(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settingsGeneral = GeneralUtils.getInstance();
    private final SettingsManager settingsManager = SettingsManager.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        String prefix = plugin.getConfig().getString("prefix");
        Player player = e.getPlayer();
        String playername = player.getDisplayName();
        int joins = plugin.getConfig().getInt("joins");
        String joinmessage = settingsGeneral.getFromConfig("joinmessage", player).replaceAll("%joins%", String.valueOf(joins));
        if(player.hasPlayedBefore()){
            e.setJoinMessage(chatcolor.chat(settingsGeneral.getFromConfig("JOIN_MSG",player)));
            UUID puid = player.getUniqueId();
            settingsManager.getData().set(puid+".name", playername);

            if(settingsManager.getData().getString(puid+".nickcolor") == null){
                settingsGeneral.setNickColor(ChatColor.WHITE, player);
            }

            if(settingsManager.getData().getString(puid+".chatcolor") == null){
                settingsGeneral.setChatColor(ChatColor.WHITE, player);}
            settingsManager.saveData();
        } else{


            e.setJoinMessage(chatcolor.chat(joinmessage));
            plugin.getConfig().set("joins", joins+1);
            plugin.saveConfig();
            UUID puid = player.getUniqueId();
            settingsManager.getData().set(puid+".name", playername);
            settingsGeneral.setNickColor(ChatColor.WHITE, player);
            settingsGeneral.setChatColor(ChatColor.WHITE, player);
            settingsGeneral.setBChatSpecial("bold", player, false);
            settingsGeneral.setBChatSpecial("italic", player, false);
            settingsGeneral.setBChatSpecial("underlined", player, false);
            settingsGeneral.setBChatSpecial("strikethrough", player, false);
            settingsManager.saveData();
        }
        boolean onjoinmessage = settingsGeneral.getBooleanFromConfig("spawn.on-join-message.enabled");
        if(onjoinmessage) {
            List<String> messages = plugin.getConfig().getStringList("spawn.on-join-message.message");
            for (String message : messages) {
                player.sendMessage(chatcolor.chat(chatcolor.hex(PlaceholderAPI.setPlaceholders(e.getPlayer(), message.replaceAll("%prefix%", plugin.getConfig().getString("prefix")).replaceAll("%player%", playername)))));
            }
        }

        boolean titles = settingsGeneral.getBooleanFromConfig("spawn.title.enabled");
        if(titles) {
            String t = chatcolor.chat(chatcolor.hex(settingsGeneral.getFromConfigS("spawn.title.join-title-msg")));
            String st = chatcolor.chat(chatcolor.hex(settingsGeneral.getFromConfigS("spawn.title.join-subtitle-msg")));

            player.sendTitle(PlaceholderAPI.setPlaceholders(e.getPlayer(), t),PlaceholderAPI.setPlaceholders(e.getPlayer(), st), Integer.parseInt(settingsGeneral.getFromConfigS("spawn.title.fadeIn")), Integer.parseInt(settingsGeneral.getFromConfigS("spawn.title.stay")), Integer.parseInt(settingsGeneral.getFromConfigS("spawn.title.fadeOut")));
        }
    }

}
