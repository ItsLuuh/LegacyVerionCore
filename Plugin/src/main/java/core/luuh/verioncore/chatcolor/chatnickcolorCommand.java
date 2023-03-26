package core.luuh.verioncore.chatcolor;

import core.luuh.verioncore.VerionAPIManager;
import core.luuh.verioncore.VerionCore;
import core.luuh.verioncore.utils.GeneralUtils;
import core.luuh.verioncore.utils.SettingsManager;
import core.luuh.verioncore.utils.chatcolor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


import java.util.UUID;

public class chatnickcolorCommand implements CommandExecutor {

    private final VerionCore plugin;

    public chatnickcolorCommand(VerionCore plugin) {this.plugin = plugin;}

    private final SettingsManager settings = SettingsManager.getInstance();

    @Override
    public boolean onCommand( CommandSender commandSender,  Command command,  String s,  String[] strings) {

        if(commandSender instanceof Player player){

            if(command.getName().equalsIgnoreCase("chatcolor")){

                guicreation(player,0);

            } else if(command.getName().equalsIgnoreCase("nickcolor")){

                guicreation(player,1);

            }



        }

        return true;
    }

    public void guicreation(Player player, Integer tipo){

        Inventory gui = null;

        if(tipo==0) {
            gui = VerionAPIManager.createChestGUI(player, 6, plugin.getConfig().getString("guititles.chatcolor"));

        } else if(tipo==1) {
            gui = VerionAPIManager.createChestGUI(player, 6, plugin.getConfig().getString("guititles.nickcolor"));
        }

        ItemStack si = VerionAPIManager.createStartItem(Material.WHITE_WOOL, 1);
        VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&f"+player.getName())));
        ItemStack white = VerionAPIManager.createFinalItem(si);

        VerionAPIManager.setItemMaterial(si, Material.ORANGE_WOOL);
        VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&6"+player.getName())));
        ItemStack orange = VerionAPIManager.createFinalItem(si);

        VerionAPIManager.setItemMaterial(si, Material.RED_TERRACOTTA);
        VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&c"+player.getName())));
        ItemStack light_red = VerionAPIManager.createFinalItem(si);

        VerionAPIManager.setItemMaterial(si, Material.LIGHT_BLUE_WOOL);
        VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&b"+player.getName())));
        ItemStack light_blue = VerionAPIManager.createFinalItem(si);

        VerionAPIManager.setItemMaterial(si, Material.YELLOW_WOOL);
        VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&e"+player.getName())));
        ItemStack yellow = VerionAPIManager.createFinalItem(si);

        VerionAPIManager.setItemMaterial(si, Material.LIME_WOOL);
        VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&a"+player.getName())));
        ItemStack lime = VerionAPIManager.createFinalItem(si);

        VerionAPIManager.setItemMaterial(si, Material.PINK_WOOL);
        VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&d"+player.getName())));
        ItemStack pink = VerionAPIManager.createFinalItem(si);

        VerionAPIManager.setItemMaterial(si, Material.GRAY_WOOL);
        VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&8"+player.getName())));
        ItemStack gray = VerionAPIManager.createFinalItem(si);

        VerionAPIManager.setItemMaterial(si, Material.LIGHT_GRAY_WOOL);
        VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&7"+player.getName())));
        ItemStack light_gray = VerionAPIManager.createFinalItem(si);

        VerionAPIManager.setItemMaterial(si, Material.PURPLE_WOOL);
        VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&5"+player.getName())));
        ItemStack purple = VerionAPIManager.createFinalItem(si);

        VerionAPIManager.setItemMaterial(si, Material.CYAN_WOOL);
        VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&3"+player.getName())));
        ItemStack cyan = VerionAPIManager.createFinalItem(si);

        VerionAPIManager.setItemMaterial(si, Material.BLUE_WOOL);
        VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&1"+player.getName())));
        ItemStack blue = VerionAPIManager.createFinalItem(si);

        VerionAPIManager.setItemMaterial(si, Material.LIGHT_BLUE_TERRACOTTA);
        VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&9"+player.getName())));
        ItemStack mid_blue = VerionAPIManager.createFinalItem(si);

        VerionAPIManager.setItemMaterial(si, Material.GREEN_WOOL);
        VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&2"+player.getName())));
        ItemStack green = VerionAPIManager.createFinalItem(si);

        VerionAPIManager.setItemMaterial(si, Material.RED_WOOL);
        VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&4"+player.getName())));
        ItemStack red = VerionAPIManager.createFinalItem(si);

        VerionAPIManager.setItemMaterial(si, Material.BLACK_WOOL);
        VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&0"+player.getName())));
        ItemStack black = VerionAPIManager.createFinalItem(si);

        VerionAPIManager.fullPanes("6", false, gui, Material.BLACK_STAINED_GLASS_PANE);

        if(tipo == 0){

            VerionAPIManager.setItemMaterial(si, Material.IRON_BLOCK);
            VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&f&l"+player.getName())));
            ItemStack bold = VerionAPIManager.createFinalItem(si);

            VerionAPIManager.setItemMaterial(si, Material.CALCITE);
            VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&f&o"+player.getName())));
            ItemStack italic = VerionAPIManager.createFinalItem(si);

            VerionAPIManager.setItemMaterial(si, Material.POLISHED_DIORITE);
            VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&f&n"+player.getName())));
            ItemStack underlined = VerionAPIManager.createFinalItem(si);

            VerionAPIManager.setItemMaterial(si, Material.DIORITE);
            VerionAPIManager.setItemName(si, chatcolor.chat(chatcolor.hex("&f&m"+player.getName())));
            ItemStack strikethrough = VerionAPIManager.createFinalItem(si);

            UUID puid = player.getUniqueId();
            if(settings.getData().getBoolean(puid+".chatbold")){ // true
                VerionAPIManager.setEnchanted(bold, true);
            } else if(!settings.getData().getBoolean(puid + ".chatbold")){ // false
                VerionAPIManager.setEnchanted(bold, false);
            }
            if(settings.getData().getBoolean(puid+".chatitalic")){
                VerionAPIManager.setEnchanted(italic, true);
            } else if(!settings.getData().getBoolean(puid + ".chatitalic")){
                VerionAPIManager.setEnchanted(italic, false);
            }
            if(settings.getData().getBoolean(puid+".chatunderlined")){
                VerionAPIManager.setEnchanted(underlined, true);
            } else if(!settings.getData().getBoolean(puid + ".chatunderlined")){
                VerionAPIManager.setEnchanted(underlined, false);
            }
            if(settings.getData().getBoolean(puid+".chatstrikethrough")){
                VerionAPIManager.setEnchanted(strikethrough, true);
            } else if(!settings.getData().getBoolean(puid + ".chatstrikethrough")){
                VerionAPIManager.setEnchanted(strikethrough, false);
            }

            gui.setItem(39, underlined);
            gui.setItem(40, italic);
            gui.setItem(41, strikethrough);
            gui.setItem(31, bold);

        } else if(tipo==1) {

        }

        gui.setItem(10, white);
        gui.setItem(11, orange);
        gui.setItem(12, light_red);
        gui.setItem(13, light_blue);
        gui.setItem(14, yellow);
        gui.setItem(15, lime);
        gui.setItem(16, pink);
        gui.setItem(19, gray);
        gui.setItem(20, light_gray);
        gui.setItem(21, purple);
        gui.setItem(22, cyan);
        gui.setItem(23, blue);
        gui.setItem(24, mid_blue);
        gui.setItem(25, green);
        gui.setItem(30, red);
        gui.setItem(32, black);

        player.openInventory(gui);

    }
}
