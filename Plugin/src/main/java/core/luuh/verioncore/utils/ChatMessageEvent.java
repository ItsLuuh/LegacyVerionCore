package core.luuh.verioncore.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import core.luuh.verioncore.VerionCore;

public class ChatMessageEvent implements Listener {

    private final VerionCore plugin;

    public ChatMessageEvent(VerionCore plugin) {this.plugin = plugin;}

    @EventHandler
    public void onMessageEvent(AsyncPlayerChatEvent e){

        String messaggio = e.getMessage();
        String message = chatcolor.chat(chatcolor.hex(plugin.getConfig().getString("chat")));
        if(message != null ) {
            e.setCancelled(true);
            if(e.getPlayer().hasPermission("*")) {
                plugin.getServer().broadcastMessage(chatcolor.chat(chatcolor.hex(PlaceholderAPI.setPlaceholders(e.getPlayer(), message).replaceAll("%message%", messaggio))));
            } else {
                plugin.getServer().broadcastMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), message).replaceAll("%message%", messaggio));

            }
        }

    }

}
