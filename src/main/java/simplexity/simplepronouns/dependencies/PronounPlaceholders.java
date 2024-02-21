package simplexity.simplepronouns.dependencies;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import simplexity.simplepronouns.saving.PronounManager;

public class PronounPlaceholders extends PlaceholderExpansion {
    
    public PronounPlaceholders() {
    }
    
    @Override
    public @NotNull String getIdentifier() {
        return "sp";
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
        String[] paramList = params.split("-");
        String pronoun = switch (paramList[0]) {
            case "sub" -> PronounManager.getSelectedPronoun(player).subjective();
            case "obj" -> PronounManager.getSelectedPronoun(player).objective();
            case "pos" -> PronounManager.getSelectedPronoun(player).possessive();
            case "posadj" -> PronounManager.getSelectedPronoun(player).possessiveAdjective();
            case "ref" -> PronounManager.getSelectedPronoun(player).reflexive();
            default -> null;
        };
        if (pronoun == null) return null;
        if (paramList.length > 1) {
            pronoun = switch (paramList[1]) {
                case "title" -> capitalizeString(pronoun);
                case "caps" -> pronoun.toUpperCase();
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
