package Controllers.Dashboard;

import javax.swing.*;

public interface IDashboardCustomer {
    public void logout();

    public void loadCustomerData(JLabel txtName);

    public void loadTableData(JTable tblPrevTickets);

    public void openCreateTicketWindow();

}
