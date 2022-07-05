package core.luuh.verioncore.utils;

import core.luuh.verioncore.VerionCore;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatMessageEvent implements Listener {

    private final VerionCore plugin;

    public ChatMessageEvent(VerionCore plugin) {this.plugin = plugin;}

    @EventHandler
    public void onMessageEvent(AsyncPlayerChatEvent e){

        String messaggio = e.getMessage();
        Chat chat = VerionCore.getChat();
        String message = plugin.getConfig().getString("chat");
        Player player = e.getPlayer();
        String playergroup = chat.getPrimaryGroup(player);
        String playergroupprefix = chat.getGroupPrefix(e.getPlayer().getWorld(), playergroup);
        String playergroupsuffix = chat.getGroupSuffix(e.getPlayer().getWorld(), playergroup);
        message.replaceAll("%player%",e.getPlayer().getDisplayName());
        message.replaceAll("%prefix%", playergroupprefix);
        message.replaceAll("%suffix%", playergroupsuffix);
        message.replaceAll("%message%", messaggio);
        e.setCancelled(true);
        plugin.getServer().broadcastMessage(chatcolor.chat(chatcolor.hex(message.replaceAll("%player%",e.getPlayer().getDisplayName()).replaceAll("%prefix%", playergroupprefix).replaceAll("%suffix%", playergroupsuffix).replaceAll("%message%", messaggio))));

    }

}
