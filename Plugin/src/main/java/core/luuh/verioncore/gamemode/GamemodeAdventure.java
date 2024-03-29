package core.luuh.verioncore.gamemode;

import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.GeneralUtils;

public class GamemodeAdventure implements CommandExecutor {

    private final VerionCore plugin;

    public GamemodeAdventure(VerionCore plugin) {this.plugin = plugin;}
    private static GeneralUtils settings = GeneralUtils.getInstance();
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label,  String[] args) {

        if(sender instanceof Player) {

            String prefix = plugin.getConfig().getString("prefix");
            Player player = (Player) sender;

            if (command.getName().equalsIgnoreCase("gma")) {
                if (player.hasPermission("*")) {
                    if (args.length > 0) {
                        Player target = plugin.getServer().getPlayer(args[0]);
                        if (target != null) {
                            target.setGameMode(GameMode.ADVENTURE);
                            player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_STEC_GM_A", target)));
                            target.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_STN_GM_A", player)));
                        } else {
                            player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(3)));
                        }
                    } else {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_GM_A", player)));
                    }

                }  else {
                    player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(1)));
                }
            }
        } else {
            System.out.println("Non puoi farlo da console");
        }

        return true;
    }
}
