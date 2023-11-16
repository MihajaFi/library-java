import Configuration.ConnectionConfig;

import java.sql.Connection;
import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        test() ;
    }
    public static Connection test(){
        ConnectionConfig connectionConfig = new ConnectionConfig();
        try(Connection connection = connectionConfig.createConnection()){
            System.out.println("Connection ok");
        } catch (SQLException e) {
            throw new RuntimeException("Error ");
        }
        return null ;
    }
}