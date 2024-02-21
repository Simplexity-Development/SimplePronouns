package simplexity.simplepronouns.saving;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.configs.ConfigLoader;
import simplexity.simplepronouns.configs.PronounLoader;

public class PronounManager {

    private static SaveHandler saveHandler;
    
    public static boolean setSelectedPronoun(OfflinePlayer player, Pronoun pronoun) {
        return saveHandler.setPronoun(player, pronoun);
    }
    
    public static Pronoun getSelectedPronoun(OfflinePlayer player) {
        Pronoun retrievedPronoun = saveHandler.getPronoun(player);
        if (retrievedPronoun == null) {
            // TODO: Return default
        }
        return retrievedPronoun;
    }
    
    public static Pronoun getPronounFromString(String string) {
        return PronounLoader.pronouns.get(string);
    }

    public static void loadSaveHandler() {
        if (saveHandler != null) saveHandler.close();
        switch (ConfigLoader.getInstance().getSaveType()) {
            case "pdc" -> saveHandler = new PlayerPDC();
            // case "yml" -> saveHandler = new YML(); TODO: Not Yet Implemented
            case "mysql" -> saveHandler = new DatabaseManager();
        }
        saveHandler.init();
    }
    
    
}
