package simplexity.simplepronouns;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Util {
    private static MiniMessage miniMessage = SimplePronouns.getMiniMessage();
    
    public static String pronounBasePerm = "pronouns";
    public static String pronounSetPerm = "pronouns.set";
    public static String pronounClearPerm = "pronouns.clear";
    public static String pronounReloadPerm = "pronouns.reload";
    public static String pronounListPerm = "pronouns.list";
    public static String pronounGetPerm = "pronouns.get";
    public static String pronounAdminPerm = "pronouns.admin";
    public static String pronounAdminSet = "pronouns.admin.set";
    public static String pronounAdminClear = "pronouns.admin.clear";
    
    public static void checkIfPlayerAndPerms(CommandSender sender, String permission) {
        if (!(sender instanceof Player player)) {
            // Must be player error message
            SimplePronouns.getInstance().getLogger().info("Must be player error message");
            return;
        }
        if (!player.hasPermission(permission)) {
            // No permission error message
            SimplePronouns.getInstance().getLogger().info("No permission error message");
        }
    }
    
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
    
    public static Component parsePronouns(Player player, String string, Pronoun pronoun){
        if (pronoun == null) {
            return miniMessage.deserialize(string);
        }
        return miniMessage.deserialize(string,
                Placeholder.unparsed("label", convertToTitleCase(pronoun.getSubjective()) + "/" + convertToTitleCase(pronoun.getObjective())),
                Placeholder.unparsed("sub", convertToTitleCase(pronoun.getSubjective())),
                Placeholder.unparsed("obj", convertToTitleCase(pronoun.getObjective())),
                Placeholder.unparsed("pos", convertToTitleCase(pronoun.getPossessive())),
                Placeholder.unparsed("posadj", convertToTitleCase(pronoun.getPossessiveAdjective())),
                Placeholder.unparsed("ref", convertToTitleCase(pronoun.getReflexive())),
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
