package core.luuh.verioncore.utils;

import core.luuh.verioncore.VerionCore;
import org.bukkit.entity.Player;

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

    public boolean getBooleanFromConfig(String s){
        return plugin.getConfig().getBoolean(s);
    }

}
