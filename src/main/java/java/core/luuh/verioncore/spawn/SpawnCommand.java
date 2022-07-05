package java.core.luuh.verioncore.spawn;

import core.luuh.verioncore.VerionCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {

    private final VerionCore plugin;

    public SpawnCommand(VerionCore plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){

            if(command.getName().equalsIgnoreCase("spawn")){

                Player player = (Player) sender;
                player.teleport(plugin.getConfig().getLocation("spawn.loc"));

            }

        }

        return true;
    }
}
