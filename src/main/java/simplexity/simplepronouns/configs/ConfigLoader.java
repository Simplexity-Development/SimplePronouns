package simplexity.simplepronouns.configs;

import org.bukkit.configuration.file.FileConfiguration;
import simplexity.simplepronouns.DatabaseManager;
import simplexity.simplepronouns.SimplePronouns;

public class ConfigLoader {
    
    private static ConfigLoader instance;
    private boolean enabled;
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
        enabled = config.getBoolean("mysql.enabled");
        ip = config.getString("mysql.ip");
        name = config.getString("mysql.name");
        username = config.getString("mysql.username");
        password = config.getString("mysql.password");
        if (enabled) {
            DatabaseManager.createDatabase();
        }
        LocaleLoader.getInstance().loadLocale();
    }
    
    public boolean isEnabled() {
        return enabled;
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
