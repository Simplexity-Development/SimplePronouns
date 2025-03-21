package simplexity.simplepronouns.saving;


import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.SimplePronouns;
import simplexity.simplepronouns.configs.ConfigLoader;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DatabaseManager extends SaveHandler {

    Connection connection;
    String dbName;
    Logger logger = SimplePronouns.getInstance().getLogger();
    String tableName = "player_pronouns";
    boolean enabled = true;

    public void init() {
        dbName = ConfigLoader.getInstance().getName();
        String url = "jdbc:mysql://" + ConfigLoader.getInstance().getIp() + "/" + dbName;
        String user = ConfigLoader.getInstance().getUsername();
        String pass = ConfigLoader.getInstance().getPassword();

        try {
            connection = DriverManager.getConnection(url, user, pass);
            logger.info("Established connection to the database.");

            try (Statement statement = connection.createStatement()) {
                statement.execute("""
                        CREATE TABLE IF NOT EXISTS player_pronouns (
                            id VARCHAR(36) PRIMARY KEY,
                            subjective VARCHAR(255),
                            objective VARCHAR(255),
                            possessive VARCHAR(255),
                            possessive_adj VARCHAR(255),
                            reflexive VARCHAR(255)
                        );""");
            }

        } catch (SQLException e) {
            if (e.getSQLState().equals("42000")) {
                logger.severe("Could not find database " + dbName + ", please create this database or fix the name to use MySQL.");
            }
            else {
                e.printStackTrace();
            }
            enabled = false;
        }
    }

    public boolean setPronoun(OfflinePlayer player, Pronoun pronoun) {
        if (!isEnabled()) return false;

        if (pronoun == null) {
            String sqlStatement = "DELETE FROM " + tableName + " WHERE id = ?;";
            try (PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
                statement.setString(1, player.getUniqueId().toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        String sqlStatement = "REPLACE INTO " + tableName +
                " (id, subjective, objective, possessive, possessive_adj, reflexive) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            statement.setString(1, player.getUniqueId().toString());
            statement.setString(2, pronoun.getSubjective());
            statement.setString(3, pronoun.getObjective());
            statement.setString(4, pronoun.getPossessive());
            statement.setString(5, pronoun.getPossessiveAdjective());
            statement.setString(6, pronoun.getReflexive());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public @Nullable Pronoun getPronoun(OfflinePlayer player) {
        Pronoun pronoun = null;
        if (!isEnabled()) return pronoun;
        String sqlStatement = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            statement.setString(1, player.getUniqueId().toString());
            try (ResultSet result = statement.executeQuery()){
                if (result.next()) {
                    pronoun = new Pronoun(
                            result.getString("subjective"),
                            result.getString("objective"),
                            result.getString("possessive"),
                            result.getString("possessive_adj"),
                            result.getString("reflexive")
                            );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pronoun;
    }

    public void close() {
        if (connection == null) return;
        try {
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private boolean checkForDatabase(Connection connection, String dbName) {
        boolean exists = false;
        try (ResultSet resultSet = connection.getMetaData().getCatalogs()) {
            while (resultSet.next()) {
                String currentDbName = resultSet.getString(1);
                if (currentDbName.equals(dbName)) {
                    exists = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    private boolean isEnabled() {
        if (!enabled) {
            logger.severe("This plugin has disabled MySQL due to invalid configuration, please check configuration and reload.");
        }
        return enabled;
    }
}
