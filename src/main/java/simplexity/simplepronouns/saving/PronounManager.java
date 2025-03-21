package simplexity.simplepronouns.saving;

import org.bukkit.OfflinePlayer;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.configs.ConfigLoader;
import simplexity.simplepronouns.configs.PronounLoader;

public class PronounManager {

    private static SaveHandler saveHandler;
    
    public static boolean setSelectedPronoun(OfflinePlayer player, Pronoun pronoun) {
        return saveHandler.setPronoun(player, pronoun);
    }
    
    public static Pronoun getSelectedPronoun(OfflinePlayer player) {
        Pronoun pronoun = saveHandler.getPronoun(player);
        if (pronoun == null) return ConfigLoader.getInstance().getDefaultPronoun();
        return pronoun;
    }
    
    public static Pronoun getPronounFromString(String string) {
        return PronounLoader.pronouns.get(string);
    }

    public static void loadSaveHandler() {
        if (saveHandler != null) saveHandler.close();
        switch (ConfigLoader.getInstance().getSaveType()) {
            case "mysql" -> saveHandler = new DatabaseManager();
            default -> saveHandler = new PlayerPDC();
        }
        saveHandler.init();
    }
    
    
}
