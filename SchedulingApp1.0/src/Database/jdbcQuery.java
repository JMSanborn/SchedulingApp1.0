package Database;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jdbcQuery {
    private static PreparedStatement preparedStatement;

    /**
     * @param connection current connection to mySQL database
     * @param sqlStatement raw sql statement (ex. insert, update, delete)
     * @throws SQLException if exception has occurred
     */
    public static void setPreparedStatement(Connection connection, String sqlStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sqlStatement);
    }

    // return statement object

    /**
     * @return the prepared statement created
     */
    public static PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

}


