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
import simplexity.simplepronouns.configs.ConfigLoader;
import simplexity.simplepronouns.configs.LocaleLoader;
import simplexity.simplepronouns.configs.PronounLoader;
import simplexity.simplepronouns.saving.PronounManager;

import java.util.List;

public class ClearAdminCommand extends SubCommand {
    
    MiniMessage miniMessage = SimplePronouns.getMiniMessage();
    
    public ClearAdminCommand(String permission, String label, String helpMessage) {
        super(permission, label, helpMessage);
    }
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendRichMessage(LocaleLoader.getInstance().getNoPermission());
            return false;
        }
        String playerString = args[2];
        Player player = Util.checkPlayer(playerString);
        if (player == null) {
            sender.sendMessage(miniMessage.deserialize(LocaleLoader.getInstance().getInvalidPlayer(),
                    Placeholder.unparsed("input", playerString)));
            return false;
        }
        Pronoun defaultPronoun = PronounLoader.pronouns.getOrDefault(ConfigLoader.getInstance().getDefaultPronouns(),
                null);
        if (defaultPronoun == null) {
            sender.sendRichMessage(LocaleLoader.getInstance().getDefaultPronoun());
            return false;
        }
        PronounManager.setSelectedPronoun(player, defaultPronoun);
        sender.sendMessage(Util.parsePronouns(player, LocaleLoader.getInstance().getPronounsAdminClear() +
                LocaleLoader.getInstance().getExampleSentence(), defaultPronoun));
        player.sendMessage(Util.parsePronouns(LocaleLoader.getInstance().getPronounsClear() +
                LocaleLoader.getInstance().getExampleSentence(), defaultPronoun));
        return true;
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
