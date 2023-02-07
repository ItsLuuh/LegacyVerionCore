package core.luuh.verioncore.fly;

import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.GeneralUtils;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FlyCommand implements CommandExecutor, TabCompleter {

    private final VerionCore plugin;

    public FlyCommand(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settings = GeneralUtils.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player) {

            String prefix = plugin.getConfig().getString("prefix");
            Player player = (Player) sender;

            if (command.getName().equalsIgnoreCase("fly")) {
                if(player.hasPermission("*")) {
                    if (!(args.length > 0)) {
                        if (player.getAllowFlight()) {
                            player.setAllowFlight(false);
                            player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_FLY_OFF", null)));
                        } else {
                            player.setAllowFlight(true);
                            player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_FLY_ON", null)));
                        }
                    } else {
                        Player target = plugin.getServer().getPlayer(args[0]);
                        if (target != null) {
                            if (target.getAllowFlight()) {
                                target.setAllowFlight(false);
                                player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_STEC_FLY_OFF", target)));
                                target.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_STN_FLY_OFF", player)));
                            } else {
                                target.setAllowFlight(true);
                                player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_STEC_FLY_ON", target)));
                                target.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_STN_FLY_ON", player)));


                            }
                        } else {
                            if (args[0].equalsIgnoreCase("on")) {
                                player.setAllowFlight(true);
                                player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_FLY_ON", null)));
                            } else if (args[0].equalsIgnoreCase("off")) {
                                player.setAllowFlight(false);
                                player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_FLY_OFF", null)));
                            }
                        }
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

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length == 1){
            List<String> arguments = new ArrayList<>();
            arguments.add("on");arguments.add("off");
            for( Player player : plugin.getServer().getOnlinePlayers()) {
                arguments.add(player.getDisplayName());
            }
            return arguments;

        } else if (args.length == 2){

            List<String> arguments = new ArrayList<>();
            arguments.add("on");arguments.add("off");
            return arguments;

        }

        return null;
    }
}
