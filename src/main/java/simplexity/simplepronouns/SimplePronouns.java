package simplexity.simplepronouns;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;
import simplexity.simplepronouns.commands.PronounCommand;
import simplexity.simplepronouns.commands.SPReload;
import simplexity.simplepronouns.commands.subcommands.AdminCommand;
import simplexity.simplepronouns.commands.subcommands.ClearCommand;
import simplexity.simplepronouns.commands.subcommands.GetCommand;
import simplexity.simplepronouns.commands.subcommands.HelpCommand;
import simplexity.simplepronouns.commands.subcommands.ListCommand;
import simplexity.simplepronouns.commands.subcommands.SetCommand;
import simplexity.simplepronouns.commands.subcommands.SubCommand;
import simplexity.simplepronouns.commands.subcommands.adminsubcommands.ClearAdminCommand;
import simplexity.simplepronouns.commands.subcommands.adminsubcommands.CustomAdminCommand;
import simplexity.simplepronouns.commands.subcommands.adminsubcommands.SetAdminCommand;
import simplexity.simplepronouns.commands.subcommands.CustomPronounCommand;
import simplexity.simplepronouns.configs.ConfigLoader;
import simplexity.simplepronouns.configs.LocaleLoader;
import simplexity.simplepronouns.configs.PronounLoader;
import simplexity.simplepronouns.dependencies.PronounPlaceholders;
import simplexity.simplepronouns.listener.PronounTransferListener;

import java.util.HashMap;

public final class SimplePronouns extends JavaPlugin {
    
    private static SimplePronouns instance;
    public static HashMap<String, SubCommand> subCommands = new HashMap<>();
    public static HashMap<String, SubCommand> adminSubCommands = new HashMap<>();
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    
    
    @Override
    public void onEnable() {
        instance = this;
        PronounLoader.getInstance().loadPronouns();
        this.saveDefaultConfig();
        ConfigLoader.getInstance().loadConfig();
        LocaleLoader.getInstance().loadLocale();
        this.getCommand("pronouns").setExecutor(new PronounCommand());
        this.getCommand("SPReload").setExecutor(new SPReload());
        this.getServer().getPluginManager().registerEvents(new PronounTransferListener(), this);
        if (this.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PronounPlaceholders().register();
        } else {
            this.getLogger().info("This plugin currently has no functionality independent of Placeholder API, it is highly recommended you download that plugin.");
        }
        populateSubCommands();
        populateAdminSubCommands();
    }
    
    public static SimplePronouns getInstance() {
        return instance;
    }
    
    public static MiniMessage getMiniMessage() {
        return miniMessage;
    }
    
    private void populateSubCommands() {
        subCommands.put("set", new SetCommand(Util.setPerm, "set", LocaleLoader.getInstance().getSetHelp()));
        subCommands.put("list", new ListCommand(Util.listPerm, "list", LocaleLoader.getInstance().getListHelp()));
        subCommands.put("help", new HelpCommand(Util.basePerm, "help", ""));
        subCommands.put("get", new GetCommand(Util.getPerm, "get", LocaleLoader.getInstance().getGetHelp()));
        subCommands.put("admin", new AdminCommand(Util.adminPerm, "admin", ""));
        subCommands.put("clear", new ClearCommand(Util.clearPerm, "clear", LocaleLoader.getInstance().getClearHelp()));
        subCommands.put("custom", new CustomPronounCommand(Util.customPerm, "custom", ""));
    }
    
    private void populateAdminSubCommands() {
        adminSubCommands.put("set", new SetAdminCommand(Util.adminSet, "set", LocaleLoader.getInstance().getAdminSetHelp()));
        adminSubCommands.put("clear", new ClearAdminCommand(Util.adminClear, "clear", LocaleLoader.getInstance().getAdminClearHelp()));
        adminSubCommands.put("custom", new CustomAdminCommand(Util.adminCustom, "custom", LocaleLoader.getInstance().getAdminCustomHelp()));
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
