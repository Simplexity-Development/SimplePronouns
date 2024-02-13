package simplexity.simplepronouns;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import simplexity.simplepronouns.configs.ConfigLoader;

import java.util.HashMap;

public class PronounManager {
    
    public static HashMap<String, Pronoun> pronouns = new HashMap<>();
    
    public static void setSelectedPronoun(Player player, Pronoun pronoun) {
        if (ConfigLoader.getInstance().isEnabled()) {
            //MYSQL stuff here b/c I don't know how to do that rn
            SimplePronouns.getInstance().getLogger().info("MySQL is not yet implemented, so the pronouns will be saved to the player's persistent data container.");
            player.getPersistentDataContainer().set(Util.pronounsKey, PersistentDataType.STRING, pronoun.getLabel());
        } else {
            player.getPersistentDataContainer().set(Util.pronounsKey, PersistentDataType.STRING, pronoun.getLabel());
        }
    }
    
    public static Pronoun getSelectedPronoun(Player player) {
        return pronouns.get(player.getPersistentDataContainer().get(Util.pronounsKey, PersistentDataType.STRING));
    }
    
}
