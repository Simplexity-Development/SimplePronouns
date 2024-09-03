package simplexity.simplepronouns.commands.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.Util;
import simplexity.simplepronouns.configs.LocaleLoader;
import simplexity.simplepronouns.saving.PronounManager;

import java.util.List;

public class CustomPronounCommand extends SubCommand {
    
    public CustomPronounCommand(String permission, String label, String helpMessage) {
        super(permission, label, helpMessage);
    }
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendRichMessage(LocaleLoader.getInstance().getNoPermission());
            return false;
        }
        if (!(sender instanceof Player player)) {
            sender.sendRichMessage(LocaleLoader.getInstance().getOnlyAPlayer());
            return false;
        }
        if (args.length < 6) {
            sender.sendRichMessage(LocaleLoader.getInstance().getNotEnoughArguments());
            return false;
        }
        String subjective = args[1];
        String objective = args[2];
        String possessive = args[3];
        String possessiveAdjective = args[4];
        String reflexive = args[5];
        Pronoun customPronoun = new Pronoun(subjective, objective, possessive, possessiveAdjective, reflexive);
        PronounManager.setSelectedPronoun(player, customPronoun);
        player.sendMessage(Util.parsePronouns(LocaleLoader.getInstance().getPronounsSet() + LocaleLoader.getInstance().getExampleSentence(), customPronoun));
        return true;
    }
    
    // pronoun admin set custom <player> {sub} {obj} {pos} {posadj} {ref}
    // pronoun set custom {sub} {obj} {pos} {posadj} {ref}
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 2) {
            return List.of("Subjective");
        }
        if (args.length == 3) {
            return List.of("Objective");
        }
        if (args.length == 4) {
            return List.of("Possessive");
        }
        if (args.length == 5) {
            return List.of("Possessive-Adjective");
        }
        if (args.length == 6) {
            return List.of("Reflexive");
        }
        return null;
    }
    
}
