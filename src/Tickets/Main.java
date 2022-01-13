package Tickets;

import Controllers.Login.CLogin;
import Models.Customer;
import Models.Employee;
import Models.User;
import Config.Database;

public class Main {

    public static User user;
    public static Database connection;
    public static Customer customer;
    public static Employee employee;

    public Main() {
        try {
            // Database connexion
            connection = new Database("127.0.0.1", "ticketingsystem", "root", "Redbirds1234");
            connection.connect(false);
            user = new User();
            employee = new Employee();
            customer = new Customer();
            new CLogin();
        } catch (IllegalAccessError ex) {}
    }

    public static void main(String[] args) { new Main(); }

}
