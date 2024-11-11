import SQLs.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class SQL {
    public Connection con;
    public String type;
    public String ip;
    public String port;
    public String username;
    public String password;
    public String databaseName;
    public String connectionUrl;
    public Connection ConnectToSQL(String type, String ip, String port, String username, String password, String databaseName) {
        this.type = type;
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
        this.databaseName = databaseName;
        if("SQL Server".matches(type)) {
            try {
                SQLServer sql = new SQLServer();
                sql.ConnectToSQLServer(ip, port, databaseName, username, password);
                System.out.println("连接成功！");
                con = sql.getSQLer();
            } catch (Exception e) {
                System.out.println("连接数据库时发生错误！");
                System.out.println(e);
            }
        } else if("MySQL".matches(type)) {
            try {
                MySQL sql = new MySQL(ip, port, username, password, databaseName);
                System.out.println("连接成功！");
                con = sql.getSQLer();
            } catch (Exception e) {
                System.out.println("连接数据库时发生错误！");
                System.out.println(e);
            }
        }
        return con;
    }

    public Connection ConnectToSQL(String type, String databaseName) {
        this.type = type;
        this.databaseName = databaseName;
        if("SQLite".matches(type)) {
            try {
                SQLite sql = new SQLite(databaseName);
                System.out.println("连接成功！");
                con = sql.getSQLer();
            } catch (Exception e) {
                System.out.println("连接数据库时发生错误！");
                System.out.println(e);
            }
        }
        return con;
    }

    public void CloseConnection() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("关闭数据库连接时发生错误！");
            System.out.println(e);
        }
    }

    public ResultSet runSQL(String sql) {
        try {
            Statement stmt = con.createStatement();
            if(stmt.execute(sql)) {
                return stmt.getResultSet();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("执行SQL语句时发生错误！");
            System.out.println(e);
            return null;
        }
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
}
