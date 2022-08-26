package core.luuh.verioncore.status;

import core.luuh.verioncore.VerionCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class feedCommand implements CommandExecutor {

    private final VerionCore plugin;

    public feedCommand(VerionCore plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if(player.hasPermission("*")){

                player.setFoodLevel(20);

            }

        }

        return true;
    }
}
