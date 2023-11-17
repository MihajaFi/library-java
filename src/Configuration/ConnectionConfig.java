package Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfig {
    private String url;
    private String username;
    private String password;


    public ConnectionConfig() {
        this.url = "jdbc:postgresql://localhost/library_management";
        this.username = "postgres";
        this.password = "12345678";
    }

    public Connection createConnection(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    this.url, this.username, this.password
            );

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
