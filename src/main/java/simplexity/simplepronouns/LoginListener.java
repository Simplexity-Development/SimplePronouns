package simplexity.simplepronouns;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.persistence.PersistentDataType;
import simplexity.simplepronouns.configs.LocaleLoader;

public class LoginListener implements Listener {
    
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    
    public void onLogin(PlayerLoginEvent loginEvent) {
        Player player = loginEvent.getPlayer();
        Bukkit.getScheduler().runTaskLater(SimplePronouns.getInstance(), () -> {
            String pronoun = player.getPersistentDataContainer().getOrDefault(Util.pronounsKey,
                    PersistentDataType.STRING, "");
            SimplePronouns.getInstance().getLogger().info("Pronoun: " + pronoun);
            Pronoun userPronouns = PronounManager.pronouns.get(pronoun);
            SimplePronouns.getInstance().getLogger().info("Pronoun: " + userPronouns);
            player.sendMessage(Util.parsePronouns((LocaleLoader.getInstance().getPronounsSet() + "\n" + LocaleLoader.getInstance().getExampleSentence() ),userPronouns));
        }, 10);
        
    }
    
}
