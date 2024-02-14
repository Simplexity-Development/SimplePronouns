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
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class PronounCommand implements TabExecutor {
    
    ArrayList<String> subCommandList = new ArrayList<>();
    Logger logger = SimplePronouns.getInstance().getLogger();
    HashMap<String, SubCommand> subCommands = SimplePronouns.subCommands;
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length < 1) {
            logger.info("/pronoun command sent with no arguments, returning");
            return false;
        }
        String subCommand = args[0];
        if (!subCommands.containsKey(subCommand)) {
            logger.info("Subcommand " + subCommand + " does not exist, returning");
            return false;
        }
        if (sender.hasPermission(subCommands.get(subCommand).getPermission()))
            logger.info("Subcommand " + subCommand + " is permitted");
        else {
            logger.info("Subcommand " + subCommand + " is not permitted, returning");
            return false;
        }
        subCommands.get(subCommand).onCommand(sender, command, s, args);
        return true;
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        subCommandList.clear();
        if (args.length >= 2) {
            return null;
        }
        for (SubCommand subCommand : SimplePronouns.subCommands.values()) {
            if (sender.hasPermission(subCommand.getPermission())) {
                subCommandList.add(subCommand.getLabel());
            }
        }
        return subCommandList;
    }
}
