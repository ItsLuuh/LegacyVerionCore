package java.core.luuh.verioncore;

import core.luuh.verioncore.fly.FlyCommand;
import core.luuh.verioncore.gamemode.*;
import core.luuh.verioncore.join.JoinMessageEvent;
import core.luuh.verioncore.join.QuitMessageEvent;
import core.luuh.verioncore.spawn.SetSpawn;
import core.luuh.verioncore.spawn.SpawnCommand;
import core.luuh.verioncore.staff.StaffChat;
import core.luuh.verioncore.staff.StaffMode;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class VerionCore extends JavaPlugin {

    private NMS nmsHandler;

    final String versionplugin = this.getDescription().getVersion();
    public void logConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }

    public void registerDefaults(){
        getConfig().addDefault("prefix", "&6[&e!&6] &lVERION &8» &f");
        getConfig().addDefault("joins", 1);
        getConfig().addDefault("joinmessage", "%prefix% Benvenuto &e%player%&f sul &6&lPRISON&f! &8(&e#%joins%&8)");
        getConfig().addDefault("spawn.join-tp-spawn", true);
    }

    public void registerEvents(){

        Bukkit.getPluginManager().registerEvents(new JoinMessageEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new QuitMessageEvent(this), this);

    }

    public void registerCommands(){

        getCommand("core").setExecutor(new CoreCommand(this));
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("setspawn").setExecutor(new SetSpawn(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("gamemode").setExecutor(new Gamemode(this));
        getCommand("gmc").setExecutor(new GamemodeCreative(this));
        getCommand("gms").setExecutor(new GamemodeSurvival(this));
        getCommand("gma").setExecutor(new GamemodeAdventure(this));
        getCommand("gmsp").setExecutor(new GamemodeSpectator(this));
        getCommand("staffchat").setExecutor(new StaffChat(this));
        getCommand("staffmode").setExecutor(new StaffMode(this));

    }

    public void registerNMS() {
        String packageName = this.getServer().getClass().getPackage().getName();
        // Get full package string of CraftServer.
        // org.bukkit.craftbukkit.version
        String version = packageName.substring(packageName.lastIndexOf('.') + 1);
        // Get the last element of the package

        try {
            final Class<?> clazz = Class.forName("core.luuh.verioncore.plugin.nms." + version + ".NMSHandler");
            // Check if we have a NMSHandler class at that location.
            if (NMS.class.isAssignableFrom(clazz)) { // Make sure it actually implements NMS
                this.nmsHandler = (NMS) clazz.getConstructor().newInstance(); // Set our handler
            }
        } catch (final Exception e) {
            e.printStackTrace();
            this.getLogger().severe("Could not find support for this CraftBukkit version.");
            this.getLogger().info("Check for updates at URL HERE");
            this.setEnabled(false);
            return;
        }
        this.getLogger().info("Loading support for " + version);
    }

    public void registerAll(){

        registerNMS();
        registerEvents();
        registerCommands();
        registerDefaults();

        getConfig().options().copyDefaults(true);
        saveConfig();
        saveDefaultConfig();

    }

    @Override
    public void onEnable() {
        registerAll();
        logConsole("&6[&e!&6] &lVERION &f" + versionplugin + "&8» &aPlugin abilitato!");

    }

    @Override
    public void onDisable() {
        logConsole("&6[&e!&6] &lVERION &f" + versionplugin + "&8» &cPlugin disabilitato!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            this.nmsHandler.sendMessage(player, this.combineSplit(args));
        }
        return true;
    }

    private String combineSplit(String[] split) {
        if (split.length == 0) {
            return "";
        } else if (split.length == 1) {
            return split[0];
        }
        StringBuilder builder = new StringBuilder();
        for (String s : split) {
            builder.append(s).append(' ');
        }
        builder.setLength(builder.length() - 1);
        return builder.toString();
    }

}

