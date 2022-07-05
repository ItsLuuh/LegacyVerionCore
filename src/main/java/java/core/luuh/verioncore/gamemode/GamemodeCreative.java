package java.core.luuh.verioncore.gamemode;

import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GamemodeCreative implements CommandExecutor {

    private final VerionCore plugin;

    public GamemodeCreative(VerionCore plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player) {

            String prefix = plugin.getConfig().getString("prefix");
            Player player = (Player) sender;

            if (command.getName().equalsIgnoreCase("gmc")) {
                if(player.hasPermission("*")) {
                    if (args.length > 0) {
                        Player target = plugin.getServer().getPlayer(args[0]);
                        if (target != null) {
                            target.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(chatcolor.chat(prefix + "Hai impostato la gamemode di " + target.getDisplayName() + " a &eCREATIVE&f."));
                            target.sendMessage(chatcolor.chat(prefix + "La tua gamemode è stata impostata a &eCREATIVE&f."));
                        } else {
                            player.sendMessage(chatcolor.chat(prefix + "Devi impostare un nome valido."));
                        }
                    } else {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(chatcolor.chat(prefix + "Hai impostato la tua gamemode a &eCREATIVE&f."));
                    }
                } else {
                    player.sendMessage(chatcolor.chat(prefix + "Non hai il permesso per eseguire questo comando."));
                }
            }
        } else {
            System.out.println("Non puoi farlo da console");
        }

        return true;
    }
}
