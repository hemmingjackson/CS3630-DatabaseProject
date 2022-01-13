package Models;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Tickets.Main.connection;

public class Employee {
    private String id;
    private String idUser;
    private Integer isAdmin;

    public Employee()
    {
        this("0", "NULL", 0);
    }

    public Employee(String id, String idUser, Integer isAdmin) {
        this.id = id;
        this.idUser = idUser;
        this.isAdmin = isAdmin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public static Employee getEmployeeByUserId(String idUser) {
        Employee employee = null;
        try {
            ResultSet rs = connection.execute("SELECT * FROM EMPLOYEE WHERE idUser = ?", new String[] {idUser}, true);
            rs.next();
            employee = new Employee();
            employee.setId(rs.getString("idEmployee"));
            employee.setIdUser(rs.getString("idUser"));
            employee.setIsAdmin(Integer.parseInt(rs.getString("isAdmin")));
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Database connection error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return employee;
    }
}
