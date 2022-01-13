package Controllers.Login;

import Controllers.Dashboard.CDashboardAdmin;
import Controllers.Dashboard.CDashboardCustomer;
import Models.Customer;
import Models.Employee;
import Models.User;
import Frames.UILogin;

import javax.swing.*;

import static Tickets.Main.user;
import static Tickets.Main.customer;
import static Tickets.Main.employee;

public class CLogin implements ILogin {

    private UILogin window;

    public CLogin() { this.window = new UILogin(this); }

    @Override
    public void validate(JTextField txtEmail, JPasswordField txtPassword) {
        User u = User.validate(txtEmail.getText(), String.valueOf(txtPassword.getPassword()));
        if (u != null) {
            user = u;
            if (Customer.getCustomerByUserId(user.getId()) != null) {
                customer = Customer.getCustomerByUserId(user.getId());
                new CDashboardCustomer();
            } else if (Employee.getEmployeeByUserId(user.getId()) != null) {
                employee = Employee.getEmployeeByUserId(user.getId());
                boolean isAdmin = employee.getIsAdmin() == 1;
                if (isAdmin) {
                    System.out.println("It is here");
                    new CDashboardAdmin();
                } else {
                    // TODO: Employee Dashboard
                }
            }
            window.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Email or Password are incorrect.", "Error", JOptionPane.ERROR_MESSAGE, null);
            txtEmail.setText("");
            txtPassword.setText("");
            txtEmail.requestFocus();
        }
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
