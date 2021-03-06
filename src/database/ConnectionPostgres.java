package database;

import java.sql.*;

public class ConnectionPostgres {

    private String url;
    private String username;
    private String password;
    private Connection connection;

    private static ConnectionPostgres database = null;

    private ConnectionPostgres() {
    }

    public boolean init(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
        if(!connect()){
            return false;
        }
        return true;
    }

    private boolean connect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return false;
        }
        System.out.println("PostgreSQL JDBC Driver successfully connected");
        connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return false;
        }
        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
            return false;
        }
        return true;
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnectionPostgres getInstance(){
        if(database == null){
            database = new ConnectionPostgres();
        }
        return database;
    }

}
