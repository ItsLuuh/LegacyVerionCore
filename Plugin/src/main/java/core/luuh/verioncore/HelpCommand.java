package core.luuh.verioncore;

import core.luuh.verioncore.utils.GeneralUtils;
import core.luuh.verioncore.utils.chatcolor;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HelpCommand implements CommandExecutor {

    private final VerionCore plugin;

    public HelpCommand(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settings = GeneralUtils.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(command.getName().equalsIgnoreCase("help")) {

            Player player = (Player) commandSender;
            List<String> messages = plugin.getConfig().getStringList("MSG_CORE_HELP");
            for (String message : messages) {
                player.sendMessage(chatcolor.chat(chatcolor.hex(PlaceholderAPI.setPlaceholders(player, message.replaceAll("%prefix%", plugin.getConfig().getString("prefix"))))));
            }

        }

        return true;
    }
}
