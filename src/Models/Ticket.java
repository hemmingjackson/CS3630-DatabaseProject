package Models;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Tickets.Main.connection;

public class Ticket {
    private String id;
    private String idUser;
    private String idTicketType;
    private String idTicketLocation;
    private String description;
    private String submissionDate;
    private String priority;
    private String status;

    public Ticket() {
        this("-1", "-1", "-1", "-1", "NULL", "0000-00-00", "NULL","0");
    }

    public Ticket(String id, String idUser, String idTicketType, String idTicketLocation, String description, String submissionDate, String priority, String status) {
        this.id = id;
        this.idUser = idUser;
        this.idTicketType = idTicketType;
        this.idTicketLocation = idTicketLocation;
        this.description = description;
        this.submissionDate = submissionDate;
        this.priority = priority;
        this.status = status;
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

    public String getIdTicketType() {
        return idTicketType;
    }

    public void setIdTicketType(String idTicketType) {
        this.idTicketType = idTicketType;
    }

    public String getIdTicketLocation() {
        return idTicketLocation;
    }

    public void setIdTicketLocation(String idTicketLocation) {
        this.idTicketLocation = idTicketLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static ArrayList<Ticket> getListByCustomerId(String id) {
        ArrayList<Ticket> tickets = new ArrayList<>();

        try {
            ResultSet rs = connection.execute("SELECT * FROM ticket WHERE idUser = ?", new String[] {id}, true);
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getString("idTicket"));
                ticket.setIdTicketType(rs.getString("idTicketType"));
                ticket.setIdTicketLocation(rs.getString("idTicketLocation"));
                ticket.setIdUser(id);
                ticket.setSubmissionDate(rs.getString("submissionDate"));
                ticket.setDescription(rs.getString("description"));
                ticket.setPriority(rs.getString("priority"));
                ticket.setStatus(rs.getString("status"));
                tickets.add(ticket);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database connection error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return tickets;
    }

    public boolean saveInDatabase() {
        boolean inserted = false;
        try {
            connection.execute("INSERT INTO ticket VALUES (DEFAULT, ?, ?, ?, ?, ?, DEFAULT, DEFAULT)",
                                new String[] {
                                    idUser, idTicketType, idTicketLocation, description, submissionDate
                                }, false);
            inserted = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Database connection error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return inserted;
    }

    public static Ticket getTicketById(String idTicket) {
        Ticket ticket = null;
        try {
            ResultSet rs = connection.execute("SELECT * FROM ticket WHERE idTicket = ?", new String[] {idTicket}, true);
            rs.next();
            if (rs.isLast()) {
                ticket = new Ticket();
                ticket.setId(rs.getString("idTicket"));
                ticket.setIdUser(rs.getString("idUser"));
                ticket.setIdTicketType(rs.getString("idTicketType"));
                ticket.setIdTicketLocation(rs.getString("idTicketLocation"));
                ticket.setDescription(rs.getString("description"));
                ticket.setSubmissionDate(rs.getString("submissionDate"));
                ticket.setPriority(rs.getString("priority"));
                ticket.setStatus(rs.getString("status"));
            }
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Database connection error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return ticket;
    }

    public static ArrayList<Ticket> getTicketsByStatus(String status) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            ResultSet rs = connection.execute("SELECT * FROM ticket WHERE status = ?", new String[] {status}, true);
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getString("idTicket"));
                ticket.setIdTicketType(rs.getString("idTicketType"));
                ticket.setIdTicketLocation(rs.getString("idTicketLocation"));
                ticket.setIdUser(rs.getString("idUser"));
                ticket.setSubmissionDate(rs.getString("submissionDate"));
                ticket.setDescription(rs.getString("description"));
                ticket.setPriority(rs.getString("priority"));
                ticket.setStatus(rs.getString("status"));
                tickets.add(ticket);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database connection error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return  tickets;
    }
}
