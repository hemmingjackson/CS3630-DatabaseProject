package Models;

import Controllers.Ticket.CTicket;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Tickets.Main.connection;

public class Assignments {

    private String idAssignments;
    private String idTicket;
    private String description;

    public Assignments() {this("NULL","NULL","NULL");}

    public Assignments(String idAssignments, String idTicket, String description)
    {
        this.idAssignments = idAssignments;
        this.idTicket = idTicket;
        this.description = description;
    }

    public String getIdAssignments() {
        return idAssignments;
    }

    public void setIdAssignments(String idAssignments) {
        this.idAssignments = idAssignments;
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean saveInDatabase()
    {
        boolean inserted = false;
        try {
            connection.execute("INSERT INTO assignment VALUES (DEFAULT, ?, ?)",
                    new String[] {
                            idAssignments,idTicket,description
                    },false);
            inserted = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Database connection error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return inserted;
    }

    public static ArrayList<Assignments> getAssignments() {
        ArrayList<Assignments> getAssignments = new ArrayList<>();
        try {
            ResultSet rs = connection.execute("SELECT * FROM assignments", new String[] {}, true);
            while (rs.next()) {
                Assignments assignments = new Assignments();
                assignments.setIdAssignments(rs.getString("idAssignments"));
                assignments.setIdTicket(rs.getString("idTicket"));
                assignments.setDescription(rs.getString("description"));
                getAssignments.add(assignments);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database connection error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return getAssignments;
    }
}
