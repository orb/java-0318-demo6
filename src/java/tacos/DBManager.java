package tacos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
    int num = 8;
    
    public DBManager() {
    }

    protected void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
        }
    }

    protected void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ignored) {
            }
        }
    }

    protected void close(ResultSet results) {
        if (results != null) {
            try {
                results.close();
            } catch (SQLException ignored) {
            }
        }
    }  
}
