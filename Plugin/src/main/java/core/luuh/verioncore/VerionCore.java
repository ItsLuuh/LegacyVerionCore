package core.luuh.verioncore;

import core.luuh.verioncore.chatcolor.chatnickcolorCommand;
import core.luuh.verioncore.chatcolor.colorGUIEvent;
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
import core.luuh.verioncore.warp.SetWarpCommand;
import core.luuh.verioncore.warp.WarpCommand;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static core.luuh.verioncore.VerionAPIManager.logConsole;

public final class VerionCore extends JavaPlugin {

    private NMS nmsHandler;

    static VerionCore instance;

    public static VerionCore getInstance() {
        return instance;
    }
    

    final String versionplugin = this.getDescription().getVersion();

    private static Chat chat = null;

    public static Chat getChat() {
        return chat;
    }

    /*
    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }
     */

    private final SettingsManager settings = SettingsManager.getInstance();

    public void registerDefaults(){
        getConfig().addDefault("prefix", "&6[&e!&6] &lVERION &8» &f");
        getConfig().addDefault("joins", 0);
        getConfig().addDefault("joinmessage", "%prefix% Benvenuto &e%player%&f su &6&lVERIONMC&f! &8(&e#%joins%&8)");
        getConfig().addDefault("spawn.join-tp-spawn", true);
        getConfig().addDefault("spawn.on-join-message.enabled", true);

        ArrayList<String> listmex = new ArrayList<String>();
        listmex.add("");
        listmex.add("Buongiorno");
        listmex.add("");

        getConfig().addDefault("spawn.on-join-message.message", listmex);
        getConfig().addDefault("spawn.title.enabled", true);
        getConfig().addDefault("spawn.title.join-title-msg", "&6&lVERION&e&lMC");
        getConfig().addDefault("spawn.title.join-subtitle-msg", "&fBenvenuto!");
        getConfig().addDefault("spawn.title.fadeIn", 20);
        getConfig().addDefault("spawn.title.stay", 280);
        getConfig().addDefault("spawn.title.fadeOut", 20);
        getConfig().addDefault("chat", "%vault_prefix% &f%player_name% &8» &f%message%");

        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("world");
        getConfig().addDefault("VOIDTP.enabled", true);
        getConfig().addDefault("VOIDTP.every-world", false);
        getConfig().addDefault("VOIDTP.worlds", list1);

        getConfig().addDefault("REMOVE_RAIN.enabled", true);
        getConfig().addDefault("REMOVE_RAIN.every-world", false);
        getConfig().addDefault("REMOVE_RAIN.worlds", list1);

        getConfig().addDefault("REMOVE_DAMAGE.enabled", true);
        getConfig().addDefault("REMOVE_DAMAGE.every-world", false);
        getConfig().addDefault("REMOVE_DAMAGE.worlds", list1);

        getConfig().addDefault("REMOVE_FOOD.enabled", true);
        getConfig().addDefault("REMOVE_FOOD.every-world", false);
        getConfig().addDefault("REMOVE_FOOD.worlds", list1);

        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("%prefix% text");
        getConfig().addDefault("MSG_CORE_HELP", list2);

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

    public void registerNMS(){

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

    public void registerEvents(){

        Bukkit.getPluginManager().registerEvents(new JoinMessageEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new QuitMessageEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new VoidTP(this), this);
        Bukkit.getPluginManager().registerEvents(new NoRainForever(this), this);
        Bukkit.getPluginManager().registerEvents(new NoDamageForever(this), this);
        Bukkit.getPluginManager().registerEvents(new NoFoodChange(this), this);
        Bukkit.getPluginManager().registerEvents(new JoinTPSpawn(this), this);
        Bukkit.getPluginManager().registerEvents(new PhantomSpawning(this), this);
        Bukkit.getPluginManager().registerEvents(new colorGUIEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new ItemsCommand(this), this);

    }

    public void registerCommands(){

        getCommand("core").setExecutor(new CoreCommand(this));
        getCommand("core").setTabCompleter(new CoreCommand(this));

        getCommand("help").setExecutor(new HelpCommand(this));
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("fly").setTabCompleter(new FlyCommand(this));

        getCommand("setspawn").setExecutor(new SetSpawn(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("gamemode").setExecutor(new Gamemode(this));
        getCommand("gamemode").setTabCompleter(new Gamemode(this));

        getCommand("gmc").setExecutor(new GamemodeCreative(this));
        getCommand("gms").setExecutor(new GamemodeSurvival(this));
        getCommand("gma").setExecutor(new GamemodeAdventure(this));
        getCommand("gmsp").setExecutor(new GamemodeSpectator(this));
        getCommand("speed").setExecutor(new SetSpeedCommand(this));
        getCommand("speed").setTabCompleter(new SetSpeedCommand(this));

        getCommand("feed").setExecutor(new feedCommand(this));
        getCommand("heal").setExecutor(new healCommand(this));
        getCommand("setwarp").setExecutor(new SetWarpCommand(this));
        getCommand("delwarp").setExecutor(new SetWarpCommand(this));
        getCommand("delwarp").setTabCompleter(new SetWarpCommand(this));

        getCommand("warp").setExecutor(new WarpCommand(this));
        getCommand("warp").setTabCompleter(new WarpCommand(this));

        getCommand("chatcolor").setExecutor(new chatnickcolorCommand(this));
        getCommand("nickcolor").setExecutor(new chatnickcolorCommand(this));
    }

    public void registerAll(){

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(new ChatMessageEvent(this), this);
        } else {
            logConsole("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        settings.setup(this);


        registerNMS();
        registerEvents();
        registerCommands();

        //setupChat();

    }

    @Override
    public void onEnable() {
        instance = this;
        registerAll();
        logConsole("&a&r                                    ");
        logConsole("&b __      ________ _____  _____ ____  _   _   &f|  ");
        logConsole("&b \\ \\    / /  ____|  __ \\|_   _/ __ \\| \\ | |  &f|  &2Verion &7" + versionplugin);
        logConsole("&b  \\ \\  / /| |__  | |__) | | || |  | |  \\| |  &f|  &8Made by Verion Developing Team");
        logConsole("&b   \\ \\/ / |  __| |  _  /  | || |  | | . ` |  &f|  ");
        logConsole("&b    \\  /  | |____| | \\ \\ _| || |__| | |\\  |  &f|  ");
        logConsole("&b     \\/   |______|_|  \\_\\_____\\____/|_| \\_|  &f|  ");
        logConsole("&a&r                                    ");
        logConsole("");

    }

    @Override
    public void onDisable() {
        settings.saveData();
        logConsole("#D60000[#FF0000!#D60000]&r &6Verion-CORE&r " + versionplugin + "&r &f»&r #C83838DISABLED!&r");
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



