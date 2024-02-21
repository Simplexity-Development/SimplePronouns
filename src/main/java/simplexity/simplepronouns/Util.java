package simplexity.simplepronouns;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

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
                Placeholder.unparsed("label", convertToTitleCase(pronoun.subjective()) + "/" + convertToTitleCase(pronoun.objective())),
                Placeholder.unparsed("sub", convertToTitleCase(pronoun.subjective())),
                Placeholder.unparsed("obj", convertToTitleCase(pronoun.objective())),
                Placeholder.unparsed("pos", convertToTitleCase(pronoun.possessive())),
                Placeholder.unparsed("posadj", convertToTitleCase(pronoun.possessiveAdjective())),
                Placeholder.unparsed("ref", convertToTitleCase(pronoun.reflexive())));
    }
    
    public static Component parsePronouns(Player player, String string, Pronoun pronoun){
        if (pronoun == null) {
            return miniMessage.deserialize(string);
        }
        return miniMessage.deserialize(string,
                Placeholder.unparsed("label", convertToTitleCase(pronoun.subjective()) + "/" + convertToTitleCase(pronoun.objective())),
                Placeholder.unparsed("sub", convertToTitleCase(pronoun.subjective())),
                Placeholder.unparsed("obj", convertToTitleCase(pronoun.objective())),
                Placeholder.unparsed("pos", convertToTitleCase(pronoun.possessive())),
                Placeholder.unparsed("posadj", convertToTitleCase(pronoun.possessiveAdjective())),
                Placeholder.unparsed("ref", convertToTitleCase(pronoun.reflexive())),
                Placeholder.component("name", player.displayName()));
    }
    
    public static Player checkPlayer(String string){
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(string);
        if (offlinePlayer instanceof Player player) {
            return player;
        } else {
            return null;
        }
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
