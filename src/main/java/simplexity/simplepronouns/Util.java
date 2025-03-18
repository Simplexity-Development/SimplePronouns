package simplexity.simplepronouns;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import simplexity.simplepronouns.configs.ConfigLoader;

public class Util {
    private static final MiniMessage miniMessage = SimplePronouns.getMiniMessage();
    
    public static String basePerm = "pronouns";
    public static String setPerm = "pronouns.set";
    public static String clearPerm = "pronouns.clear";
    public static String reloadPerm = "pronouns.reload";
    public static String listPerm = "pronouns.list";
    public static String getPerm = "pronouns.get";
    public static String customPerm = "pronouns.custom";
    public static String adminPerm = "pronouns.admin";
    public static String adminSet = "pronouns.admin.set";
    public static String adminClear = "pronouns.admin.clear";
    public static String adminCustom = "pronouns.admin.custom";
    
    public static Component parsePronouns(String string, Pronoun pronoun){
        if (pronoun == null) {
            return miniMessage.deserialize(string);
        }
        return miniMessage.deserialize(string,
                Placeholder.unparsed("label", convertToTitleCase(pronoun.getSubjective()) + "/" + convertToTitleCase(pronoun.getObjective())),
                Placeholder.unparsed("sub", convertToTitleCase(pronoun.getSubjective())),
                Placeholder.unparsed("obj", convertToTitleCase(pronoun.getObjective())),
                Placeholder.unparsed("pos", convertToTitleCase(pronoun.getPossessive())),
                Placeholder.unparsed("posadj", convertToTitleCase(pronoun.getPossessiveAdjective())),
                Placeholder.unparsed("ref", convertToTitleCase(pronoun.getReflexive())));
    }
    
    public static Component parsePronouns(@NotNull OfflinePlayer player, String string, Pronoun pronoun){
        if (pronoun == null || player.getName() == null) {
            pronoun = ConfigLoader.getInstance().getDefaultPronoun();
        }
        assert player.getName() != null;
        return miniMessage.deserialize(string,
                Placeholder.unparsed("label", convertToTitleCase(pronoun.getSubjective()) + "/" + convertToTitleCase(pronoun.getObjective())),
                Placeholder.unparsed("sub", convertToTitleCase(pronoun.getSubjective())),
                Placeholder.unparsed("obj", convertToTitleCase(pronoun.getObjective())),
                Placeholder.unparsed("pos", convertToTitleCase(pronoun.getPossessive())),
                Placeholder.unparsed("posadj", convertToTitleCase(pronoun.getPossessiveAdjective())),
                Placeholder.unparsed("ref", convertToTitleCase(pronoun.getReflexive())),
                Placeholder.unparsed("name", player.getName()));
    }
    
    public static String convertToTitleCase(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        String[] words = input.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return result.toString().trim();
    }

}
