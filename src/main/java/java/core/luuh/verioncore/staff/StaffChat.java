package java.core.luuh.verioncore.staff;

import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class StaffChat implements CommandExecutor {

    private final VerionCore plugin;

    public StaffChat(VerionCore plugin) {
        this.plugin = plugin;
    }

    private static HashMap<Player, Boolean> staffchat = new HashMap<Player, Boolean>();

    public static Boolean getStaffChat(Player player) {

        if(staffchat.get(player) == null){
            return false;
        } else {
            return staffchat.get(player);
        }
    }

    public static void replaceStaffchat(Player player, Boolean bool) {
        if(staffchat.get(player)!=null){
            staffchat.replace(player,bool);
        } else {
            staffchat.put(player,bool);
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player) {

            if (command.getName().equalsIgnoreCase("staffchat")) {

                Player player = (Player) sender;
                String prefix = plugin.getConfig().getString("prefix");
                String playername = player.getDisplayName();

                if (player.hasPermission("staffer")) {
                    if (!(args.length > 0)) {
                        if (getStaffChat(player)) {
                            player.sendMessage(chatcolor.chat(prefix + "Hai disattivato la &eStaffChat &8(&cOFF&8)"));
                            replaceStaffchat(player, false);
                        } else {
                            player.sendMessage(chatcolor.chat(prefix + "Hai attivato la &eStaffChat &8(&aON&8)"));
                            replaceStaffchat(player, true);
                        }

                    } else {
                        if (args[0].equalsIgnoreCase("on")) {
                            player.sendMessage(chatcolor.chat(prefix + "Hai attivato la &eStaffChat &8(&aON&8)"));
                            replaceStaffchat(player, true);
                        } else if (args[0].equalsIgnoreCase("off")) {
                            player.sendMessage(chatcolor.chat(prefix + "Hai disattivato la &eStaffChat &8(&cOFF&8)"));
                            replaceStaffchat(player, false);
                        } else {
                            StringBuilder str = new StringBuilder();
                            for (String arg : args) {
                                str.append(arg).append(" ");
                            }
                            String message = str.toString().trim();
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.hasPermission("staffer")) {
                                    p.sendMessage(chatcolor.chat("&8(&9&lSTAFFCHAT&8) &f" + playername + " &8» &7" + message));

                                }

                            }
                        }
                    }
                }
            }


        }

        return true;
    }
}
