package Controllers.Assignment;

import Controllers.Dashboard.CDashboardCustomer;
import Controllers.Team.CTeams;
import Controllers.Ticket.ITicket;
import Frames.UIAssignTicketToTeam;

import javax.swing.*;
import java.sql.SQLException;

import static Tickets.Main.connection;

public class CAssignment implements IAssignment {

    private UIAssignTicketToTeam window;
    private String ticketID;

    public CAssignment(int adminTicketIndex) {
        //this.window = new UIAssignTicketToTeam( this);
        this.ticketID = String.valueOf(adminTicketIndex);
    }


    @Override
    public void updateAssignmentTicket(String teamIndex) {
        try {
            if (JOptionPane.showConfirmDialog(null, "Are you sure to assign it?", "Assign", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
                connection.execute("INSERT INTO ticketsteams VALUES (?,?,DEFAULT, DEFAULT)",
                        new String[]{
                                ticketID, teamIndex
                        }, false);
                connection.execute("UPDATE ticket SET status = '1' WHERE idticket = ?",
                        new String[]{
                                ticketID
                        }, false);
                new CTeams();
                window.dispose();
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void cancel()
    {
        new CDashboardCustomer();
        window.dispose();
    }
}
