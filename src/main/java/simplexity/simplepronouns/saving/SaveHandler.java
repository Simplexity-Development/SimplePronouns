package simplexity.simplepronouns.saving;

import org.bukkit.OfflinePlayer;
import simplexity.simplepronouns.Pronoun;

public abstract class SaveHandler {

    public abstract void init();

    public abstract Pronoun getPronoun(OfflinePlayer player);

    public abstract boolean setPronoun(OfflinePlayer player, Pronoun pronoun);

}
