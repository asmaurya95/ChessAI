
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ashutosh
 */
public class Database {
        public static void createNewDatabase() {
        String url = "jdbc:sqlite:/home/ashutosh/NetBeansProjects/ChessAI/mydatabase.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Connection Established");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
