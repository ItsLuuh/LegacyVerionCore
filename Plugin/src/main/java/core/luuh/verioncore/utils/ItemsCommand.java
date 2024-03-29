package core.luuh.verioncore.utils;

import core.luuh.verioncore.VerionAPIManager;
import core.luuh.verioncore.VerionCore;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;


public class ItemsCommand implements Listener {

    private final VerionCore plugin;

    public ItemsCommand(VerionCore plugin) {this.plugin = plugin;}

    private static GeneralUtils settings = GeneralUtils.getInstance();

    @EventHandler
    public void ItemClick(PlayerInteractEvent e){

        if(plugin.getConfig().getBoolean("ItemsCommand.parkour")) {

            Player player = e.getPlayer();
             ItemStack item = e.getItem();
            String itemName;
            if (item != null && item.getType() == Material.LADDER && item.hasItemMeta()) {
                itemName = e.getItem().getItemMeta().getDisplayName();

                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (itemName.contains("Parkour")) {

                        player.performCommand("ajp start");

                    }
                }

            }
        }

    }

    @EventHandler
    public void ItemGive(PlayerJoinEvent e){

        if(plugin.getConfig().getBoolean("ItemsCommand.parkour")) {

            Player player = e.getPlayer();
            ItemStack itemStack = VerionAPIManager.createStartItem(Material.LADDER, 1);
            VerionAPIManager.setItemName(itemStack, chatcolor.chat(chatcolor.hex("&6» &eParkour &6«")));
            ItemStack finalItemStack = VerionAPIManager.createFinalItem(itemStack);
            player.getInventory().setItem(0, finalItemStack);
        }
    }
}
