package core.luuh.verioncore.spawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import core.luuh.verioncore.VerionCore;

public class SpawnCommand implements CommandExecutor {

    private final VerionCore plugin;

    public SpawnCommand(VerionCore plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label,  String[] args) {

        if(sender instanceof Player){

            if(command.getName().equalsIgnoreCase("spawn")){

                Player player = (Player) sender;
                player.teleport(plugin.getConfig().getLocation("spawn.loc"));

            }

        }

        return true;
    }
}
