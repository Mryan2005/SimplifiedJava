package top.mryan2005.simplifiedjava.SQLs.Exceptions;

import java.sql.SQLException;

public class SQLServerPrimaryKeyException extends SQLException {
  public SQLServerPrimaryKeyException(String message) {
      super(message);
  }
}
