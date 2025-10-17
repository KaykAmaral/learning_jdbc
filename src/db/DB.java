package src.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection(){
        if (conn == null){

            // U need this try cause getConnection can throw a SQLException

            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }

        }

        return conn;

    }

    public static void closeConnection(){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }


    // This Method loads the data of db.properties archive

    private static Properties loadProperties() {
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fis);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

}