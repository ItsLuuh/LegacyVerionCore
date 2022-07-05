package java.core.luuh.verioncore;

import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CoreCommand implements CommandExecutor {

    private final VerionCore plugin;

    public CoreCommand(VerionCore plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(command.getName().equalsIgnoreCase("core")){

            Player player = (Player) sender;
            String versione = plugin.getDescription().getVersion();
            String prefix = plugin.getConfig().getString("prefix");

            sender.sendMessage("");

            if (args.length > 0) {

                if (player.hasPermission("*")) {

                    if(args[0].equalsIgnoreCase("reload")) {
                        plugin.reloadConfig();
                        sender.sendMessage(chatcolor.chat(prefix + "&aTutti i Config reloaddati con successo."));

                    }

                } else {
                    sender.sendMessage(chatcolor.chat(prefix + "Non hai il permesso per eseguire questo comando."));
                }

            }

            sender.sendMessage(chatcolor.chat("&6[&e!&6] &lVERION&r &fCore &ov" + versione  + " &6[&e!&6]"));
            sender.sendMessage(chatcolor.chat("&fFatto da ItsLuuh."));
            sender.sendMessage("");
        }

        return true;
    }
}
