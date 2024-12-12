package top.mryan2005.simplifiedjava.SQLs;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SQLServer {
    public Connection con;
    public String ip;
    public String port;
    public String database;

    public Connection ConnectToSQLServer(String inputIp, String inputPort, String inputDatabase, String inputUsername, String inputPassword, boolean encrypt) throws ClassNotFoundException, SQLException {
        Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection("jdbc:sqlserver://" + inputIp + ":" + inputPort + ";databaseName=" + inputDatabase + ";user=" + inputUsername + ";password=" + inputPassword + ";encrypt=" + encrypt + ";");
        ip = inputIp;
        port = inputPort;
        database = inputDatabase;
        return con;
    }

    public Connection ConnectToSQLServer(String inputIp, String inputPort, String inputUsername, String inputPassword, boolean encrypt) throws ClassNotFoundException, SQLException {
        Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection("jdbc:sqlserver://" + inputIp + ":" + inputPort + ";user=" + inputUsername + ";password=" + inputPassword + ";"+ "encrypt=" + encrypt + ";");
        return con;
    }

    public void CloseConnection() throws SQLException {
        con.close();
    }

    public ResultSet runSQL(String sql) throws SQLException {
        Statement stmt = con.createStatement();
        if(stmt.execute(sql)) {
            return stmt.getResultSet();
        } else {
            return null;
        }
    }

    public Connection getSQLer() {
        return con;
    }

    public HashMap<Object, ArrayList<Object>> runSQL(String sql, String primaryKey) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            HashMap<Object, ArrayList<Object>> result = new HashMap<>();
            while(rs.next()) {
                ArrayList<Object> row = new ArrayList<>();
                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getObject(i));
                }
                result.put(rs.getObject(primaryKey), row);
            }
            return result;
        } catch (Exception e) {
            System.out.println("执行SQL语句时发生错误！");
            System.out.println(e);
            return null;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        SQLServer sqlServer = new SQLServer();
        sqlServer.ConnectToSQLServer("localhost", "1433", "sa", "123456", false);
        sqlServer.CloseConnection();
        sqlServer.ConnectToSQLServer("localhost", "1433", "master", "sa", "123456", false);
        sqlServer.CloseConnection();
    }
}
