package simplexity.simplepronouns.configs;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import simplexity.simplepronouns.SimplePronouns;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class LocaleLoader {
    
    private static LocaleLoader instance;
    private final String fileName = "locale.yml";
    private final File localeFile = new File(SimplePronouns.getInstance().getDataFolder(), fileName);
    private final FileConfiguration localeConfig = new YamlConfiguration();
    Logger logger = SimplePronouns.getInstance().getLogger();
    
    private String pronounsSet, pronounsClear, pronounsGet, pronounsSetOther, pronounsClearOther, pronounsGetOther;
    private String exampleSentence;
    private String errorMustBePlayer, noPermission, syntaxError, notEnoughArguments;
    private String commandReloaded, listHeader, listItem;
    
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
            e.printStackTrace();
        }
        pronounsSet = localeConfig.getString("pronouns.set");
        pronounsClear = localeConfig.getString("pronouns.cleared");
        pronounsGet = localeConfig.getString("pronouns.get");
        pronounsSetOther = localeConfig.getString("pronouns.set-other");
        pronounsClearOther = localeConfig.getString("pronouns.clear-other");
        pronounsGetOther = localeConfig.getString("pronouns.get-other");
        exampleSentence = localeConfig.getString("example-sentence");
        errorMustBePlayer = localeConfig.getString("errors.must-be-player");
        noPermission = localeConfig.getString("errors.no-permission");
        syntaxError = localeConfig.getString("errors.syntax-error");
        notEnoughArguments = localeConfig.getString("errors.not-enough-arguments");
        commandReloaded = localeConfig.getString("command-feedback.reloaded");
        listHeader = localeConfig.getString("command-feedback.list-header");
        listItem = localeConfig.getString("command-feedback.list-item");
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
    
    public String getPronounsSetOther() {
        return pronounsSetOther;
    }
    
    public String getPronounsClearOther() {
        return pronounsClearOther;
    }
    
    public String getPronounsGetOther() {
        return pronounsGetOther;
    }
    
    public String getExampleSentence() {
        return exampleSentence;
    }
    
    public String getErrorMustBePlayer() {
        return errorMustBePlayer;
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
    
    public String getCommandReloaded() {
        return commandReloaded;
    }
    public String getListHeader() {
        return listHeader;
    }
    public String getListItem() {
        return listItem;
    }
}
