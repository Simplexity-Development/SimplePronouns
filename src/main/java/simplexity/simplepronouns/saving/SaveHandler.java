package simplexity.simplepronouns.saving;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;
import simplexity.simplepronouns.Pronoun;

public abstract class SaveHandler {

    public abstract void init();

    public abstract @Nullable Pronoun getPronoun(OfflinePlayer player);

    public abstract boolean setPronoun(OfflinePlayer player, Pronoun pronoun);

    public abstract void close();

}
