package simplexity.simplepronouns.commands.subcommands;

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
import simplexity.simplepronouns.configs.LocaleLoader;
import simplexity.simplepronouns.saving.PronounManager;

import java.util.List;

public class GetCommand extends SubCommand {
    
    MiniMessage miniMessage = SimplePronouns.getMiniMessage();
    
    public GetCommand(String permission, String label, String helpMessage) {
        super(permission, label, helpMessage);
    }
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendRichMessage(LocaleLoader.getInstance().getNoPermission());
            return false;
        }
        if (args.length < 2) {
            return getOwnPronouns(sender);
        }
        return getOtherPronouns(sender, args);
    }
    
    private boolean getOwnPronouns(CommandSender sender){
        if (!(sender instanceof Player playerSender)) {
            sender.sendRichMessage(LocaleLoader.getInstance().getOnlyAPlayer());
            return false;
        }
        sender.sendMessage(Util.parsePronouns(playerSender, (LocaleLoader.getInstance().getPronounsGet() +
                LocaleLoader.getInstance().getListItem()), PronounManager.getSelectedPronoun(playerSender)));
        return true;
    }
    
    private boolean getOtherPronouns(CommandSender sender, String[] args) {
        String playerString = args[1];
        Player player = Util.checkPlayer(playerString);
        if (player == null) {
            sender.sendMessage(miniMessage.deserialize(LocaleLoader.getInstance().getInvalidPlayer(),
                    Placeholder.unparsed("input", playerString)));
            return false;
        }
        Pronoun pronouns = PronounManager.getSelectedPronoun(player);
        sender.sendMessage(Util.parsePronouns(player, (LocaleLoader.getInstance().getUserPronouns() +
                LocaleLoader.getInstance().getListItem()), pronouns));
        return true;
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
