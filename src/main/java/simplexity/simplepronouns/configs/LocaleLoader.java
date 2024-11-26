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
    
    private String pronounsSet, pronounsClear, pronounsGet, pronounsGetOther, pronounsAdminSet, pronounsAdminClear;
    private String exampleSentence;
    private String noPermission, syntaxError, notEnoughArguments, notConfigured, invalidPlayer, defaultPronoun, onlyAPlayer, noCommands, couldNotSet;
    private String commandReloaded, helpHeader, listHelp, setHelp, getHelp, clearHelp, customHelp, adminSetHelp, adminClearHelp, adminCustomHelp,
            listHeader, listItem, userPronouns;
    private String transferPdc;
    
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
        pronounsSet = localeConfig.getString("pronouns.set", "<white><bold>[</bold><green>Success</green><bold>]</bold></white> <gray>Your pronouns have been set to <yellow><label></yellow>.</gray>");
        pronounsClear = localeConfig.getString("pronouns.clear", "<white><bold>[</bold><green>Success</green><bold>]</bold></white> <gray>Your pronouns have been cleared and set to the configured default of <yellow><label></yellow></gray>");
        pronounsGet = localeConfig.getString("pronouns.get", "Your current pronouns are:");
        pronounsGetOther = localeConfig.getString("pronouns.get-other", "<name>'s current set pronouns are:");
        pronounsAdminSet = localeConfig.getString("pronouns.admin-set", "<name>'s pronouns have been set.");
        pronounsAdminClear = localeConfig.getString("pronouns.admin-clear", "<name>'s pronouns have been cleared");
        exampleSentence = localeConfig.getString("example-sentence", "\n<gray>A sentence with these pronouns looks like this: <u><yellow><sub></yellow></u> promised <u><yellow><ref></yellow></u> that nobody would use <u><yellow><pos></yellow></u> own tricks against <u><yellow><obj></yellow></u> again. The final laugh would be <u><yellow><posadj></yellow></u>.</gray>");
        noPermission = localeConfig.getString("errors.no-permission", "<red>You do not have permission to run this command</red>");
        syntaxError = localeConfig.getString("errors.syntax-error", "<red>Command not found, please check your spelling and run the command again</red>");
        notEnoughArguments = localeConfig.getString("errors.not-enough-arguments", "<red>Not enough arguments provided</red>");
        notConfigured = localeConfig.getString("errors.not-configured", "<red><input> has not been configured as a selectable pronoun. Please check your syntax and check again</red>");
        invalidPlayer = localeConfig.getString("errors.invalid-player", "<red><input> is not a valid player. Please check your syntax and try again.</red>");
        defaultPronoun = localeConfig.getString("errors.default-pronoun", "<red>There is a configuration error and there is currently no valid default pronoun. Please fix the configuration file before using this command again.</red>");
        onlyAPlayer = localeConfig.getString("errors.only-a-player", "<red>Only a player can run this command</red>");
        noCommands = localeConfig.getString("errors.no-commands", "\n<gray>Someone configured the permissions incorrectly, you have access to the help command but nothing else. I, the developer of the plugin, unfortunately cannot help with that. The staff on whichever server this is probably can though. You should probably ask them to fix that.</gray>");
        couldNotSet = localeConfig.getString("errors.could-not-set", "\n<red>Could not set the pronouns for <player>. Saving type PDC does not support offline player pronoun setting.");
        commandReloaded = localeConfig.getString("command-feedback.reloaded", "<gold>SimplePronouns Reloaded</gold>");
        helpHeader = localeConfig.getString("command-feedback.help-header", "<white><bold>[</bold><aqua>Pronouns Help</aqua><bold>]</bold></white>");
        listHelp = localeConfig.getString("command-feedback.list-help", "\n <gray>- <white>/pronouns <yellow>list</yellow></white> : Lists the configured pronouns and their variations.</gray>");
        setHelp = localeConfig.getString("command-feedback.set-help", "\n <gray>- <white>/pronouns <yellow>set {pronoun}</yellow></white> : Sets your chosen pronouns</gray>");
        getHelp = localeConfig.getString("command-feedback.get-help", "\n <gray>- <white>/pronouns <yellow>get {player}</yellow></white> : View the pronouns that someone else has selected</gray>");
        clearHelp = localeConfig.getString("command-feedback.clear-help", "\n <gray>- <white>/pronouns <yellow>clear</yellow></white> : Clears your selected pronouns and sets them to the configured default</gray>");
        customHelp = localeConfig.getString("command-feedback.custom-help", "\n <gray>- <white>/pronouns <yellow>custom</yellow> {subjective} {objective} {possesive} {possessive adjective} {reflexive}</white> : Sets a user's pronouns to a custom set.");
        adminSetHelp = localeConfig.getString("command-feedback.admin-set-help", "\n <gray>- <white>/pronouns <yellow>admin set {player} {pronoun}</yellow></white> : Sets the pronouns of another user</gray>");
        adminClearHelp = localeConfig.getString("command-feedback.admin-clear-help", "\n <gray>- <white>/pronouns <yellow>admin clear {player}</yellow></white> : Clears someone's selected pronouns and sets them to the configured default</gray>");
        adminCustomHelp = localeConfig.getString("command-feedback.admin-custom-help", "\n <gray>- <white>/pronouns <yellow>admin custom</yellow> {subjective} {objective} {possesive} {possessive adjective} {reflexive}</white> : Sets a user's pronouns to a custom set.");
        listHeader = localeConfig.getString("command-feedback.list-header", "<aqua>The current configured pronouns are:</aqua>");
        listItem = localeConfig.getString("command-feedback.list-item", "\n<hover:show_text:'<bold>[</bold><green><label></green><bold>]</bold>\n - Subjective: <yellow><u><sub></u></yellow>\n - Objective <yellow><u><obj></u></yellow>\n - Possessive: <yellow><u><pos></u></yellow>\n - Possessive-Adjective: <yellow><u><posadj></u></yellow>\n - Reflexive: <yellow><u><ref></u></yellow>'> - <bold>[</bold><green><label></green><bold>]</bold></hover>");
        userPronouns = localeConfig.getString("command-feedback.user-pronouns", "<gold><name>'s current pronouns are:</gold>");
        transferPdc = localeConfig.getString("logger.transfer-pdc", "Transferred PDC pronoun data to current saving type for <player>.");
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
    public String getNoCommands() {
        return noCommands;
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
    
    public String getAdminCustomHelp() {
        return adminCustomHelp;
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

    public String getCouldNotSet() {
        return couldNotSet;
    }

    public String getPronounsGetOther() {
        return pronounsGetOther;
    }

    public String getCustomHelp() {
        return customHelp;
    }

    public String getTransferPdc() {
        return transferPdc;
    }
}
