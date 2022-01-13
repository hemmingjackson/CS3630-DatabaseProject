package Controllers.Ticket;

import Controllers.Dashboard.CDashboardAdmin;
import Controllers.Dashboard.CDashboardCustomer;
import Frames.UIAssignTicketToTeam;
import Frames.UIChangePriority;
import Frames.UICreateTicket;
import Frames.UITicketDetail;
import Models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static Tickets.Main.user;

public class CTicket implements ITicket {

    private UICreateTicket window;
    private UIAssignTicketToTeam assignWindow;
    private UITicketDetail detailWindow;

    private ArrayList<TicketLocation> ticketLocations;
    private ArrayList<TicketType> ticketTypes;
    private ArrayList<Assignments> assignments;
    private Ticket ticket;
    private User customer;

    public CTicket(int action, String idTicket) {
        switch (action) {
            case 1: // Create a ticket action
                ticketLocations = TicketLocation.getTicketLocations();
                ticketTypes = TicketType.getTicketTypes();
                window = new UICreateTicket(this);
                break;
            case 2: // Modify a ticket action
                ticket = Ticket.getTicketById(idTicket);
                customer = User.getUserById(ticket.getIdUser());
                detailWindow = new UITicketDetail(this);
                break;
            case 3: // Assign a ticket action
                assignments = Assignments.getAssignments();
                //assignWindow = new UIAssignTicketToTeam(this);
                break;
        }

    }

    @Override
    public void cancel() {
        new CDashboardCustomer();
        window.dispose();
    }

    @Override
    public void saveCreationByCustomer(JComboBox cbxLocation, JComboBox cbxType, JTextArea txtIssue) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Ticket ticket = new Ticket();
        ticket.setIdUser(user.getId());
        ticket.setIdTicketLocation(String.valueOf(cbxLocation.getSelectedIndex() + 1));
        ticket.setIdTicketType(String.valueOf(cbxType.getSelectedIndex() + 1));
        ticket.setDescription(txtIssue.getText());
        ticket.setSubmissionDate(dtf.format(LocalDateTime.now()));
        boolean inserted = ticket.saveInDatabase();

        if (inserted) {
            JOptionPane.showMessageDialog(null, "Ticket created successfully.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            new CDashboardCustomer();
            window.dispose();
        }

    }

    @Override
    public void loadInformationForCreation(JComboBox cbxLocation, JComboBox cbxType) {
        for (int i = 0; i < ticketLocations.size(); i++) {
            String description = ticketLocations.get(i).getIdTicketLocation() + ": " + ticketLocations.get(i).getName() + "/" + ticketLocations.get(i).getRoom() + "/" + ticketLocations.get(i).getBuilding();
            cbxLocation.addItem(description);
        }

        for (int i = 0; i < ticketTypes.size(); i++) {
            String description = ticketTypes.get(i).getIdTicketType() + ": " + ticketTypes.get(i).getName();
            cbxType.addItem(description);
        }
    }

    @Override
    public void loadInformationForAssignment(JComboBox assignedTeam) {
        for (int i = 0; i < assignments.size(); i++) {
            String description = assignments.get(i).getIdAssignments() + ": " + assignments.get(i).getIdTicket() + "/" + assignments.get(i).getDescription();
            assignedTeam.addItem(description);
        }
    }

    @Override
    public void goBack() {
        new CDashboardAdmin();
        detailWindow.dispose();
    }

    @Override
    public void loadTicketInfo(JTable tblTicket, JTextArea textAreaDescription) {
        DefaultTableModel model = (DefaultTableModel) tblTicket.getModel();
        model.setRowCount(0);
        model.addRow(new Object[] {
            ticket.getId(),
            ticket.getIdTicketType(),
            ticket.getSubmissionDate(),
            ticket.getStatus(),
            ticket.getPriority()
        });
        textAreaDescription.setText(ticket.getDescription());
    }

    @Override
    public void loadCustomerInfo(JTable tblCustomer) {
        DefaultTableModel model = (DefaultTableModel) tblCustomer.getModel();
        model.setRowCount(0);
        model.addRow(new Object[] {
                customer.getId(),
                customer.getName(),
                customer.getEmail()
        });
    }

    @Override
    public void openUIChangePriority() {
        new UIChangePriority(ticket.getId());
    }
}
