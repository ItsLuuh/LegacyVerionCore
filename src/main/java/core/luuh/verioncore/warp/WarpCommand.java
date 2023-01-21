package core.luuh.verioncore.warp;

import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.GeneralUtils;
import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpCommand implements CommandExecutor {
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
                        plugin.getConfig().set("warps." + strings[0], player.getLocation());
                        plugin.saveConfig();
                        player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_WARP_TP", null)));
                    }else {
                        player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(1)));
                    }

                }
        }
        return true;
    }
}
