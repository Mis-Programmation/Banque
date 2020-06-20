package helpers;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

public class DatabaseHelper {

    private static Connection connection = null;
    private static final String url      = "jdbc:mysql://localhost:3306/banque?serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "";


    /**
     * permet de faire la connexition a la base de donnee
     * @return Connection
     */
    public static Connection getConnection()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if(null == connection){
                connection = DriverManager.getConnection(url,username,password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static void closeConnection(){
        try {
            if(connection != null && !connection.isClosed()){
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
