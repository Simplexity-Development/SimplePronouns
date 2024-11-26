package simplexity.simplepronouns.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.SimplePronouns;
import simplexity.simplepronouns.configs.ConfigLoader;
import simplexity.simplepronouns.configs.LocaleLoader;
import simplexity.simplepronouns.saving.PlayerPDC;
import simplexity.simplepronouns.saving.PronounManager;

public class PronounTransferListener implements Listener {

    PlayerPDC pdcSaving = new PlayerPDC();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (ConfigLoader.getInstance().getSaveType().equalsIgnoreCase("pdc")) return;
        Player player = event.getPlayer();
        Pronoun pronoun = pdcSaving.getPronoun(player);
        if (pronoun == null) return;
        pdcSaving.setPronoun(player, null);
        PronounManager.setSelectedPronoun(player, pronoun);
        String message = LocaleLoader.getInstance().getTransferPdc();
        message = message.replaceAll("<player>", player.getName());
        SimplePronouns.getInstance().getLogger().info(message);
    }

}
