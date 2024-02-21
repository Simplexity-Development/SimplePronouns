package simplexity.simplepronouns.saving;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import simplexity.simplepronouns.Pronoun;

public abstract class SaveHandler {

    public abstract void init();

    public abstract @NotNull Pronoun getPronoun(OfflinePlayer player);

    public abstract boolean setPronoun(OfflinePlayer player, Pronoun pronoun);

    public abstract void close();

}
