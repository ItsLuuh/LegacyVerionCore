package core.luuh.verioncore;

import core.luuh.verioncore.utils.GeneralUtils;
import core.luuh.verioncore.utils.chatcolor;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CoreCommand implements CommandExecutor {

    private final VerionCore plugin;

    public CoreCommand(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settings = GeneralUtils.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(command.getName().equalsIgnoreCase("core")) {

            Player player = (Player) sender;
            String versione = plugin.getDescription().getVersion();

            if (args.length > 0) {

                if (player.hasPermission("*")) {

                    if (args[0].equalsIgnoreCase("reload")) {
                        plugin.reloadConfig();
                        sender.sendMessage("");
                        sender.sendMessage(chatcolor.chat("&6[&e!&6] &lVERION&r &fCore &ov" + versione + " &6[&e!&6]"));
                        sender.sendMessage(chatcolor.chat(settings.getCaseFromConfig(5)));

                    } else if (args[0].equalsIgnoreCase("help")) {

                        List<String> messages = plugin.getConfig().getStringList("MSG_CORE_HELP");
                        for (String message : messages) {
                            player.sendMessage(chatcolor.chat(chatcolor.hex(PlaceholderAPI.setPlaceholders(player, message.replaceAll("%prefix%", plugin.getConfig().getString("prefix"))))));
                        }

                    } else {
                        sender.sendMessage(chatcolor.chat(settings.getCaseFromConfig(1)));
                    }

                } else {
                    sender.sendMessage("");
                    sender.sendMessage(chatcolor.chat("&6[&e!&6] &lVERION&r &fCore &ov" + versione + " &6[&e!&6]"));
                    sender.sendMessage(chatcolor.chat("&fBy ItsLuuh."));
                    sender.sendMessage("");
                }
            } else {
                sender.sendMessage("");
                sender.sendMessage(chatcolor.chat("&6[&e!&6] &lVERION&r &fCore &ov" + versione + " &6[&e!&6]"));
                sender.sendMessage(chatcolor.chat("&fBy ItsLuuh."));
                sender.sendMessage("");
            }
        }

        return true;
    }
}
