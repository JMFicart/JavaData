package data_access;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/dbslide";
    private static final String USER = "root";
    private static final String PSWD = "";

    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection (URL, USER, PSWD);
        return getDataSource("dbslide").getConnection();
    }

    public static DataSource getDataSource(String dbName){
        switch (dbName){
            case "dbslide":
                return getDBSlideDataSource();
            case "dbslide_bis":
                return getDBSlideBisDataSource();
            case "dbslide_ter":
                return null;
            default:
                throw new IllegalArgumentException("Wrong name");
        }
    }

    public static DataSource getDBSlideDataSource(){
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName("localhost");
        ds.setPort(3306);
        ds.setDatabaseName("dbslide");
        ds.setUser(USER);
        ds.setPassword(PSWD);
        return ds;
    }

    public static DataSource getDBSlideBisDataSource(){
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName("localhost");
        ds.setPort(3306);
        ds.setDatabaseName("dbslide2");
        ds.setUser(USER);
        ds.setPassword(PSWD);
        return ds;
    }
}
