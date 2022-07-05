package core.luuh.verioncore.fly;

import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FlyCommand implements CommandExecutor {

    private final VerionCore plugin;

    public FlyCommand(VerionCore plugin) {this.plugin = plugin;}



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
                            player.sendMessage(chatcolor.chat(prefix + "Hai impostato la modalità volo a &cOFF&f."));
                        } else {
                            player.setAllowFlight(true);
                            player.sendMessage(chatcolor.chat(prefix + "Hai impostato la modalità volo a &aON&f."));
                        }
                    } else {
                        Player target = plugin.getServer().getPlayer(args[0]);
                        if (target != null) {
                            if (target.getAllowFlight()) {
                                target.setAllowFlight(false);
                                target.sendMessage(chatcolor.chat(prefix + "La tua modalità volo è stata impostata a &cOFF&f."));
                                player.sendMessage(chatcolor.chat(prefix + "Hai impostato la modalità volo di " + target.getDisplayName() + " a &cOFF&f."));
                            } else {
                                target.setAllowFlight(true);
                                target.sendMessage(chatcolor.chat(prefix + "La tua modalità volo è stata impostata a &aON."));
                                player.sendMessage(chatcolor.chat(prefix + "Hai impostato la modalità volo di " + target.getDisplayName() + " a &aON&f."));


                            }
                        } else {
                            if (args[0].equalsIgnoreCase("on")) {
                                player.setAllowFlight(true);
                                player.sendMessage(chatcolor.chat(prefix + "Hai impostato la modalità volo a &aON&f."));
                            } else if (args[0].equalsIgnoreCase("off")) {
                                player.setAllowFlight(false);
                                player.sendMessage(chatcolor.chat(prefix + "Hai impostato la modalità volo a &cOFF&f."));
                            }
                        }
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
