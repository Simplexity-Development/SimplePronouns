package simplexity.simplepronouns.configs;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import simplexity.simplepronouns.SimplePronouns;

import java.io.File;
import java.io.IOException;

public class LocaleLoader {
    
    private static LocaleLoader instance;
    private final String fileName = "locale.yml";
    private final File localeFile = new File(SimplePronouns.getInstance().getDataFolder(), fileName);
    private final FileConfiguration localeConfig = new YamlConfiguration();
    
    private String pronounsSet, pronounsClear, pronounsGet, pronounsAdminSet, pronounsAdminClear;
    private String exampleSentence;
    private String noPermission, syntaxError, notEnoughArguments, notConfigured, invalidPlayer, defaultPronoun, onlyAPlayer;
    private String commandReloaded, helpHeader, listHelp, setHelp, getHelp, clearHelp, adminSetHelp, adminClearHelp, listHeader,
            listItem, userPronouns;
    
    private LocaleLoader() {
        if (!localeFile.exists()) {
            SimplePronouns.getInstance().saveResource(fileName, false);
        }
    }
    
    public static LocaleLoader getInstance() {
        if (instance == null) instance = new LocaleLoader();
        return instance;
    }
    
    public FileConfiguration getPronounConfig() {
        return localeConfig;
    }
    
    public void loadLocale() {
        try {
            localeConfig.load(localeFile);
        } catch (IOException | InvalidConfigurationException e) {
            SimplePronouns.getInstance().getLogger().severe("Error loading locale.yml");
            SimplePronouns.getInstance().getLogger().severe(e.getMessage());
        }
        pronounsSet = localeConfig.getString("pronouns.set");
        pronounsClear = localeConfig.getString("pronouns.clear");
        pronounsGet = localeConfig.getString("pronouns.get");
        pronounsAdminSet = localeConfig.getString("pronouns.admin-set");
        pronounsAdminClear = localeConfig.getString("pronouns.admin-clear");
        exampleSentence = localeConfig.getString("example-sentence");
        noPermission = localeConfig.getString("errors.no-permission");
        syntaxError = localeConfig.getString("errors.syntax-error");
        notEnoughArguments = localeConfig.getString("errors.not-enough-arguments");
        notConfigured = localeConfig.getString("errors.not-configured");
        invalidPlayer = localeConfig.getString("errors.invalid-player");
        defaultPronoun = localeConfig.getString("errors.default-pronoun");
        onlyAPlayer = localeConfig.getString("errors.only-a-player");
        commandReloaded = localeConfig.getString("command-feedback.reloaded");
        helpHeader = localeConfig.getString("command-feedback.help-header");
        listHelp = localeConfig.getString("command-feedback.list-help");
        setHelp = localeConfig.getString("command-feedback.set-help");
        getHelp = localeConfig.getString("command-feedback.get-help");
        clearHelp = localeConfig.getString("command-feedback.clear-help");
        adminSetHelp = localeConfig.getString("command-feedback.admin-set-help");
        adminClearHelp = localeConfig.getString("command-feedback.admin-clear-help");
        listHeader = localeConfig.getString("command-feedback.list-header");
        listItem = localeConfig.getString("command-feedback.list-item");
        userPronouns = localeConfig.getString("command-feedback.user-pronouns");
        
    }
    
    public String getPronounsSet() {
        return pronounsSet;
    }
    
    public String getPronounsClear() {
        return pronounsClear;
    }
    
    public String getPronounsGet() {
        return pronounsGet;
    }
    
    public String getPronounsAdminSet() {
        return pronounsAdminSet;
    }
    
    public String getPronounsAdminClear() {
        return pronounsAdminClear;
    }
    
    public String getExampleSentence() {
        return exampleSentence;
    }
    
    public String getNoPermission() {
        return noPermission;
    }
    
    public String getSyntaxError() {
        return syntaxError;
    }
    
    public String getNotEnoughArguments() {
        return notEnoughArguments;
    }
    
    public String getNotConfigured() {
        return notConfigured;
    }
    
    public String getInvalidPlayer() {
        return invalidPlayer;
    }
    
    public String getDefaultPronoun() {
        return defaultPronoun;
    }
    
    public String getOnlyAPlayer() {
        return onlyAPlayer;
    }
    
    public String getCommandReloaded() {
        return commandReloaded;
    }
    
    public String getHelpHeader() {
        return helpHeader;
    }
    
    public String getListHelp() {
        return listHelp;
    }
    
    public String getSetHelp() {
        return setHelp;
    }
    
    public String getGetHelp() {
        return getHelp;
    }
    
    public String getClearHelp() {
        return clearHelp;
    }
    
    public String getAdminSetHelp() {
        return adminSetHelp;
    }
    
    public String getAdminClearHelp() {
        return adminClearHelp;
    }
    
    public String getListHeader() {
        return listHeader;
    }
    
    public String getListItem() {
        return listItem;
    }
    
    public String getUserPronouns() {
        return userPronouns;
    }
    
}
