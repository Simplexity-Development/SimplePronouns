package simplexity.simplepronouns.saving;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import simplexity.simplepronouns.Pronoun;

public class PronounManager {
    
    public static void setSelectedPronoun(OfflinePlayer offlinePlayer, Pronoun pronoun) {
        if (!(offlinePlayer instanceof Player player)) return;
        String id = pronoun.getLabel();
        PlayerPDC.savePronouns(player, id);
    }
    
    public static Pronoun getSelectedPronoun(OfflinePlayer offlinePlayer) {
        if (!(offlinePlayer instanceof Player player)) return null;
        return PlayerPDC.getPronouns(player);
    }
    
    
}
