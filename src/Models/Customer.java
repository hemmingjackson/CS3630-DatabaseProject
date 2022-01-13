package Models;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Tickets.Main.connection;

public class Customer {

    private String id;
    private String idUser;
    private String idDepartment;
    private String position;

    public Customer()
    {
        this("0", "NULL", "NULL", "NULL");
    }

    public Customer(String id, String idUser, String idDepartment, String position) {
        this.id = id;
        this.idUser = idUser;
        this.idDepartment = idDepartment;
        this.position = position;
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

    public String getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(String idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public static Customer getCustomerByUserId(String idUser) {
        Customer customer = null;
        try {
            ResultSet rs = connection.execute("SELECT * FROM CUSTOMER WHERE idUser = ?", new String[] {idUser}, true);
            if (rs.next() == false) {
                System.out.println("ResultSet in empty in Java");
            } else {
                do {
                    customer = new Customer();
                    customer.setId(rs.getString("idCustomer"));
                    customer.setIdUser(rs.getString("idUser"));
                    customer.setPosition(rs.getString("position"));
                    customer.setIdDepartment(rs.getString("idDepartment"));
                } while (rs.next());
            }
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Database connection error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return customer;
    }



}
