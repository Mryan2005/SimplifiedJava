package top.mryan2005.simplifiedjava.SQLs.Exceptions;

import java.sql.SQLException;

public class SQLServerDatabaseNotFoundException extends SQLException {
    public SQLServerDatabaseNotFoundException(String message) {
        super(message);
    }
}
