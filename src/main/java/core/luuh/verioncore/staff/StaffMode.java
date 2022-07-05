package core.luuh.verioncore.staff;

import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class StaffMode implements CommandExecutor {

    private final VerionCore plugin;

    public StaffMode(VerionCore plugin) {
        this.plugin = plugin;
    }

    private static final HashMap<Player, Boolean> staffmode = new HashMap<Player, Boolean>();

    public void setStaffmode(Player player, Boolean bool){

        if(staffmode.get(player)!=null){
            staffmode.replace(player,bool);
        } else {
            staffmode.put(player,bool);
        }

    }

    public void setVanish(Player player, Boolean bool){

        String prefix = plugin.getConfig().getString("prefix");

        if(bool) {
            player.sendMessage(chatcolor.chat(prefix + "Hai attivato la &eStaffMode &8(&aON&8)"));
            player.chat("/staffchat on");
            player.setInvulnerable(true);
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (!(players.hasPermission("staffer"))) {
                    players.hidePlayer(player);
                }
            }
            player.setAllowFlight(true);
            setStaffmode(player, true);
        } else {
            player.sendMessage(chatcolor.chat(prefix + "Hai disattivato la &eStaffMode &8(&cOFF&8)"));
            player.chat("/staffchat off");
            player.setInvulnerable(false);
            for (Player players : Bukkit.getOnlinePlayers()) {
                if(!(players.hasPermission("staffer"))) {
                    players.showPlayer(player);
                }
            }
            player.setAllowFlight(false);
            setStaffmode(player, false);
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {

            if (command.getName().equalsIgnoreCase("staffmode")) {

                Player player = (Player) sender;

                if (player.hasPermission("staffer")) {

                    if (!(args.length > 0)) {
                        if (staffmode.get(player)==null) {
                            setVanish(player, true);
                        } else {
                            if(staffmode.get(player)){
                                setVanish(player, false);
                            } else {
                                setVanish(player, true);
                            }
                        }

                    } else {
                        if (args[0].equalsIgnoreCase("on")) {
                            setVanish(player, true);
                        } else {
                            setVanish(player, false);
                        }
                    }
                }
            }

        }
        return true;
    }
}
