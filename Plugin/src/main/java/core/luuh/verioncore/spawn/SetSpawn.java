package core.luuh.verioncore.spawn;

import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.GeneralUtils;

public class SetSpawn implements CommandExecutor {

    private final VerionCore plugin;

    public SetSpawn(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settings = GeneralUtils.getInstance();

    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label,  String[] args) {

        if(sender instanceof Player) {
            if(command.getName().equalsIgnoreCase("setspawn")) {

                Player player = (Player) sender;
                String prefix = plugin.getConfig().getString("prefix");

                if(player.hasPermission("*")) {
                    player.getWorld().setSpawnLocation(player.getLocation());
                    plugin.getConfig().set("spawn.loc", player.getLocation());
                    plugin.saveConfig();
                    player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_SETSPAWN",null)));
                } else {
                    player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(1)));
                }
            }

        }

        return true;
    }
}
