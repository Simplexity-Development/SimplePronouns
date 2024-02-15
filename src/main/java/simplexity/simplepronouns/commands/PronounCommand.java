package simplexity.simplepronouns.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import simplexity.simplepronouns.SimplePronouns;
import simplexity.simplepronouns.commands.subcommands.SubCommand;
import simplexity.simplepronouns.configs.LocaleLoader;

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
            sender.sendRichMessage(LocaleLoader.getInstance().getNotEnoughArguments());
            return false;
        }
        String subCommand = args[0];
        if (!subCommands.containsKey(subCommand)) {
            sender.sendRichMessage(LocaleLoader.getInstance().getSyntaxError());
            return false;
        }
        if (!sender.hasPermission(subCommands.get(subCommand).getPermission())) {
            sender.sendRichMessage(LocaleLoader.getInstance().getNoPermission());
            return false;
        }
        subCommands.get(subCommand).onCommand(sender, command, s, args);
        return true;
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        subCommandList.clear();
        if (args.length >= 2) {
            String subCommand = args[0].toLowerCase();
            if (subCommands.containsKey(subCommand)) {
                return subCommands.get(subCommand).onTabComplete(sender, command, s, args);
            }
        }
        for (SubCommand subCommand : SimplePronouns.subCommands.values()) {
            if (sender.hasPermission(subCommand.getPermission())) {
                subCommandList.add(subCommand.getLabel());
            }
        }
        return subCommandList;
    }
}
