package core.luuh.verioncore;

import core.luuh.verioncore.utils.SettingsManager;
import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import core.luuh.verioncore.utils.GeneralUtils;


import java.util.ArrayList;
import java.util.List;

public class CoreCommand implements CommandExecutor, TabCompleter {

    private final VerionCore plugin;

    public CoreCommand(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settings = GeneralUtils.getInstance();
    private final SettingsManager settingsm = SettingsManager.getInstance();

    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label,  String[] args) {

        if(command.getName().equalsIgnoreCase("core")) {

            Player player = (Player) sender;
            String versione = plugin.getDescription().getVersion();

            if (args.length > 0) {

                if (player.hasPermission("*")) {

                    if (args[0].equalsIgnoreCase("reload")) {
                        plugin.reloadConfig();
                        settingsm.reloadData();
                        sender.sendMessage("");
                        sender.sendMessage(chatcolor.chat("&6[&e!&6] &lVERION&r &fCore &ov" + versione + " &6[&e!&6]"));
                        sender.sendMessage(chatcolor.chat(settings.getCaseFromConfig(5)));

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
            } else {
                sender.sendMessage("");
                sender.sendMessage(chatcolor.chat("&6[&e!&6] &lVERION&r &fCore &ov" + versione + " &6[&e!&6]"));
                sender.sendMessage(chatcolor.chat("&fBy ItsLuuh."));
                sender.sendMessage("");
            }
        }

        return true;
    }

    
    @Override
    public List<String> onTabComplete( CommandSender sender,  Command command,  String label,  String[] args) {

        if(args.length == 1){
            List<String> arguments = new ArrayList<>();
            arguments.add("reload");
            return arguments;

        }

        return null;
    }
}
