package core.luuh.verioncore.gamemode;

import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.GeneralUtils;
import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Gamemode implements CommandExecutor {

    private final VerionCore plugin;

    public Gamemode(VerionCore plugin) {this.plugin = plugin;}
    private static GeneralUtils settings = GeneralUtils.getInstance();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player) {

            String prefix = plugin.getConfig().getString("prefix");
            Player player = (Player) sender;

            //gamemode args[0] args[1]
            //gamemode creative ItsLuuh

            if (command.getName().equalsIgnoreCase("gamemode")) {
                if (player.hasPermission("*")) {
                    if (args.length > 0) {
                        if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("1")) {
                            if (args.length > 1) {
                                Player target = plugin.getServer().getPlayer(args[1]);
                                if (target != null) {
                                    target.setGameMode(GameMode.CREATIVE);
                                    player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_STEC_GM_C", target)));
                                    target.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_STN_GM_C", player)));
                                } else {
                                    player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(3)));
                                }
                            } else {
                                player.setGameMode(GameMode.CREATIVE);
                                player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_GM_C", null)));
                            }
                        } else if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("0")) {
                            if (args.length > 1) {
                                Player target = plugin.getServer().getPlayer(args[1]);
                                if (target != null) {
                                    target.setGameMode(GameMode.SURVIVAL);
                                    player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_STEC_GM_S", target)));
                                    target.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_STN_GM_S", player)));
                                } else {
                                    player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(3)));
                                }
                            } else {
                                player.setGameMode(GameMode.SURVIVAL);
                                player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_GM_S", null)));
                            }
                        } else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("3")) {
                            if (args.length > 1) {
                                Player target = plugin.getServer().getPlayer(args[1]);
                                if (target != null) {
                                    target.setGameMode(GameMode.SPECTATOR);
                                    player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_STEC_GM_SP", target)));
                                    target.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_STN_GM_SP", player)));
                                } else {
                                    player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(3)));
                                }
                            } else {
                                player.setGameMode(GameMode.SPECTATOR);
                                player.sendMessage(chatcolor.chat(settings.getFromConfig("MSG_GM_SP",null)));
                            }
                        } else if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("4")) {
                            if (args.length > 1) {
                                Player target = plugin.getServer().getPlayer(args[1]);
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
                        } else {
                            player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(4)));
                        }
                    } else {
                        player.sendMessage(chatcolor.chat(settings.getCaseFromConfig(2)));
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
