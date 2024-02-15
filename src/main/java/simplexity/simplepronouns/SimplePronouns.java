package simplexity.simplepronouns;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;
import simplexity.simplepronouns.commands.PronounCommand;
import simplexity.simplepronouns.commands.SPReload;
import simplexity.simplepronouns.commands.subcommands.HelpCommand;
import simplexity.simplepronouns.commands.subcommands.ListCommand;
import simplexity.simplepronouns.commands.subcommands.SetCommand;
import simplexity.simplepronouns.commands.subcommands.SubCommand;
import simplexity.simplepronouns.configs.ConfigLoader;
import simplexity.simplepronouns.configs.LocaleLoader;
import simplexity.simplepronouns.configs.PronounLoader;
import simplexity.simplepronouns.dependencies.PronounPlaceholders;

import java.util.HashMap;

public final class SimplePronouns extends JavaPlugin {
    
    private static SimplePronouns instance;
    public static HashMap<String, SubCommand> subCommands = new HashMap<>();
    private static MiniMessage miniMessage = MiniMessage.miniMessage();
    
    
    @Override
    public void onEnable() {
        instance = this;
        PronounLoader.getInstance().loadPronouns();
        this.saveDefaultConfig();
        ConfigLoader.getInstance().loadConfig();
        LocaleLoader.getInstance().loadLocale();
        this.getCommand("pronouns").setExecutor(new PronounCommand());
        this.getCommand("SPReload").setExecutor(new SPReload());
        if (this.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PronounPlaceholders(this).register();
        } else {
            this.getLogger().info("This plugin currently has no functionality independent of Placeholder API, it is highly recommended you download that plugin.");
        }
        populateSubCommands();
    }
    
    public static SimplePronouns getInstance() {
        return instance;
    }
    
    public static MiniMessage getMiniMessage() {
        return miniMessage;
    }
    
    private void populateSubCommands() {
        subCommands.put("set", new SetCommand(Util.pronounSetPerm, "set", LocaleLoader.getInstance().getSetHelp()));
        subCommands.put("list", new ListCommand(Util.pronounListPerm, "list", LocaleLoader.getInstance().getListHelp()));
        subCommands.put("help", new HelpCommand(Util.pronounBasePerm, "help", ""));
    }
    
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
