package core.luuh.verioncore;

import core.luuh.verioncore.fly.FlyCommand;
import core.luuh.verioncore.gamemode.*;
import core.luuh.verioncore.join.JoinMessageEvent;
import core.luuh.verioncore.join.QuitMessageEvent;
import core.luuh.verioncore.spawn.JoinTPSpawn;
import core.luuh.verioncore.spawn.SetSpawn;
import core.luuh.verioncore.spawn.SpawnCommand;
import core.luuh.verioncore.speed.SetSpeedCommand;
import core.luuh.verioncore.status.feedCommand;
import core.luuh.verioncore.status.healCommand;
import core.luuh.verioncore.utils.*;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class VerionCore extends JavaPlugin {

    static VerionCore instance;

    public static VerionCore getInstance() {
        return instance;
    }

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
        getConfig().addDefault("spawn.title.join-title", true);
        getConfig().addDefault("spawn.title.join-title-msg", "&6&lVERION&e&lMC");
        getConfig().addDefault("spawn.title.join-subtitle-msg", "&fBenvenuto!");
        getConfig().addDefault("spawn.title.fadeIn", 20);
        getConfig().addDefault("spawn.title.stay", 280);
        getConfig().addDefault("spawn.title.fadeOut", 20);
        getConfig().addDefault("chat", "%vault_prefix% &f%player_name% &8» &f%message%");

        getConfig().addDefault("VOIDTP", true);
        getConfig().addDefault("REMOVE_RAIN", true);
        getConfig().addDefault("REMOVE_DAMAGE", true);
        getConfig().addDefault("REMOVE_FOOD", true);

        getConfig().addDefault("MSG_CORE_HELP", "");

        getConfig().addDefault("QUIT_MSG", "&8[&c-&8] &7%player%");
        getConfig().addDefault("JOIN_MSG", "&8[&a+&8] &7%player%");

        getConfig().addDefault("NO_PERMS", "%prefix%Non hai abbastanza permessi per eseguire questo comando.");
        getConfig().addDefault("NO_ARGS", "%prefix%Devi inserire un argomento.");
        getConfig().addDefault("INVALID_PLAYER", "%prefix%Devi inserire un player valido.");
        getConfig().addDefault("INVALID_ARGS", "%prefix%Devi inserire un argomento valido.");
        getConfig().addDefault("PLUGIN_RELOADED", "%prefix%Tutti i config reloaddati con successo.");
        getConfig().addDefault("MSG_FLY_SPEED", "%prefix%Hai impostato la tua &evelocità di volo&f a &e%arg%&f.");
        getConfig().addDefault("MSG_WALK_SPEED", "%prefix%Hai impostato la tua &evelocità di camminata&f a &e%arg%&f.");
        getConfig().addDefault("MSG_SETSPAWN", "%prefix%Hai impostato lo &espawn&f.");

        getConfig().addDefault("MSG_STEC_GM_S", "%prefix%Hai impostato la &emodalità di gioco&f di &e%player%&f a &eSURVIVAL.");
        getConfig().addDefault("MSG_STN_GM_S", "%prefix%La tua &emodalità di gioco&f è stata impostata a &eSURVIVAL.");
        getConfig().addDefault("MSG_GM_S", "%prefix%Hai impostato la tua &emodalità di gioco&f a &eSURVIVAL.");
        getConfig().addDefault("MSG_STEC_GM_SP", "%prefix%Hai impostato la &emodalità di gioco&f di &e%player%&f a &eSPECTATOR.");
        getConfig().addDefault("MSG_STN_GM_SP", "%prefix%La tua &emodalità di gioco&f è stata impostata a &eSPECTATOR.");
        getConfig().addDefault("MSG_GM_SP", "%prefix%Hai impostato la tua &emodalità di gioco&f a &eSPECTATOR.");
        getConfig().addDefault("MSG_STEC_GM_C", "%prefix%Hai impostato la &emodalità di gioco&f di &e%player%&f a &eCREATIVE.");
        getConfig().addDefault("MSG_STN_GM_C", "%prefix%La tua &emodalità di gioco&f è stata impostata a &eCREATIVE.");
        getConfig().addDefault("MSG_GM_C", "%prefix%Hai impostato la tua &emodalità di gioco&f a &eCREATIVE.");
        getConfig().addDefault("MSG_STEC_GM_A", "%prefix%Hai impostato la &emodalità di gioco&f di &e%player%&f a &eADVENTURE.");
        getConfig().addDefault("MSG_STN_GM_A", "%prefix%La tua &emodalità di gioco&f è stata impostata a &eADVENTURE.");
        getConfig().addDefault("MSG_GM_A", "%prefix%Hai impostato la tua &emodalità di gioco&f a &eADVENTURE.");

        getConfig().addDefault("MSG_FLY_OFF", "%prefix%Hai impostato la tua &emodalità di volo&f a &cOFF&f.");
        getConfig().addDefault("MSG_FLY_ON", "%prefix%Hai impostato la tua &emodalità di volo&f a &aON&f.");
        getConfig().addDefault("MSG_STEC_FLY_OFF", "%prefix%Hai impostato la &emodalità di volo&f di &e%player%&f a &cOFF&f.");
        getConfig().addDefault("MSG_STN_FLY_OFF", "%prefix%La tua &emodalità di volo&f è stata impostata a &cOFF&f.");
        getConfig().addDefault("MSG_STEC_FLY_OFF", "%prefix%Hai impostato la &emodalità di volo&f di &e%player%&f a &aON&f.");
        getConfig().addDefault("MSG_STN_FLY_OFF", "%prefix%La tua &emodalità di volo&f è stata impostata a &aON&f.");


    }

    public void registerEvents(){

        Bukkit.getPluginManager().registerEvents(new JoinMessageEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new QuitMessageEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new VoidTP(this), this);
        Bukkit.getPluginManager().registerEvents(new NoRainForever(this), this);
        Bukkit.getPluginManager().registerEvents(new NoDamageForever(this), this);
        Bukkit.getPluginManager().registerEvents(new NoFoodChange(this), this);
        Bukkit.getPluginManager().registerEvents(new JoinTPSpawn(this), this);

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
        getCommand("speed").setExecutor(new SetSpeedCommand(this));
        getCommand("feed").setExecutor(new feedCommand(this));
        getCommand("heal").setExecutor(new healCommand(this));
    }

    public void registerAll(){

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(new ChatMessageEvent(this), this);
        } else {
            logConsole("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

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
        instance = this;
        registerAll();
        logConsole("&6[&e!&6] &lVERION &f" + versionplugin + "&8» &aPlugin abilitato!");

    }

    @Override
    public void onDisable() {
        logConsole("&6[&e!&6] &lVERION &f" + versionplugin + "&8» &cPlugin disabilitato!");
    }

}

