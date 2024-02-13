package simplexity.simplepronouns.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.PronounManager;
import simplexity.simplepronouns.SimplePronouns;
import simplexity.simplepronouns.Util;
import simplexity.simplepronouns.commands.subcommands.SubCommand;

import java.util.ArrayList;
import java.util.List;

public class PronounCommand implements TabExecutor {
    
    ArrayList<String> subCommandList = new ArrayList<>();
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length < 2) {
            sender.sendMessage("USE /pronouns set <thing>");
            // Error message
            return false;
        }
        Util.checkIfPlayerAndPerms(sender, "testperm");
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
        subCommandList.clear();
        for (SubCommand subCommand : SimplePronouns.subCommands.values()) {
            if (sender.hasPermission(subCommand.getPermission())) {
                subCommandList.add(subCommand.getLabel());
            }
        }
        return subCommandList;
    }
}
