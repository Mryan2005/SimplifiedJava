package top.mryan2005.simplifiedjava.SQLs.Exceptions;

import java.sql.SQLException;

public class SQLServerNotNULLException extends SQLException {
    public SQLServerNotNULLException(String message) {
        super(message);
    }
}
