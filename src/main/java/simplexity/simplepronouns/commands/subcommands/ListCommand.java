package simplexity.simplepronouns.commands.subcommands;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.SimplePronouns;
import simplexity.simplepronouns.Util;
import simplexity.simplepronouns.configs.LocaleLoader;
import simplexity.simplepronouns.configs.PronounLoader;

import java.util.List;

public class ListCommand extends SubCommand {
    
    public ListCommand(String permission, String label, String helpMessage) {
        super(permission, label, helpMessage);
    }
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendRichMessage(LocaleLoader.getInstance().getNoPermission());
            return false;
        }
        Component messageToSend = Component.empty();
        messageToSend = messageToSend.append(SimplePronouns.getMiniMessage().deserialize(LocaleLoader.getInstance().getListHeader()));
        for (Pronoun p : PronounLoader.pronouns.values()) {
            if (!p.isSelectable()) continue;
            messageToSend = messageToSend.append(Util.parsePronouns(LocaleLoader.getInstance().getListItem(), p));
        }
        sender.sendMessage(messageToSend);
        return true;
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        return List.of("");
    }
}
