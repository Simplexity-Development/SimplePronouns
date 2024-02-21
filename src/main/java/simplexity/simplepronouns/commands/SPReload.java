package simplexity.simplepronouns.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import simplexity.simplepronouns.SimplePronouns;
import simplexity.simplepronouns.Util;
import simplexity.simplepronouns.configs.ConfigLoader;
import simplexity.simplepronouns.configs.LocaleLoader;
import simplexity.simplepronouns.configs.PronounLoader;

public class SPReload implements CommandExecutor {
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!sender.hasPermission(Util.reloadPerm)) {
            sender.sendRichMessage(LocaleLoader.getInstance().getNoPermission());
            return false;
        }
        SimplePronouns.getInstance().reloadConfig();
        ConfigLoader.getInstance().loadConfig();
        LocaleLoader.getInstance().loadLocale();
        PronounLoader.getInstance().loadPronouns();
        sender.sendRichMessage(LocaleLoader.getInstance().getCommandReloaded());
        return true;
    }
}
