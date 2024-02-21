package simplexity.simplepronouns.configs;

import org.bukkit.configuration.file.FileConfiguration;
import simplexity.simplepronouns.SimplePronouns;
import simplexity.simplepronouns.saving.PronounManager;

public class ConfigLoader {
    
    private static ConfigLoader instance;
    private String defaultPronouns;
    private boolean customPronouns;
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
        customPronouns = config.getBoolean("allow-custom-pronouns");
        saveType = config.getString("save-type");
        ip = config.getString("mysql.ip");
        name = config.getString("mysql.name");
        username = config.getString("mysql.username");
        password = config.getString("mysql.password");
        saveType = config.getString("save-type").toLowerCase();
        LocaleLoader.getInstance().loadLocale();
        PronounManager.loadSaveHandler();
    }
    
    public String getDefaultPronouns() {
        return defaultPronouns;
    }
    public boolean allowCustomPronouns(){
        return customPronouns;
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
}
