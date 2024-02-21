package simplexity.simplepronouns.commands.subcommands.adminsubcommands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.SimplePronouns;
import simplexity.simplepronouns.Util;
import simplexity.simplepronouns.commands.subcommands.SubCommand;
import simplexity.simplepronouns.configs.LocaleLoader;
import simplexity.simplepronouns.configs.PronounLoader;
import simplexity.simplepronouns.saving.PronounManager;

import java.util.List;

public class SetAdminCommand extends SubCommand {
    
    MiniMessage miniMessage = SimplePronouns.getMiniMessage();
    
    public SetAdminCommand(String permission, String label, String helpMessage) {
        super(permission, label, helpMessage);
    }
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendRichMessage(LocaleLoader.getInstance().getNoPermission());
            return false;
        }
        if (args.length < 4) {
            sender.sendRichMessage(LocaleLoader.getInstance().getNotEnoughArguments());
            return false;
        }
        String pronounString = args[3];
        Pronoun pronoun = PronounManager.getPronounFromString(pronounString);
        if (pronoun == null) {
            sender.sendMessage(miniMessage.deserialize(LocaleLoader.getInstance().getNotConfigured(),
                    Placeholder.unparsed("input", pronounString)));
            return false;
        }
        Player player = Util.checkPlayer(args[2]);
        if (player == null) {
            sender.sendMessage(miniMessage.deserialize(LocaleLoader.getInstance().getInvalidPlayer(),
                    Placeholder.unparsed("input", args[2])));
            return false;
        }
        PronounManager.setSelectedPronoun(player, pronoun);
        sender.sendMessage(Util.parsePronouns(player, LocaleLoader.getInstance().getPronounsAdminSet() + LocaleLoader.getInstance().getExampleSentence(),
                pronoun));
        player.sendMessage(Util.parsePronouns(LocaleLoader.getInstance().getPronounsSet() + LocaleLoader.getInstance().getExampleSentence(), pronoun));
        return true;
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length != 4) {
            return null;
        } else if (!sender.hasPermission(getPermission())) {
            return null;
        }
        return PronounLoader.pronouns.keySet().stream().toList();
    }
}
