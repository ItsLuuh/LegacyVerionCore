package core.luuh.verioncore.chatcolor;

import core.luuh.verioncore.VerionAPIManager;
import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.GeneralUtils;
import core.luuh.verioncore.utils.SettingsManager;
import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

import static core.luuh.verioncore.VerionAPIManager.*;

public class colorGUIEvent implements Listener {

    private final VerionCore plugin;

    public colorGUIEvent(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settingsGeneral = GeneralUtils.getInstance();
    private final SettingsManager settingsManager = SettingsManager.getInstance();

    public void edChatSpecial(String special, Player player, ItemStack item){

        UUID puid = player.getUniqueId();
        String placeholder = null;
        if(special.equalsIgnoreCase("strikethrough"))placeholder = "chatstrikethrough";
        if(special.equalsIgnoreCase("underlined"))placeholder = "chatunderlined";
        if(special.equalsIgnoreCase("bold"))placeholder = "chatbold";
        if(special.equalsIgnoreCase("italic"))placeholder = "chatitalic";

        if(settingsManager.getData().getBoolean(puid+"."+placeholder)){ //if placeholder true
            settingsManager.getData().set(puid+"."+placeholder, false);
            settingsManager.saveData();
            VerionAPIManager.setEnchanted(item, false);
            player.sendMessage(chatcolor.chat(chatcolor.hex(settingsGeneral.getFromConfigU("MSG_CC_REMOVED_S", player, special.toUpperCase()))));
        } else if(!settingsManager.getData().getBoolean(puid + "."+placeholder)){ //if placeholder false
            settingsManager.getData().set(puid+"."+placeholder, true);
            settingsManager.saveData();
            VerionAPIManager.setEnchanted(item, true);
            player.sendMessage(chatcolor.chat(chatcolor.hex(settingsGeneral.getFromConfigU("MSG_CC_ADDED_S", player, special.toUpperCase()))));
        }

    }

    @EventHandler
    public void clickEvent(InventoryClickEvent e){

        if(VerionAPIManager.titleGUICheck(e, "guititles.chatcolor")){

            Player player = (Player) e.getWhoClicked();
            e.setCancelled(true);
            ItemStack itemStack = e.getCurrentItem();

            switch (e.getSlot()){

                case 10:
                    settingsGeneral.setChatColor(ChatColor.WHITE, player);
                    break;

                case 11:
                    settingsGeneral.setChatColor(ChatColor.GOLD, player);
                    break;
                case 12:
                    settingsGeneral.setChatColor(ChatColor.RED, player);
                    break;

                case 13:
                    settingsGeneral.setChatColor(ChatColor.AQUA, player);
                    break;
                case 14:
                    settingsGeneral.setChatColor(ChatColor.YELLOW, player);
                    break;

                case 15:
                    settingsGeneral.setChatColor(ChatColor.GREEN, player);
                    break;

                case 16:
                    settingsGeneral.setChatColor(ChatColor.LIGHT_PURPLE, player);
                    break;

                case 19:
                    settingsGeneral.setChatColor(ChatColor.DARK_GRAY, player);
                    break;

                case 20:
                    settingsGeneral.setChatColor(ChatColor.GRAY, player);
                    break;
                case 21:
                    settingsGeneral.setChatColor(ChatColor.DARK_PURPLE, player);
                    break;

                case 22:
                    settingsGeneral.setChatColor(ChatColor.DARK_AQUA, player);
                    break;
                case 23:
                    settingsGeneral.setChatColor(ChatColor.DARK_BLUE, player);
                    break;

                case 24:
                    settingsGeneral.setChatColor(ChatColor.BLUE, player);
                    break;

                case 25:
                    settingsGeneral.setChatColor(ChatColor.DARK_GREEN, player);
                    break;

                case 30:
                    settingsGeneral.setChatColor(ChatColor.DARK_RED, player);
                    break;

                case 32:
                    settingsGeneral.setChatColor(ChatColor.BLACK, player);
                    break;

                case 31:
                    edChatSpecial("bold", player, itemStack);
                    break;

                case 39:
                    edChatSpecial("underlined", player, itemStack);
                    break;

                case 40:
                    edChatSpecial("italic", player, itemStack);
                    break;

                case 41:
                    edChatSpecial("strikethrough", player, itemStack);
                    break;

            }

        }
        else if (VerionAPIManager.titleGUICheck(e, "guititles.nickcolor")){

            Player player = (Player) e.getWhoClicked();
            e.setCancelled(true);

            switch (e.getSlot()){

                case 10:
                    settingsGeneral.setNickColor(ChatColor.WHITE, player);
                    break;

                case 11:
                    settingsGeneral.setNickColor(ChatColor.GOLD, player);
                    break;
                case 12:
                    settingsGeneral.setNickColor(ChatColor.RED, player);
                    break;

                case 13:
                    settingsGeneral.setNickColor(ChatColor.AQUA, player);
                    break;
                case 14:
                    settingsGeneral.setNickColor(ChatColor.YELLOW, player);
                    break;

                case 15:
                    settingsGeneral.setNickColor(ChatColor.GREEN, player);
                    break;

                case 16:
                    settingsGeneral.setNickColor(ChatColor.LIGHT_PURPLE, player);
                    break;

                case 19:
                    settingsGeneral.setNickColor(ChatColor.DARK_GRAY, player);
                    break;

                case 20:
                    settingsGeneral.setNickColor(ChatColor.GRAY, player);
                    break;
                case 21:
                    settingsGeneral.setNickColor(ChatColor.DARK_PURPLE, player);
                    break;

                case 22:
                    settingsGeneral.setNickColor(ChatColor.DARK_AQUA, player);
                    break;
                case 23:
                    settingsGeneral.setNickColor(ChatColor.DARK_BLUE, player);
                    break;

                case 24:
                    settingsGeneral.setNickColor(ChatColor.BLUE, player);
                    break;

                case 25:
                    settingsGeneral.setNickColor(ChatColor.DARK_GREEN, player);
                    break;

                case 30:
                    settingsGeneral.setNickColor(ChatColor.DARK_RED, player);
                    break;

                case 32:
                    settingsGeneral.setNickColor(ChatColor.BLACK, player);
                    break;

            }

        }

    }

}
