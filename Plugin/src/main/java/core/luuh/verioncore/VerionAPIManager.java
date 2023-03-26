package core.luuh.verioncore;

import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static core.luuh.verioncore.VerionCore.getInstance;
import static core.luuh.verioncore.utils.chatcolor.chat;
import static core.luuh.verioncore.utils.chatcolor.hex;

 public interface VerionAPIManager {

     static Inventory createChestGUI(Player player, Integer size, String title){

        // Creating a GUI of chest type.

        Inventory gui = Bukkit.getServer().createInventory(player, size * 9, chat(hex(title)));

        return gui;

    }

     static void createDispenserGUI(Player player, String title){

        // Creating a GUI of dispenser type.

        Bukkit.getServer().createInventory(player, InventoryType.DISPENSER, chat(hex(title)));

    }

     static void createHopperGUI(Player player, String title){

        // Creating a GUI of Hopper type.

        Bukkit.getServer().createInventory(player, InventoryType.HOPPER, chat(hex(title)));

    }

     static ItemStack createStartItem(Material mat, Integer amount){

        // Settig the material of the Item.

        return new ItemStack(mat, amount);

    }

     static ItemStack createFinalItem(ItemStack itemStack){

        // Settig the material of the Item.

        return new ItemStack(itemStack);

    }

     static void setItemMaterial(ItemStack itemStack, Material material){

        // Settig the material of the Item.

        itemStack.setType(material);

    }

     static void setItemAmout(ItemStack itemStack, Integer amount){

        // Settig the material of the Item.

        itemStack.setAmount(amount);

    }

     static void setItemName(ItemStack itemStack, String name){

        // Setting the name of the item.

        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);

    }

     static void setLore(ItemStack itemStack, ArrayList lore){

        // Setting the lore of the item.

        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(lore);
        itemStack.setItemMeta(meta);

    }

     static void setCustomModelData(ItemStack itemStack, Integer custommodeldata){

        // Setting the custommodeldata of the item.

        ItemMeta meta = itemStack.getItemMeta();
        meta.setCustomModelData(custommodeldata);
        itemStack.setItemMeta(meta);

    }

     static void setEnchanted(ItemStack itemStack, Boolean enchanted){

        if(enchanted) {
            itemStack.addUnsafeEnchantment(Enchantment.LURE, 1);
            final ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            itemStack.setItemMeta(itemMeta);
        } else {
            itemStack.removeEnchantment(Enchantment.LURE);
            final ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
            itemStack.setItemMeta(itemMeta);
        }

    }

     static void cornerPanes(String righe, Inventory gui, Material pane) {
        ItemStack Pane = new ItemStack(pane, 1);
        ItemMeta Panemeta = Pane.getItemMeta();
        Panemeta.setDisplayName(chat("&f"));
        Pane.setItemMeta(Panemeta);
        ItemStack Panee = new ItemStack(Pane);

        gui.setItem(0, Panee);
        gui.setItem(1, Panee);
        gui.setItem(2, Panee);
        gui.setItem(3, Panee);
        gui.setItem(4, Panee);
        gui.setItem(5, Panee);
        gui.setItem(6, Panee);
        gui.setItem(7, Panee);
        gui.setItem(8, Panee);

        switch (righe) {

            case "3":
                gui.setItem(9, Panee);
                gui.setItem(17, Panee);

                gui.setItem(18, Panee);
                gui.setItem(19, Panee);
                gui.setItem(20, Panee);
                gui.setItem(21, Panee);
                gui.setItem(22, Panee);
                gui.setItem(23, Panee);
                gui.setItem(24, Panee);
                gui.setItem(25, Panee);
                gui.setItem(26, Panee);
                break;
            case "4":
                gui.setItem(9, Panee);
                gui.setItem(17, Panee);
                gui.setItem(18, Panee);
                gui.setItem(26, Panee);

                gui.setItem(27, Panee);
                gui.setItem(28, Panee);
                gui.setItem(29, Panee);
                gui.setItem(30, Panee);
                gui.setItem(31, Panee);
                gui.setItem(32, Panee);
                gui.setItem(33, Panee);
                gui.setItem(34, Panee);
                gui.setItem(35, Panee);
                break;
            case "5":
                gui.setItem(9, Panee);
                gui.setItem(17, Panee);
                gui.setItem(18, Panee);
                gui.setItem(26, Panee);
                gui.setItem(27, Panee);
                gui.setItem(35, Panee);

                gui.setItem(36, Panee);
                gui.setItem(37, Panee);
                gui.setItem(38, Panee);
                gui.setItem(39, Panee);
                gui.setItem(40, Panee);
                gui.setItem(41, Panee);
                gui.setItem(42, Panee);
                gui.setItem(43, Panee);
                gui.setItem(44, Panee);
                break;

            case "6":

                gui.setItem(9, Panee);
                gui.setItem(17, Panee);
                gui.setItem(18, Panee);
                gui.setItem(26, Panee);
                gui.setItem(27, Panee);
                gui.setItem(35, Panee);
                gui.setItem(36, Panee);
                gui.setItem(44, Panee);

                gui.setItem(45, Panee);
                gui.setItem(46, Panee);
                gui.setItem(47, Panee);
                gui.setItem(48, Panee);
                gui.setItem(49, Panee);
                gui.setItem(50, Panee);
                gui.setItem(51, Panee);
                gui.setItem(52, Panee);
                gui.setItem(53, Panee);
                break;

            case "dispenser":
                gui.setItem(0, Panee);
                gui.setItem(1, Panee);
                gui.setItem(2, Panee);
                gui.setItem(3, Panee);
                gui.setItem(5, Panee);
                gui.setItem(6, Panee);
                gui.setItem(7, Panee);
                gui.setItem(8, Panee);
                break;
        }


    }

     static void fullPanes(String righe, Boolean wait, Inventory gui, Material pane){

        ItemStack Pane = new ItemStack(pane, 1);
        ItemMeta Panemeta = Pane.getItemMeta();
        Panemeta.setDisplayName(chat("&f"));
        Pane.setItemMeta(Panemeta);
        ItemStack Panee = new ItemStack(Pane);

        int slot = 0;
        switch (righe){
            case "1":
                slot = 8;
                break;
            case "2":
                slot = 17;
                break;
            case "3":
                slot = 26;
                break;
            case "4":
                slot = 35;
                break;
            case "5":
                slot = 44;
                break;
            case "6":
                slot = 53;
                break;
            case "dispenser":
                slot = 8;
                break;
        }

        if(wait) {
            for (int i = 0; ; i++) {
                if (i > slot) {
                    break;
                } else {
                    int finalI = i;
                    Bukkit.getScheduler().runTaskLater(getInstance(), () -> gui.setItem(finalI, Panee), 20L);
                }
            }
        } else {
            for (int i = 0; ; i++) {
                if (i > slot) {
                    break;
                } else {
                    gui.setItem(i, Panee);
                }
            }
        }
    }

     static boolean titleGUICheck(InventoryClickEvent e, String title){



        if(e.getView().getTitle().equalsIgnoreCase(chat(hex(getInstance().getConfig().getString(title))))){
            return true;
        }

        return  false;

    }

     static void logConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(chatcolor.chat(chatcolor.hex(message)));
    }

     public static String chat(String message) {
         return ChatColor.translateAlternateColorCodes('&', message);
     }
     public static String hex(String message) {
         Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
         Matcher matcher = pattern.matcher(message);
         while (matcher.find()) {
             String hexCode = message.substring(matcher.start(), matcher.end());
             String replaceSharp = hexCode.replace('#', 'x');

             char[] ch = replaceSharp.toCharArray();
             StringBuilder builder = new StringBuilder("");
             for (char c : ch) {
                 builder.append("&" + c);
             }

             message = message.replace(hexCode, builder.toString());
             matcher = pattern.matcher(message);
         }
         return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message);
     }

}
