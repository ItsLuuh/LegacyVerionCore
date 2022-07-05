package core.luuh.verioncore.join;

import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinMessageEvent implements Listener {

    private final VerionCore plugin;

    public JoinMessageEvent(VerionCore plugin) {this.plugin = plugin;}

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        String prefix = plugin.getConfig().getString("prefix");
        Player player = e.getPlayer();
        String playername = player.getDisplayName();
        int joins = plugin.getConfig().getInt("joins");
        boolean jointpspawn = plugin.getConfig().getBoolean("join-tp-spawn");
        String joinmessage = plugin.getConfig().getString("joinmessage").replaceAll("%player%", playername).replaceAll("%prefix%", prefix).replaceAll("%joins%", String.valueOf(joins));
        if(player.hasPlayedBefore()){
            e.setJoinMessage(chatcolor.chat("&8[&a+&8] &7" + playername));
        } else{
            e.setJoinMessage(chatcolor.chat(joinmessage));
            plugin.getConfig().set("joins", joins+1);
            plugin.saveConfig();
        }
        if(jointpspawn){
            player.teleport(plugin.getConfig().getLocation("spawn.loc"));
        }
    }

}
