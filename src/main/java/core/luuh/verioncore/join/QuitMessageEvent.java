package core.luuh.verioncore.join;

import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitMessageEvent implements Listener {

    private final VerionCore plugin;

    public QuitMessageEvent(VerionCore plugin) {this.plugin = plugin;}

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        String player = e.getPlayer().getDisplayName();
        e.setQuitMessage(chatcolor.chat("&8[&c-&8] &7"+player));
    }

}
