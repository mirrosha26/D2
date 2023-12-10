import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connector implements DatabaseConnector {
    private static final String URL = "jdbc:h2:tcp://localhost/~/treeDB";
    private static final String USER = "userTree";
    private static final String PASSWORD = "pass";

    @Override
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
