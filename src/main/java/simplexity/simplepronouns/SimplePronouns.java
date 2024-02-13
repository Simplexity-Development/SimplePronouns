package simplexity.simplepronouns;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;
import simplexity.simplepronouns.commands.PronounCommand;
import simplexity.simplepronouns.commands.subcommands.SetCommand;
import simplexity.simplepronouns.commands.subcommands.SubCommand;
import simplexity.simplepronouns.configs.ConfigLoader;
import simplexity.simplepronouns.configs.PronounLoader;

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
        this.getServer().getPluginManager().registerEvents(new LoginListener(), this);
        this.getCommand("pronouns").setExecutor(new PronounCommand());
        // Plugin startup logic

    }
    public static SimplePronouns getInstance() {
        return instance;
    }
    public static MiniMessage getMiniMessage() {
        return miniMessage;
    }
    
    private void populateSubCommands(){
        subCommands.put("set", new SetCommand(Util.pronounSetPerm, "set", "Sets the specified pronoun for the player"));
    }
    

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
