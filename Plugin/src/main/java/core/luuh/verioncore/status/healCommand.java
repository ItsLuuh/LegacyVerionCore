package core.luuh.verioncore.status;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import core.luuh.verioncore.VerionCore;

public class healCommand implements CommandExecutor {

    private final VerionCore plugin;

    public healCommand(VerionCore plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if(player.hasPermission("*")){

                player.setHealth(player.getMaxHealth());

            }

        }

        return true;
    }
}
