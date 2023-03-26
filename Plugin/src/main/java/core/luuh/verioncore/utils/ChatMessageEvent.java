package core.luuh.verioncore.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.format.TextFormat;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.TextComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import core.luuh.verioncore.VerionCore;

import java.util.UUID;

import static core.luuh.verioncore.VerionAPI.setChatColor;
import static core.luuh.verioncore.VerionAPI.setNickColor;

public class ChatMessageEvent implements Listener {

    private final VerionCore plugin;

    public ChatMessageEvent(VerionCore plugin) {this.plugin = plugin;}

    private final SettingsManager settings = SettingsManager.getInstance();

    @EventHandler
    public void onMessageEvent(AsyncPlayerChatEvent e){

        Player player = e.getPlayer();
        UUID puid = e.getPlayer().getUniqueId();
        String messaggio = e.getMessage();
        char nc,cc;

        if(settings.getData().getString(puid+".nickcolor") != null){
            nc = settings.getData().getString(puid+".nickcolor").charAt(0);
        } else {
            setNickColor(ChatColor.WHITE, player);
            nc = settings.getData().getString(puid+".nickcolor").charAt(0);
        }

        if(settings.getData().getString(puid+".chatcolor") != null){
            cc = settings.getData().getString(puid+".chatcolor").charAt(0);
        } else {
            setChatColor(ChatColor.WHITE, player);
            cc = settings.getData().getString(puid+".chatcolor").charAt(0);
        }



        TextComponent textComponent1 = new TextComponent();
        textComponent1.setColor(ChatColor.getByChar(nc).asBungee());
        textComponent1.setText(player.getDisplayName());
        String playername = TextComponent.toLegacyText(textComponent1);

        TextComponent textComponent = new TextComponent(messaggio);
        textComponent.setColor(ChatColor.getByChar(cc).asBungee());

        if(settings.getData().getBoolean(puid + ".chatbold"))textComponent.setBold(true);
        if(settings.getData().getBoolean(puid + ".chatitalic"))textComponent.setItalic(true);
        if(settings.getData().getBoolean(puid + ".chatunderlined"))textComponent.setUnderlined(true);
        if(settings.getData().getBoolean(puid + ".chatstrikethrough"))textComponent.setStrikethrough(true);
        messaggio = TextComponent.toLegacyText(textComponent);

        String message = chatcolor.chat(chatcolor.hex(plugin.getConfig().getString("chat").replaceAll("%player%", playername)));
        e.setCancelled(true);
        if(player.hasPermission("*")) {
            plugin.getServer().broadcastMessage(chatcolor.chat(chatcolor.hex(PlaceholderAPI.setPlaceholders(player, message).replaceAll("%message%", messaggio))));
        } else {
            plugin.getServer().broadcastMessage(PlaceholderAPI.setPlaceholders(player, message).replaceAll("%message%",  messaggio));
        }

    }



}
