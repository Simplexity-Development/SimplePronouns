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
        return "pronoun";
    }

    @Override
    public @NotNull String getAuthor() {
        return "simplexity";
    }

    @Override
    public @NotNull String getVersion() {
        return "2.0.1";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        String[] paramList = params.split("-");
        String pronoun = switch (paramList[0]) {
            case "sub" -> {
                String subjective = PronounManager.getSelectedPronoun(player).getSubjective();
                if (subjective == null) {
                    yield "none";
                } else {
                    yield subjective;
                }
            }
            case "obj" -> {
                String objective = PronounManager.getSelectedPronoun(player).getObjective();
                if (objective == null) {
                    yield "none";
                } else {
                    yield objective;
                }
            }
            case "pos" -> {
                String possessive = PronounManager.getSelectedPronoun(player).getPossessive();
                if (possessive == null ) {
                    yield "none";
                } else {
                    yield possessive;
                }
            }
            case "pos_adj" -> {
                String possessiveAdjective = PronounManager.getSelectedPronoun(player).getPossessiveAdjective();
                if (possessiveAdjective == null) {
                    yield "none";
                } else {
                    yield possessiveAdjective;
                }
            }
            case "ref" -> {
                String reflexive = PronounManager.getSelectedPronoun(player).getReflexive();
                if (reflexive == null) {
                    yield "none";
                } else {
                    yield reflexive;
                }
            }
            default ->  "none";
        };
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
        if (string == null || string.isEmpty()) return string;
        String firstLetter = string.substring(0, 1);
        return firstLetter.toUpperCase() + string.substring(1);
    }
}
