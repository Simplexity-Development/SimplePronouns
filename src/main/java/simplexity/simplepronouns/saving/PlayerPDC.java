package simplexity.simplepronouns.saving;

import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.SimplePronouns;
import simplexity.simplepronouns.configs.ConfigLoader;
import simplexity.simplepronouns.configs.PronounLoader;

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
    
    public @NotNull Pronoun getPronoun(OfflinePlayer player){
        String defaultPronounString = ConfigLoader.getInstance().getDefaultPronouns();
        Pronoun defaultPronoun = PronounLoader.pronouns.get(defaultPronounString);
        if (!(player instanceof Player p)) return defaultPronoun; // TODO: Default to something cause null not the best.
        String serializedPronoun = p.getPersistentDataContainer().get(pronounsKey, PersistentDataType.STRING);
        if (serializedPronoun == null) return defaultPronoun;
        Pronoun pronoun = Pronoun.deserialize(serializedPronoun);
        return (pronoun == null) ? defaultPronoun : pronoun;
    }

    public void close() {

    }
}
