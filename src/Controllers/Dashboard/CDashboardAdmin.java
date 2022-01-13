package Controllers.Dashboard;

import Controllers.Assignment.CAssignment;
import Controllers.Login.CLogin;
import Controllers.Team.CTeams;
import Controllers.Ticket.CTicket;
import Frames.UIAssignTicketToTeam;
import Frames.UIDashboardAdmin;
import Models.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

import static Tickets.Main.connection;

public class CDashboardAdmin implements IDashboardAdmin {

    private UIDashboardAdmin window;
    private ArrayList<Ticket> ticketsToDo;
    private ArrayList<Ticket> ticketsInProgress;
    private ArrayList<Ticket> ticketsCompleted;

    public CDashboardAdmin() {
        ticketsToDo = Ticket.getTicketsByStatus("0");
        ticketsInProgress = Ticket.getTicketsByStatus("1");
        ticketsCompleted = Ticket.getTicketsByStatus("2");
        window = new UIDashboardAdmin(this);

    }


    @Override
    public void loadTableData(JTable tblToDo, JTable tblInProgress, JTable tblCompleted) {
        assignTableTicket(tblToDo, ticketsToDo);
        assignTableTicket(tblInProgress, ticketsInProgress);
        assignTableTicket(tblCompleted, ticketsCompleted);
    }

    private void assignTableTicket(JTable table, ArrayList<Ticket> tickets) {
        DefaultTableModel modelToDo = (DefaultTableModel) table.getModel();
        modelToDo.setRowCount(0);
        for (int i = 0; i < tickets.size(); i++) {
            modelToDo.addRow(new Object[]{
                    tickets.get(i).getId(),
                    tickets.get(i).getDescription(),
                    tickets.get(i).getIdUser(),
                    tickets.get(i).getSubmissionDate(),
                    tickets.get(i).getPriority()
            });
        }
    }

    @Override
    public void logout() {
        new CLogin();
        window.dispose();
    }

    @Override
    public void markAsDone(String selectedRow) {
        //TODO: Update ticket ID to 'done'
        try {
            Ticket ticket = ticketsInProgress.get(Integer.parseInt(selectedRow));
            if (JOptionPane.showConfirmDialog(null, "Are you sure to complete it?", "Complete", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
                connection.execute("UPDATE ticket SET status = '2' WHERE idticket = ?", new String[]{ticket.getId()}, false);
                window.dispose();
                new CDashboardAdmin();
            }
        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Nothing to complete", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void assignTicket(String ticketIndex) {
        //new UIAssignTicketToTeam(inter, ticketIndex);
    }

    @Override
    public void openTeamWindow() {
        new CTeams();
    }

    @Override
    public void openAssignTicketWindow(int adminTicketIndex) { new CAssignment(adminTicketIndex); }

    @Override
    public void deleteToDoTicket(JTable tblAdminToDo) {
        int i = tblAdminToDo.getSelectedRow();
        if (i != -1) {
            try {
                Ticket ticket = ticketsToDo.get(i);
                if (JOptionPane.showConfirmDialog(null, "Are you sure to delete it?", "Delete", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
                    connection.execute("DELETE FROM ticket WHERE idTicket = ?", new String[] {ticket.getId()}, false);
                    window.dispose();
                    new CDashboardAdmin();
                }
            } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Nothing to delete", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please, select a ticket", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void viewTicket(JTable tblTicket, int option) {
        int i = tblTicket.getSelectedRow();
        if (i != -1) {
            try {
                String idTicket;
                if (option == 0) {
                    idTicket = ticketsToDo.get(i).getId();
                } else {
                    idTicket = ticketsInProgress.get(i).getId();
                }
                new CTicket(2, idTicket);
                window.dispose();
            } catch (ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Nothing to view/edit", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please, select a ticket", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
