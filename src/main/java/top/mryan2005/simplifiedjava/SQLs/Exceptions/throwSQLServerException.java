package top.mryan2005.simplifiedjava.SQLs.Exceptions;

import java.sql.SQLException;

public class throwSQLServerException {
    public static void throwExeption(int ErrorCode, String message) throws SQLException {
        switch (ErrorCode) {
            case 4060:
                throw new SQLServerDatabaseNotFoundException(message);
            default:
                throw new SQLException(message);
        }
    }
}
