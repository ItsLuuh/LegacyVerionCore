package core.luuh.verioncore;

import core.luuh.verioncore.fly.FlyCommand;
import core.luuh.verioncore.gamemode.*;
import core.luuh.verioncore.join.JoinMessageEvent;
import core.luuh.verioncore.join.QuitMessageEvent;
import core.luuh.verioncore.spawn.SetSpawn;
import core.luuh.verioncore.spawn.SpawnCommand;
import core.luuh.verioncore.utils.ChatMessageEvent;
import core.luuh.verioncore.utils.NoDamageForever;
import core.luuh.verioncore.utils.NoRainForever;
import core.luuh.verioncore.utils.VoidTP;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class VerionCore extends JavaPlugin {

    final String versionplugin = this.getDescription().getVersion();
    public void logConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
    private static Chat chat = null;

    public static Chat getChat() {
        return chat;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    public void registerDefaults(){
        getConfig().addDefault("prefix", "&6[&e!&6] &lVERION &8» &f");
        getConfig().addDefault("joins", 0);
        getConfig().addDefault("joinmessage", "%prefix% Benvenuto &e%player%&f su &6&lVERIONMC&f! &8(&e#%joins%&8)");
        getConfig().addDefault("spawn.join-tp-spawn", true);
        getConfig().addDefault("chat", "%prefix% &f%player% &8» &f%message%");
    }

    public void registerEvents(){

        Bukkit.getPluginManager().registerEvents(new JoinMessageEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new QuitMessageEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new VoidTP(this), this);
        Bukkit.getPluginManager().registerEvents(new NoRainForever(this), this);
        Bukkit.getPluginManager().registerEvents(new NoDamageForever(this), this);
        Bukkit.getPluginManager().registerEvents(new ChatMessageEvent(this), this);

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
    }

    public void registerAll(){

        registerEvents();
        registerCommands();
        registerDefaults();
        setupChat();

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

}

