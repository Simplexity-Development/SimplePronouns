package simplexity.simplepronouns.commands.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.PronounManager;
import simplexity.simplepronouns.Util;

import java.util.ArrayList;
import java.util.List;

public class SetCommand extends SubCommand {
    
    public SetCommand(String permission, String label, String helpMessage) {
        super(permission, label, helpMessage);
    }
    private ArrayList<String> pronounList = new ArrayList<>();
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
            // Error message
            return false;
        }
        PronounManager.setSelectedPronoun(player, pronounObject);
        player.sendMessage("Selected pronoun: " + pronounObject.getLabel());
        return false;
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        pronounList.clear();
        if (!sender.hasPermission(getPermission())) {
            return pronounList;
        } else {
            for (Pronoun pronoun : PronounManager.pronouns.values()) {
                pronounList.add(pronoun.getLabel());
            }
            return pronounList;
        }
    }
}
