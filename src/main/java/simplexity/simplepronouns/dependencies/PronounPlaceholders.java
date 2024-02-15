package simplexity.simplepronouns.dependencies;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import simplexity.simplepronouns.SimplePronouns;
import simplexity.simplepronouns.saving.PronounManager;

public class PronounPlaceholders extends PlaceholderExpansion {
    
    private final SimplePronouns plugin;
    
    public PronounPlaceholders(SimplePronouns plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public @NotNull String getIdentifier() {
        return "pronoun";
    }
    
    @Override
    public @NotNull String getAuthor() {
        return "simplexity";
    }
    
    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }
    
    @Override
    public boolean persist() {
        return true;
    }
    
    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        String[] paramList = params.split("_");
        String pronoun = switch (paramList[0]) {
            case "sub" -> PronounManager.getSelectedPronoun(player).getSubjective();
            case "obj" -> PronounManager.getSelectedPronoun(player).getObjective();
            case "pos" -> PronounManager.getSelectedPronoun(player).getPossessive();
            case "pos-adj" -> PronounManager.getSelectedPronoun(player).getPossessiveAdjective();
            case "ref" -> PronounManager.getSelectedPronoun(player).getReflexive();
            default -> null;
        };
        if (pronoun == null) return null;
        if (paramList.length > 1) {
            pronoun = switch (paramList[1]) {
                case "caps" -> capitalizeString(pronoun);
                case "all-caps" -> pronoun.toUpperCase();
                default -> pronoun;
            };
        }
        return pronoun;
    }
    
    private String capitalizeString(String string) {
        String firstLetter = string.substring(0, 1);
        return firstLetter.toUpperCase() + string.substring(1);
    }
}
