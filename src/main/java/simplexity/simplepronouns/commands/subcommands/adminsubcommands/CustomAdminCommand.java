package simplexity.simplepronouns.commands.subcommands.adminsubcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.Util;
import simplexity.simplepronouns.commands.subcommands.SubCommand;
import simplexity.simplepronouns.configs.ConfigLoader;
import simplexity.simplepronouns.configs.LocaleLoader;
import simplexity.simplepronouns.saving.PronounManager;

import java.util.List;

public class CustomAdminCommand extends SubCommand {
    // pronoun admin custom <player> {sub} {obj} {pos} {posadj} {ref}
    
    public CustomAdminCommand(String permission, String label, String helpMessage) {
        super(permission, label, helpMessage);
    }
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendRichMessage(LocaleLoader.getInstance().getNoPermission());
            return false;
        }
        if (!ConfigLoader.getInstance().allowCustomPronouns()){
            sender.sendRichMessage(LocaleLoader.getInstance().getSyntaxError());
            return false;
        }
        if (args.length < 8) {
            sender.sendRichMessage(LocaleLoader.getInstance().getNotEnoughArguments());
            return false;
        }
        String playerString = args[2];
        Player player = Util.checkPlayer(playerString);
        if (player == null) {
            sender.sendRichMessage(LocaleLoader.getInstance().getInvalidPlayer());
            return false;
        }
        String subjective = args[3];
        String objective = args[4];
        String possessive = args[5];
        String possessiveAdjective = args[6];
        String reflexive = args[7];
        Pronoun customPronoun = new Pronoun(subjective, objective, possessive, possessiveAdjective, reflexive);
        PronounManager.setSelectedPronoun(player, customPronoun);
        player.sendMessage(Util.parsePronouns(LocaleLoader.getInstance().getPronounsSet() + LocaleLoader.getInstance().getExampleSentence(), customPronoun));
        return true;
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 4) {
            return List.of("Subjective");
        }
        if (args.length == 5) {
            return List.of("Objective");
        }
        if (args.length == 6) {
            return List.of("Possessive");
        }
        if (args.length == 7) {
            return List.of("Possessive-Adjective");
        }
        if (args.length == 8) {
            return List.of("Reflexive");
        }
        return null;
    }
}
