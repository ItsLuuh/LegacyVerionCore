package core.luuh.verioncore.warp;

import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.GeneralUtils;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WarpCommand implements CommandExecutor, TabCompleter {
    private final VerionCore plugin;

    public WarpCommand(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settings = GeneralUtils.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player) {

            Player player = (Player) commandSender;
            //warp (Warp)

                if (!(strings.length > 0)) {
                    player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(2)));
                } else {
                    if(player.hasPermission("warps."+strings[0])) {
                        if(plugin.getConfig().getLocation("warps." + strings[0]) != null && !(strings[0].equalsIgnoreCase("list"))){
                            if(strings[1] != null){
                                if(plugin.getServer().getPlayer(strings[1]) != null && player.hasPermission("*")){
                                    player= plugin.getServer().getPlayer(strings[1]);
                                }
                            }

                            Location loc = plugin.getConfig().getLocation("warps." + strings[0]);
                            player.teleport(loc);
                            player.sendMessage(chatcolor.chat(settings.getFromConfigU("MSG_WARP_TP", player, strings[0])));
                        } else {
                            player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(4)));
                        }
                    }else {
                        player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(1)));
                    }

                }
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length == 1){
            List<String> arguments = new ArrayList<>();
            for(String s : plugin.getConfig().getStringList("warps.list")){
                arguments.add(s);
            }
            return arguments;

        }

        return null;
    }
}
