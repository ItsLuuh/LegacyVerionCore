package core.luuh.verioncore.warp;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetWarpCommand implements CommandExecutor, TabCompleter {

    private final VerionCore plugin;

    public SetWarpCommand(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settings = GeneralUtils.getInstance();

    public boolean getSpecialChars(String text) {
        Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecial = special.matcher(text);
        return hasSpecial.find();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player) {

            if (command.getName().equalsIgnoreCase("setwarp")) {

                Player player = (Player) commandSender;
                //setwarp (Warp)

                if (player.hasPermission("*")) {
                    if (!(strings.length > 0)) {
                        player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(2)));
                    } else {
                        if (getSpecialChars(strings[0]) && !(strings[0].equalsIgnoreCase("list"))) {
                            player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(4)));
                        } else {
                            plugin.getConfig().set("warps." + strings[0], player.getLocation());

                            List<String> warps = new ArrayList<>();
                            for (String st : plugin.getConfig().getStringList("warps.list")) {
                                warps.add(st);
                            }

                            warps.add(strings[0]);
                            plugin.getConfig().set("warps.list", warps);
                            plugin.saveConfig();
                            player.sendMessage(chatcolor.chat(settings.getFromConfigU("MSG_WARP_SET", player, strings[0])));
                        }
                    }
                } else {
                    player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(1)));
                }
            } else if(command.getName().equalsIgnoreCase("delwarp")) {

                Player player = (Player) commandSender;
                //delwarp (Warp)

                if (player.hasPermission("*")) {
                    if (!(strings.length > 0)) {
                        player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(2)));
                    } else {
                        if (getSpecialChars(strings[0]) && !(strings[0].equalsIgnoreCase("list"))) {
                            player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(4)));
                        } else {
                            plugin.getConfig().set("warps." + strings[0], null);

                            List<String> warps = plugin.getConfig().getStringList("warps.list");
                            warps.remove(strings[0]);
                            plugin.getConfig().set("warps.list", warps);
                            plugin.saveConfig();
                            player.sendMessage(chatcolor.chat(settings.getFromConfigU("MSG_WARP_DEL", player, strings[0])));
                        }
                    }
                } else {
                    player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(1)));
                }

            }
        }

        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(command.getName().equalsIgnoreCase("delwarp") && args.length==1){

            List<String> arguments = new ArrayList<>();
            for(String s : plugin.getConfig().getStringList("warps.list")){
                arguments.add(s);
            }
            arguments.remove("list");
            return arguments;

        }

        return null;
    }
}
