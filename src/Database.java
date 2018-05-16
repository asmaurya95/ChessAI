
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 *
 * @author ashutosh
 */
public class Database {

    static String url = "jdbc:sqlite:/home/ashutosh/NetBeansProjects/ChessAI/mydatabase.db";
    static String sql;
    static Connection conn;
    static Statement stmt;
    static PreparedStatement pstmt;

    public static void createDatabase() {
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Connection Established");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTable() {
        // SQL statement for creating a new table
        sql = "CREATE TABLE IF NOT EXISTS games (\n"
                + "gameNumber integer PRIMARY KEY AUTOINCREMENT NOT NULL, \n"
                + "playername text NOT NULL, \n"
                + "winner text NOT NULL\n"
                + ");";
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert(String name, String result) {
        // SQL statement for inserting data into the SQLite Database
        sql = "INSERT INTO games(playername,winner) VALUES(?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, result);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
