package core.luuh.verioncore.gamemode;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.GeneralUtils;
import core.luuh.verioncore.utils.chatcolor;

public class GamemodeCreative implements CommandExecutor {

    private final VerionCore plugin;

    public GamemodeCreative(VerionCore plugin) {this.plugin = plugin;}
    private static GeneralUtils settings = GeneralUtils.getInstance();
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label,  String[] args) {

        if(sender instanceof Player) {

            String prefix = plugin.getConfig().getString("prefix");
            Player player = (Player) sender;

            if (command.getName().equalsIgnoreCase("gmc")) {
                if(player.hasPermission("*")) {
                    if (args.length > 0) {
                        Player target = plugin.getServer().getPlayer(args[0]);
                        if (target != null) {
                            target.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_STEC_GM_C", target)));
                            target.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_STN_GM_C", player)));
                        } else {
                            player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(3)));
                        }
                    } else {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_GM_C", player)));
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
