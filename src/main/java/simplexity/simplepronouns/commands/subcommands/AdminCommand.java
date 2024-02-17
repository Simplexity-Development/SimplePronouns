package simplexity.simplepronouns.commands.subcommands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import simplexity.simplepronouns.SimplePronouns;
import simplexity.simplepronouns.Util;
import simplexity.simplepronouns.configs.LocaleLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminCommand extends SubCommand {
    
    ArrayList<String> adminSubCommandList = new ArrayList<>();
    HashMap<String, SubCommand> adminSubCommands = SimplePronouns.adminSubCommands;
    MiniMessage miniMessage = SimplePronouns.getMiniMessage();
    
    public AdminCommand(String permission, String label, String helpMessage) {
        super(permission, label, helpMessage);
    }
    
    // /pronouns admin [set|clear|get] <player> <pronoun>
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendRichMessage(LocaleLoader.getInstance().getNoPermission());
            return false;
        }
        if (args.length < 3) {
            sender.sendRichMessage(LocaleLoader.getInstance().getNotEnoughArguments());
            return false;
        }
        String playerString = args[2];
        Player player = Util.checkPlayer(playerString);
        if (player == null) {
            sender.sendMessage(miniMessage.deserialize(LocaleLoader.getInstance().getInvalidPlayer(),
                    Placeholder.unparsed("input", playerString)));
            return false;
        }
        String action = args[1];
        if (!adminSubCommands.containsKey(action)) {
            sender.sendRichMessage(LocaleLoader.getInstance().getSyntaxError());
            return false;
        }
        if (!sender.hasPermission(adminSubCommands.get(action).getPermission())) {
            sender.sendRichMessage(LocaleLoader.getInstance().getNoPermission());
            return false;
        }
        adminSubCommands.get(action).onCommand(sender, command, s, args);
        return false;
    }
    
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        adminSubCommandList.clear();
        if (args.length == 2) {
            for (SubCommand adminSubCommand : adminSubCommands.values()) {
                if (!sender.hasPermission(adminSubCommand.getPermission())) continue;
                adminSubCommandList.add(adminSubCommand.getLabel());
            }
        }
        if (args.length == 3) {
            return null;
        }
        if (args.length == 4) {
            if (adminSubCommands.containsKey(args[1])) {
                return adminSubCommands.get(args[1]).onTabComplete(sender, command, s, args);
            }
        }
        return adminSubCommandList;
    }
}
