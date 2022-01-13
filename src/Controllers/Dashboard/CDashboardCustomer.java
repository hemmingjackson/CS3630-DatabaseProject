package Controllers.Dashboard;

import Controllers.Login.CLogin;
import Controllers.Ticket.CTicket;
import Frames.UIDashboardCustomer;
import Models.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

import static Tickets.Main.user;

public class CDashboardCustomer implements IDashboardCustomer {

    private UIDashboardCustomer window;
    private ArrayList<Ticket> tickets;

    public CDashboardCustomer() {
        tickets = Ticket.getListByCustomerId(user.getId());
        window = new UIDashboardCustomer(this);
    }

    @Override
    public void logout() {
        new CLogin();
        window.dispose();
    }

    @Override
    public void loadCustomerData(JLabel txtName) {
        txtName.setText("Welcome, " + user.getName() + " " + user.getLastname());
    }

    @Override
    public void loadTableData(JTable tblPrevTickets) {
        DefaultTableModel model = (DefaultTableModel) tblPrevTickets.getModel();
        model.setRowCount(0);

        for (int i = 0; i < tickets.size(); i++) {
            model.addRow(new Object[] {
                tickets.get(i).getDescription(),
                tickets.get(i).getIdTicketType(),
                tickets.get(i).getSubmissionDate(),
                tickets.get(i).getPriority()
            });
        }
    }

    @Override
    public void openCreateTicketWindow() {
        new CTicket(1, null);
        window.dispose();
    }

}
