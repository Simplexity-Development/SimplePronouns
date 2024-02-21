package simplexity.simplepronouns.saving;


import org.bukkit.OfflinePlayer;
import simplexity.simplepronouns.Pronoun;
import simplexity.simplepronouns.SimplePronouns;
import simplexity.simplepronouns.configs.ConfigLoader;

import java.sql.*;
import java.util.logging.Logger;

public class DatabaseManager extends SaveHandler {

    Connection connection;

    public void init() {
        String url = "jdbc:mysql://" + ConfigLoader.getInstance().getIp();
        String user = ConfigLoader.getInstance().getUsername();
        String pass = ConfigLoader.getInstance().getPassword();
        String dbName = ConfigLoader.getInstance().getName();
        Logger logger = SimplePronouns.getInstance().getLogger();

        try {
            connection = DriverManager.getConnection(url, user, pass);
            logger.info("Established connection to the database.");
            if (checkForDatabase(connection, dbName)) {
                logger.info("Database " + dbName + " found!");
            }
            else {
                logger.severe("Could not find database " + dbName + ", please create this database or resolve the name to use MySQL.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean setPronoun(OfflinePlayer player, Pronoun pronoun) {
        return false; // TODO: do it
    }

    public Pronoun getPronoun(OfflinePlayer player) {
        return null; // TODO: do it
    }
    
    private boolean checkForDatabase(Connection connection, String dbName) throws SQLException {
        boolean exists = false;
        ResultSet resultSet = connection.getMetaData().getCatalogs();
        while (resultSet.next()) {
            String currentDbName = resultSet.getString(1);
            if (currentDbName.equals(dbName)) {
                exists = true;
                break;
            }
        }
        resultSet.close();
        return exists;
    }
}
