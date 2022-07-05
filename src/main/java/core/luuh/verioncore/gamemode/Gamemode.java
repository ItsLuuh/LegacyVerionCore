package core.luuh.verioncore.gamemode;

import core.luuh.verioncore.VerionCore;
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
                                    player.sendMessage(chatcolor.chat(prefix + "Hai impostato la gamemode di " + target.getDisplayName() + " a &eCREATIVE&f."));
                                    target.sendMessage(chatcolor.chat(prefix + "La tua gamemode è stata impostata a &eCREATIVE&f."));
                                } else {
                                    player.sendMessage(chatcolor.chat(prefix + "Devi impostare un nome valido."));
                                }
                            } else {
                                player.setGameMode(GameMode.CREATIVE);
                                player.sendMessage(chatcolor.chat(prefix + "Hai impostato la tua gamemode a &eCREATIVE&f."));
                            }
                        } else if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("0")) {
                            if (args.length > 1) {
                                Player target = plugin.getServer().getPlayer(args[1]);
                                if (target != null) {
                                    target.setGameMode(GameMode.SURVIVAL);
                                    player.sendMessage(chatcolor.chat(prefix + "Hai impostato la gamemode di " + target.getDisplayName() + " a &eSURVIVAL&f."));
                                    target.sendMessage(chatcolor.chat(prefix + "La tua gamemode è stata impostata a &eSURVIVAL&f."));
                                } else {
                                    player.sendMessage(chatcolor.chat(prefix + "Devi impostare un nome valido."));
                                }
                            } else {
                                player.setGameMode(GameMode.SURVIVAL);
                                player.sendMessage(chatcolor.chat(prefix + "Hai impostato la tua gamemode a &eSURVIVAL&f."));
                            }
                        } else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("3")) {
                            if (args.length > 1) {
                                Player target = plugin.getServer().getPlayer(args[1]);
                                if (target != null) {
                                    target.setGameMode(GameMode.SPECTATOR);
                                    player.sendMessage(chatcolor.chat(prefix + "Hai impostato la gamemode di " + target.getDisplayName() + " a &eSPETTATORE&f."));
                                    target.sendMessage(chatcolor.chat(prefix + "La tua gamemode è stata impostata a &eSPETTATORE&f."));
                                } else {
                                    player.sendMessage(chatcolor.chat(prefix + "Devi impostare un nome valido."));
                                }
                            } else {
                                player.setGameMode(GameMode.SPECTATOR);
                                player.sendMessage(chatcolor.chat(prefix + "Hai impostato la tua gamemode a &eSPETTATORE&f."));
                            }
                        } else if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("4")) {
                            if (args.length > 1) {
                                Player target = plugin.getServer().getPlayer(args[1]);
                                if (target != null) {
                                    target.setGameMode(GameMode.ADVENTURE);
                                    player.sendMessage(chatcolor.chat(prefix + "Hai impostato la gamemode di " + target.getDisplayName() + " a &eAVVENTURA&f."));
                                    target.sendMessage(chatcolor.chat(prefix + "La tua gamemode è stata impostata a &eAVVENTURA&f."));
                                } else {
                                    player.sendMessage(chatcolor.chat(prefix + "Devi impostare un nome valido."));
                                }
                            } else {
                                player.setGameMode(GameMode.ADVENTURE);
                                player.sendMessage(chatcolor.chat(prefix + "Hai impostato la tua gamemode a &eAVVENTURA&f."));
                            }
                        } else {
                            player.sendMessage(chatcolor.chat(prefix + "Inserisci una valida modalità di gioco."));
                        }
                    } else {
                        player.sendMessage(chatcolor.chat(prefix + "Inserisci una modalità di gioco."));
                    }
                }  else {
                    player.sendMessage(chatcolor.chat(prefix + "Non hai il permesso per eseguire questo comando."));
                }
            }

        } else {
            System.out.println("Non puoi farlo da console");
        }

        return true;
    }
}
