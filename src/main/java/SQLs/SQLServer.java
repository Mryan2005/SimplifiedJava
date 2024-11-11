package SQLs;

import java.sql.*;

public class SQLServer {
    public Connection con;
    public String ip;
    public String port;
    public String database;
    public String username;
    public String password;

    public void ConnectToSQLServer(String inputIp, String inputPort, String inputDatabase, String inputUsername, String inputPassword, boolean encrypt) throws ClassNotFoundException, SQLException {
        Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection("jdbc:sqlserver://" + inputIp + ":" + inputPort + ";databaseName=" + inputDatabase + ";user=" + inputUsername + ";password=" + inputPassword + ";encrypt=" + encrypt + ";");
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

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        SQLServer sqlServer = new SQLServer();
        sqlServer.ConnectToSQLServer("localhost", "1433", "wuzhouDict", "sa", "123456", false);
        sqlServer.runSQL("INSERT INTO dbo.[users] (id, username, password, name, role) VALUES (1, 'admin', 'password', 'admin', '1')");
        ResultSet rs = sqlServer.runSQL("SELECT * FROM dbo.[users]");
        while(rs.next()) {
            System.out.println(rs.getString("name"));
        }
        sqlServer.CloseConnection();
    }
}
