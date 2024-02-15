package simplexity.simplepronouns;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Util {
    private static MiniMessage miniMessage = SimplePronouns.getMiniMessage();
    
    public static String pronounBasePerm = "pronouns";
    public static String pronounSetPerm = "pronouns.set";
    public static String pronounRemovePerm = "pronouns.remove";
    public static String pronounReloadPerm = "pronouns.reload";
    public static String pronounListPerm = "pronouns.list";
    public static String pronounInfoPerm = "pronouns.info";
    public static String pronounOtherPerm = "pronouns.other";
    public static String pronounOtherSet = "pronouns.other.set";
    public static boolean checkIfPlayerAndPerms(CommandSender sender, String permission) {
        if (!(sender instanceof Player player)) {
            // Must be player error message
            SimplePronouns.getInstance().getLogger().info("Must be player error message");
            return false;
        }
        if (!player.hasPermission(permission)) {
            // No permission error message
            SimplePronouns.getInstance().getLogger().info("No permission error message");
            return false;
        }
        return true;
    }
    
    public static Component parsePronouns(String string, Pronoun pronoun){
        if (pronoun == null) {
            return miniMessage.deserialize(string);
        }
        return miniMessage.deserialize(string,
                Placeholder.parsed("sub", pronoun.getSubjective()),
                Placeholder.parsed("obj", pronoun.getObjective()),
                Placeholder.parsed("pos", pronoun.getPossessive()),
                Placeholder.parsed("posadj", pronoun.getPossessiveAdjective()),
                Placeholder.parsed("ref", pronoun.getReflexive()),
                Placeholder.parsed("label", pronoun.getLabel()));
    }

}
