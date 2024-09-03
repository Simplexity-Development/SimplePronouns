package simplexity.simplepronouns.configs;

import org.bukkit.configuration.file.FileConfiguration;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.SimplePronouns;
import simplexity.simplepronouns.saving.PronounManager;

public class ConfigLoader {
    
    private static ConfigLoader instance;
    private String defaultPronouns;
    private Pronoun defaultPronoun;
    private String saveType;
    private String ip;
    private String name;
    private String username;
    private String password;
    
    public ConfigLoader() {
    }
    
    public static ConfigLoader getInstance() {
        if (instance == null) instance = new ConfigLoader();
        return instance;
    }
    
    public void loadConfig() {
        FileConfiguration config = SimplePronouns.getInstance().getConfig();
        defaultPronouns = config.getString("default-pronouns");
        saveType = config.getString("save-type");
        ip = config.getString("mysql.ip");
        name = config.getString("mysql.name");
        username = config.getString("mysql.username");
        password = config.getString("mysql.password");
        saveType = config.getString("save-type").toLowerCase();
        LocaleLoader.getInstance().loadLocale();
        PronounLoader.getInstance().loadPronouns();
        if (PronounLoader.pronouns.containsKey(defaultPronouns)) {
            defaultPronoun = PronounLoader.pronouns.get(defaultPronouns);
        } else {
            SimplePronouns.getInstance().getLogger().warning("There was an issue loading the default pronouns from the config." +
                    "\nPlease make sure the default pronoun names match in the config.yml and pronouns.yml" +
                    "\nSetting to 'default' until resolved");
            defaultPronoun = new Pronoun("", "", "", "", "");
        }
        PronounManager.loadSaveHandler();
    }
    
    public String getDefaultPronouns() {
        return defaultPronouns;
    }
    public String getSaveType() {
        return saveType;
    }
    
    public String getIp() {
        return ip;
    }
    public String getName() {
        return name;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }

    public Pronoun getDefaultPronoun() {
        return defaultPronoun;
    }
}
