package simplexity.simplepronouns.commands.subcommands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import simplexity.simplepronouns.SimplePronouns;
import simplexity.simplepronouns.configs.LocaleLoader;

import java.util.List;

public class HelpCommand extends SubCommand {
    
    private static MiniMessage miniMessage = SimplePronouns.getMiniMessage();
    
    public HelpCommand(String permission, String label, String helpMessage) {
        super(permission, label, helpMessage);
    }
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendRichMessage(LocaleLoader.getInstance().getNoPermission());
            return false;
        }
        Component helpMessage = miniMessage.deserialize(LocaleLoader.getInstance().getHelpHeader());
        for (SubCommand subCommand : SimplePronouns.subCommands.values()) {
            helpMessage = helpMessage.append(miniMessage.deserialize(subCommand.getHelpMessage()));
        }
        sender.sendMessage(helpMessage);
        return true;
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        return List.of("");
    }
}
