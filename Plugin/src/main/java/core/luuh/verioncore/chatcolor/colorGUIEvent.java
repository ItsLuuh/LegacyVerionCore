package core.luuh.verioncore.chatcolor;

import core.luuh.verioncore.VerionAPI;
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

import static core.luuh.verioncore.VerionAPI.*;

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
            VerionAPI.setEnchanted(item, false);
            player.sendMessage(chatcolor.chat(chatcolor.hex(settingsGeneral.getFromConfigU("MSG_CC_REMOVED_S", player, special.toUpperCase()))));
        } else if(!settingsManager.getData().getBoolean(puid + "."+placeholder)){ //if placeholder false
            settingsManager.getData().set(puid+"."+placeholder, true);
            settingsManager.saveData();
            VerionAPI.setEnchanted(item, true);
            player.sendMessage(chatcolor.chat(chatcolor.hex(settingsGeneral.getFromConfigU("MSG_CC_ADDED_S", player, special.toUpperCase()))));
        }

    }

    @EventHandler
    public void clickEvent(InventoryClickEvent e){

        if(VerionAPI.titleGUICheck(e, "guititles.chatcolor")){

            Player player = (Player) e.getWhoClicked();
            e.setCancelled(true);
            ItemStack itemStack = e.getCurrentItem();

            switch (e.getSlot()){

                case 10:
                    setChatColor(ChatColor.WHITE, player);
                    break;

                case 11:
                    setChatColor(ChatColor.GOLD, player);
                    break;
                case 12:
                    setChatColor(ChatColor.RED, player);
                    break;

                case 13:
                    setChatColor(ChatColor.AQUA, player);
                    break;
                case 14:
                    setChatColor(ChatColor.YELLOW, player);
                    break;

                case 15:
                    setChatColor(ChatColor.GREEN, player);
                    break;

                case 16:
                    setChatColor(ChatColor.LIGHT_PURPLE, player);
                    break;

                case 19:
                    setChatColor(ChatColor.DARK_GRAY, player);
                    break;

                case 20:
                    setChatColor(ChatColor.GRAY, player);
                    break;
                case 21:
                    setChatColor(ChatColor.DARK_PURPLE, player);
                    break;

                case 22:
                    setChatColor(ChatColor.DARK_AQUA, player);
                    break;
                case 23:
                    setChatColor(ChatColor.DARK_BLUE, player);
                    break;

                case 24:
                    setChatColor(ChatColor.BLUE, player);
                    break;

                case 25:
                    setChatColor(ChatColor.DARK_GREEN, player);
                    break;

                case 30:
                    setChatColor(ChatColor.DARK_RED, player);
                    break;

                case 32:
                    setChatColor(ChatColor.BLACK, player);
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
        else if (VerionAPI.titleGUICheck(e, "guititles.nickcolor")){

            Player player = (Player) e.getWhoClicked();
            e.setCancelled(true);

            switch (e.getSlot()){

                case 10:
                    setNickColor(ChatColor.WHITE, player);
                    break;

                case 11:
                    setNickColor(ChatColor.GOLD, player);
                    break;
                case 12:
                    setNickColor(ChatColor.RED, player);
                    break;

                case 13:
                    setNickColor(ChatColor.AQUA, player);
                    break;
                case 14:
                    setNickColor(ChatColor.YELLOW, player);
                    break;

                case 15:
                    setNickColor(ChatColor.GREEN, player);
                    break;

                case 16:
                    setNickColor(ChatColor.LIGHT_PURPLE, player);
                    break;

                case 19:
                    setNickColor(ChatColor.DARK_GRAY, player);
                    break;

                case 20:
                    setNickColor(ChatColor.GRAY, player);
                    break;
                case 21:
                    setNickColor(ChatColor.DARK_PURPLE, player);
                    break;

                case 22:
                    setNickColor(ChatColor.DARK_AQUA, player);
                    break;
                case 23:
                    setNickColor(ChatColor.DARK_BLUE, player);
                    break;

                case 24:
                    setNickColor(ChatColor.BLUE, player);
                    break;

                case 25:
                    setNickColor(ChatColor.DARK_GREEN, player);
                    break;

                case 30:
                    setNickColor(ChatColor.DARK_RED, player);
                    break;

                case 32:
                    setNickColor(ChatColor.BLACK, player);
                    break;

            }

        }

    }

}
