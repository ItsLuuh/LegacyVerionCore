package core.luuh.verioncore.status;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import core.luuh.verioncore.VerionCore;

public class healCommand implements CommandExecutor {

    private final VerionCore plugin;

    public healCommand(VerionCore plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand( CommandSender commandSender,  Command command,  String s,  String[] strings) {

        if(commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if(player.hasPermission("*")){

                player.setHealth(player.getMaxHealth());

            }

        }

        return true;
    }
}
