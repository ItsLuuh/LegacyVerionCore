package core.luuh.verioncore.speed;

import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.GeneralUtils;
import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetSpeedCommand implements CommandExecutor {

    private final VerionCore plugin;

    public SetSpeedCommand(VerionCore plugin) {this.plugin = plugin;}

    public void setSpeed(Player player, double speed, int i) {
        if (speed > 10)  {
            double b = speed - 10;
            speed -= b;
        }
        if (speed < 0) {
            speed -= speed;
        }
        double a = speed / 10;
        float value = (float) a;
        if(i == 0) {player.setFlySpeed(value);} else {player.setWalkSpeed(value);}
    }

    private static GeneralUtils settings = GeneralUtils.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            String prefix = plugin.getConfig().getString("prefix");
            if(player.hasPermission("*")) {
                if (strings.length == 1) {
                    if(GeneralUtils.isInt(strings[0]) && GeneralUtils.isPositive(strings[0])) {
                        if (player.isFlying()) {
                            setSpeed(player, Double.parseDouble(strings[0]), 0);
                            player.sendMessage(chatcolor.chat(settings.getFromConfigU("MSG_FLY_SPEED",null, strings[0])));
                        } else {
                            setSpeed(player, Double.parseDouble(strings[0]), 1);
                            player.sendMessage(chatcolor.chat(settings.getFromConfigU("MSG_WALK_SPEED",null, strings[0])));
                        }
                    }
                } else {
                    player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(2)));
                }
            } else {
                player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(1)));
            }

        }

        return true;
    }
}
