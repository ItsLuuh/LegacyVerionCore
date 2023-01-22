package core.luuh.verioncore.gamemode;

import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.GeneralUtils;

public class GamemodeSpectator implements CommandExecutor {

    private final VerionCore plugin;

    public GamemodeSpectator(VerionCore plugin) {this.plugin = plugin;}
    private static GeneralUtils settings = GeneralUtils.getInstance();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player) {

            String prefix = plugin.getConfig().getString("prefix");
            Player player = (Player) sender;

            if (command.getName().equalsIgnoreCase("gmsp")) {
                if(player.hasPermission("*")) {
                    if (args.length > 0) {
                        Player target = plugin.getServer().getPlayer(args[0]);
                        if (target != null) {
                            target.setGameMode(GameMode.SPECTATOR);
                            player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_STEC_GM_SP", target)));
                            target.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_STN_GM_SP", player)));
                        } else {
                            player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(3)));
                        }
                    } else {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_GM_SP",player)));
                    }
                } else {
                    player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(1)));
                }
            }
        } else {
            System.out.println("Non puoi farlo da console");
        }

        return true;
    }
}


