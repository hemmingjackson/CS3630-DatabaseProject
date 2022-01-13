package Models;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Tickets.Main.connection;

public class User {

    private String id;
    private String name;
    private String lastname;
    private String email;
    private String phone;

    public User()
    {
        this("0", "NULL", "NULL", "NULL", "NULL");
    }


    public User(String id, String name, String lastname, String email, String phone) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static User validate(String email, String password) {
        User user = null;
        try {
            ResultSet rs = connection.execute("SELECT * FROM USER WHERE email = ? AND password = ?", new String[] {email, password}, true);
            rs.next();
            if (rs.isLast()) {
                user = new User();
                user.setId(rs.getString("idUser"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Database connection error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return user;
    }

    public static User getUserById(String idUser) {
        User user = null;
        try {
            ResultSet rs = connection.execute("SELECT * FROM USER WHERE idUser = ?", new String[] {idUser}, true);
            rs.next();
            if (rs.isLast()) {
                user = new User();
                user.setId(rs.getString("idUser"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Database connection error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return user;
    }
}
