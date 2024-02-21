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
    
    private static final MiniMessage miniMessage = SimplePronouns.getMiniMessage();
    
    public HelpCommand(String permission, String label, String helpMessage) {
        super(permission, label, helpMessage);
    }
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendRichMessage(LocaleLoader.getInstance().getNoPermission());
            return false;
        }
        Component helpMessage = Component.empty();
        for (SubCommand subCommand : SimplePronouns.subCommands.values()) {
            if (!sender.hasPermission(subCommand.getPermission())) continue;
            helpMessage = helpMessage.append(miniMessage.deserialize(subCommand.getHelpMessage()));
        }
        for (SubCommand adminSubCommand : SimplePronouns.adminSubCommands.values()) {
            if (!sender.hasPermission(adminSubCommand.getPermission())) continue;
            helpMessage = helpMessage.append(miniMessage.deserialize(adminSubCommand.getHelpMessage()));
        }
        if (helpMessage.equals(Component.empty())) {
            sender.sendRichMessage(LocaleLoader.getInstance().getHelpHeader() + LocaleLoader.getInstance().getNoCommands());
            return false;
        }
        sender.sendMessage(miniMessage.deserialize(LocaleLoader.getInstance().getHelpHeader()).append(helpMessage));
        return true;
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        return List.of("");
    }
}
