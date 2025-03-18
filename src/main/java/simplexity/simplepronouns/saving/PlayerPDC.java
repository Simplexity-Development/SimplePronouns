package simplexity.simplepronouns.saving;

import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.SimplePronouns;

public class PlayerPDC extends SaveHandler {
    public static NamespacedKey pronounsKey = new NamespacedKey(SimplePronouns.getInstance(), "pronouns");

    public void init() {

    }

    public boolean setPronoun(OfflinePlayer player, Pronoun pronoun){
        if (!(player instanceof Player p)) return false;
        if (pronoun == null) {
            p.getPersistentDataContainer().remove(pronounsKey);
            return true;
        }
        p.getPersistentDataContainer().set(pronounsKey, PersistentDataType.STRING, Pronoun.serialize(pronoun));
        return true;
    }
    
    public @Nullable Pronoun getPronoun(OfflinePlayer player){
        if (!(player instanceof Player p)) return null;
        if (!p.getPersistentDataContainer().has(pronounsKey, PersistentDataType.STRING)) return null;
        String serializedPronoun = p.getPersistentDataContainer().get(pronounsKey, PersistentDataType.STRING);
        if (serializedPronoun == null) return null;
        return Pronoun.deserialize(serializedPronoun);
    }

    public void close() {

    }
}
