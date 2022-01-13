/*
Creating program to establish a connection with mySQl database
 */
package Config;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private Connection connection;
    private String host;
    private String database;
    private String user;
    private String password;

    public Database(String host, String database, String user, String password) {
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public boolean connect (boolean verify) {
        boolean response = true;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + host + ":3306/" + database;
            System.out.println("URL:" + url);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully.");
        } catch (ClassNotFoundException | SQLException ex) {
            if(!verify) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            response = false;
        }
        return response;
    }

    public ResultSet execute (String query, String[] data,  boolean receive) throws SQLException {
        ResultSet rs = null;
        if (connection != null) {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            if (data != null)
                for(int i = 0; i < data.length; i++) {
                    preparedStmt.setString(i + 1, data[i]);
                }
            if (receive)
                rs = preparedStmt.executeQuery();
            else
                preparedStmt.execute();
        }
        return rs;
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Access denied for User: " + user + ", Password: " + password + ". Configure DB connection.");
        }
    }
}