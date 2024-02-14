package simplexity.simplepronouns.configs;


import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.PronounManager;
import simplexity.simplepronouns.SimplePronouns;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

public class PronounLoader {
    
    private static PronounLoader instance;
    private final String fileName = "pronouns.yml";
    private final File pronounFile = new File(SimplePronouns.getInstance().getDataFolder(), fileName);
    private final FileConfiguration pronounConfig = new YamlConfiguration();
    Logger logger = SimplePronouns.getInstance().getLogger();
    
    private PronounLoader() {
        if (!pronounFile.exists()) {
            SimplePronouns.getInstance().saveResource(fileName, false);
        }
    }
    
    public static PronounLoader getInstance() {
        if (instance == null) instance = new PronounLoader();
        return instance;
    }
    
    public FileConfiguration getPronounConfig() {
        return pronounConfig;
    }
    
    public void loadPronouns() {
        try {
            pronounConfig.load(pronounFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        ConfigurationSection pronounSection = pronounConfig.getConfigurationSection("pronouns");
        if (pronounSection == null) {
            logger.warning("No pronouns found in config file.");
            return;
        }
        PronounManager.pronouns.clear();
        PronounManager.pronounLabels.clear();
        Set<String> keys = pronounSection.getKeys(false);
        for (String pronounKey : keys) {
            if (pronounKey == null) continue;
            ConfigurationSection configSection = pronounConfig.getConfigurationSection("pronouns." + pronounKey);
            logger.info("pronoun in for loop: " + pronounKey);
            if (configSection == null) continue;
            PronounManager.pronouns.put(pronounKey, new Pronoun(
                    pronounKey,
                    configSection.getString("subjective"),
                    configSection.getString("objective"),
                    configSection.getString("possessive"),
                    configSection.getString("possessive-adj"),
                    configSection.getString("reflexive")));
            PronounManager.pronounLabels.add(pronounKey);
            logger.info("===============================================");
            logger.info("Loaded pronoun " + pronounKey);
            logger.info("Subjective: " + configSection.getString("subjective"));
            logger.info("Objective: " + configSection.getString("objective"));
            logger.info("Possessive: " + configSection.getString("possessive"));
            logger.info("Possessive Adj: " + configSection.getString("possessive-adj"));
            logger.info("Reflexive: " + configSection.getString("reflexive"));
            logger.info("===============================================");
        }
    }
    
}
