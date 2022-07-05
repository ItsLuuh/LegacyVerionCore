package java.core.luuh.verioncore.spawn;

import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetSpawn implements CommandExecutor {

    private final VerionCore plugin;

    public SetSpawn(VerionCore plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player) {
            if(command.getName().equalsIgnoreCase("setspawn")) {

                Player player = (Player) sender;
                String prefix = plugin.getConfig().getString("prefix");

                if(player.hasPermission("*")) {
                    player.getWorld().setSpawnLocation(player.getLocation());
                    plugin.getConfig().set("spawn.loc", player.getLocation());
                    plugin.saveConfig();
                    player.sendMessage(chatcolor.chat(prefix + "Hai impostato la posizione dello &espawn&f."));
                } else {
                    player.sendMessage(chatcolor.chat(prefix+"Non hai il permesso per eseguire questo comando."));
                }
            }

        }

        return true;
    }
}
