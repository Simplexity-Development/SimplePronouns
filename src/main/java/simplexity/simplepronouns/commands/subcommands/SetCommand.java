package simplexity.simplepronouns.commands.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.PronounManager;
import simplexity.simplepronouns.Util;
import simplexity.simplepronouns.configs.LocaleLoader;

import java.util.ArrayList;
import java.util.List;

public class SetCommand extends SubCommand {
    
    public SetCommand(String permission, String label, String helpMessage) {
        super(permission, label, helpMessage);
    }
    private final ArrayList<String> pronounList = new ArrayList<>();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length < 2) {
            // Error message
            return false;
        }
        Util.checkIfPlayerAndPerms(sender, getPermission());
        Player player = (Player) sender;
        String selectedPronoun = args[1];
        Pronoun pronounObject = PronounManager.pronouns.get(selectedPronoun.toLowerCase());
        if (pronounObject == null) {
            player.sendMessage("Pronoun " + selectedPronoun + " does not exist");
            return false;
        }
        PronounManager.setSelectedPronoun(player, pronounObject);
        player.sendMessage("Selected pronoun: " + pronounObject.getLabel());
        player.sendMessage(Util.parsePronouns(LocaleLoader.getInstance().getPronounsSet() + LocaleLoader.getInstance().getExampleSentence(), pronounObject));
        return false;
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        pronounList.clear();
        if (!sender.hasPermission(getPermission()) || args.length > 2) {
            return pronounList;
        } else {
            return PronounManager.pronounLabels;
        }
    }
}
