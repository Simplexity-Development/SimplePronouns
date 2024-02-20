package simplexity.simplepronouns.saving;


import org.bukkit.OfflinePlayer;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.configs.ConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager extends SaveHandler {

    public void init() {
        String url = "jdbc:mysql://" + ConfigLoader.getInstance().getIp();
        String user = ConfigLoader.getInstance().getUsername();
        String pass = ConfigLoader.getInstance().getPassword();

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            if (conn != null) {
                System.out.println("Connected to the database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean setPronoun(OfflinePlayer player, String pronoun) {
        return false; // TODO: do it
    }

    public Pronoun getPronoun(OfflinePlayer player) {
        return null; // TODO: do it
    }
    
    
    

}
