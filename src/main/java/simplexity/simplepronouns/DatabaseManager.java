package simplexity.simplepronouns;


import simplexity.simplepronouns.configs.ConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    
    public static void createDatabase() {
        String url = ConfigLoader.getInstance().getIp();
        try (Connection conn = DriverManager.getConnection(url,
                ConfigLoader.getInstance().getUsername(),
                ConfigLoader.getInstance().getPassword())) {
            if (conn != null) {
                System.out.println("Connected to the database!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    

}
