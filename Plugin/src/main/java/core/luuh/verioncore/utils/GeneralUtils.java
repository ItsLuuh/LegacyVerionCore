package core.luuh.verioncore.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import core.luuh.verioncore.VerionCore;

import java.util.UUID;


public class GeneralUtils {

    static GeneralUtils instance;

    public static GeneralUtils getInstance() {
        if (instance == null) instance = new GeneralUtils(VerionCore.getInstance());
        return instance;
    }

    private final VerionCore plugin;

    public GeneralUtils(VerionCore plugin) {this.plugin = plugin;}

    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isPositive(String str){
        int num = Integer.parseInt(str);
        return num > 0;
    }

    public String getCaseFromConfig(int i){
        switch (i) {
            case 0:
                return plugin.getConfig().getString("prefix");
            case 1:
                return plugin.getConfig().getString("NO_PERMS").replaceAll("%prefix%", plugin.getConfig().getString("prefix"));
            case 2:
                return plugin.getConfig().getString("NO_ARGS").replaceAll("%prefix%", plugin.getConfig().getString("prefix"));
            case 3:
                return plugin.getConfig().getString("INVALID_PLAYER").replaceAll("%prefix%", plugin.getConfig().getString("prefix"));
            case 4:
                return plugin.getConfig().getString("INVALID_ARGS").replaceAll("%prefix%", plugin.getConfig().getString("prefix"));
            case 5:
                return plugin.getConfig().getString("PLUGIN_RELOADED").replaceAll("%prefix%", plugin.getConfig().getString("prefix"));
        }
        return null;
    }

    public String getFromConfigS(String s){
        return plugin.getConfig().getString(s);
    }

    public String getFromConfig(String s, Player player){
        if (player != null) {
            return plugin.getConfig().getString(s).replaceAll("%prefix%", getCaseFromConfig(0)).replaceAll("%player%", player.getDisplayName());
        } else {
            return plugin.getConfig().getString(s).replaceAll("%prefix%", getCaseFromConfig(0));
        }
    }

    public String getFromConfigU(String s, Player player, String arg){
        if (player != null) {
            return plugin.getConfig().getString(s).replaceAll("%prefix%", getCaseFromConfig(0)).replaceAll("%arg%", arg).replaceAll("%player%", player.getDisplayName());
        } else {
            return plugin.getConfig().getString(s).replaceAll("%prefix%", getCaseFromConfig(0)).replaceAll("%arg%", arg);
        }
    }

    private static final SettingsManager settingsManager = SettingsManager.getInstance();

    public boolean getBooleanFromConfig(String s){
        return plugin.getConfig().getBoolean(s);
    }

    public void setChatColor(ChatColor color, Player player){

        UUID puid = player.getUniqueId();
        settingsManager.getData().set(puid+".chatcolor", color.getChar());
        settingsManager.saveData();

        player.sendMessage(chatcolor.chat(chatcolor.hex(getFromConfigU("MSG_CC_SET", player, color.toString().toUpperCase()+getFromConfigS("MSG_CNC_COLOR")))));

    }

    public void setNickColor(ChatColor color, Player player){
        UUID puid = player.getUniqueId();
        settingsManager.getData().set(puid+".nickcolor", color.getChar());
        settingsManager.saveData();

        player.sendMessage(chatcolor.chat(chatcolor.hex(getFromConfigU("MSG_NC_SET", player, color.toString().toUpperCase()+getFromConfigS("MSG_CNC_COLOR")))));
    }

    public static void setChatSpecial(String special, Player player){

        UUID puid = player.getUniqueId();
        String placeholder = null;
        if(special.equalsIgnoreCase("strikethrough"))placeholder = "chatstrikethrough";
        if(special.equalsIgnoreCase("underlined"))placeholder = "chatunderlined";
        if(special.equalsIgnoreCase("bold"))placeholder = "chatbold";
        if(special.equalsIgnoreCase("italic"))placeholder = "chatitalic";

        if(settingsManager.getData().getBoolean(puid+"."+placeholder)){ //if placeholder true
            settingsManager.getData().set(puid+"."+placeholder, false);
        } else if(!settingsManager.getData().getBoolean(puid + "."+placeholder)){ //if placeholder false
            settingsManager.getData().set(puid+"."+placeholder, true);
        }
        settingsManager.saveData();

    }

    public static void setBChatSpecial(String special, Player player, Boolean bool){

        UUID puid = player.getUniqueId();
        String placeholder = null;
        if(special.equalsIgnoreCase("strikethrough"))placeholder = "chatstrikethrough";
        if(special.equalsIgnoreCase("underlined"))placeholder = "chatunderlined";
        if(special.equalsIgnoreCase("bold"))placeholder = "chatbold";
        if(special.equalsIgnoreCase("italic"))placeholder = "chatitalic";

        settingsManager.getData().set(puid+"."+placeholder, bool);
        settingsManager.saveData();

    }


}
