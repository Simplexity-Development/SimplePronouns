package simplexity.simplepronouns.saving;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.SimplePronouns;
import simplexity.simplepronouns.configs.PronounLoader;

public class PlayerPDC {
    public static NamespacedKey pronounsKey = new NamespacedKey(SimplePronouns.getInstance(), "pronouns");
    
    public static void savePronouns(Player player, String label){
        player.getPersistentDataContainer().set(pronounsKey, PersistentDataType.STRING, label);
    }
    
    public static Pronoun getPronouns(Player player){
        String label = player.getPersistentDataContainer().get(pronounsKey, PersistentDataType.STRING);
        return PronounLoader.pronouns.get(label);
    }
    

}
