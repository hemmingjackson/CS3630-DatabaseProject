package Frames;

import javax.swing.*;

import Controllers.Dashboard.CDashboardAdmin;
import Controllers.Dashboard.IDashboardAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Tickets.Main.connection;

public class UIAssignTicketToTeam extends JFrame {

    private JPanel mainPanel;
    private JComboBox assignedTeam;
    private JButton confirmButton;
    private JButton cancelButton;

    private String teamIndex;
    private String ticketID;

    public UIAssignTicketToTeam(String ticketIndex)
    {
        initPanel();
        updateTeamComboBox();
        this.setVisible(true);
        this.setTitle("Create a Ticket");
        setLocationRelativeTo(null);

        ticketID = ticketIndex;
        teamIndex = null;

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //((IAssignment) inter).updateAssignmentTicket(String.valueOf(assignedTeam.getSelectedIndex()));
                teamIndex = String.valueOf(assignedTeam.getSelectedIndex());
                try {
                    if (JOptionPane.showConfirmDialog(null, "Are you sure to assign it?", "Assign", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
                        System.out.println(teamIndex);
                        System.out.println(ticketID);
                        connection.execute("INSERT INTO ticketsteams VALUES (?,?,DEFAULT, DEFAULT)",
                                new String[]{
                                        ticketID, teamIndex
                                }, false);
                        connection.execute("UPDATE ticket SET status = '1' WHERE idticket = ?",
                                new String[]{
                                        ticketID
                                }, false);
                        dispose();
                        new CDashboardAdmin();
                        //new UIDashboardAdmin(inter);
                    }
                }
                catch(SQLException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //new UIDashboardAdmin(inter);
            }
        });
    }

    private void initPanel() {
        setContentPane(mainPanel);
        setSize(600, 400);
    }

    private void updateTeamComboBox()
    {
        try {
            ResultSet rs = connection.execute("SELECT name FROM team", new String[] {}, true);
            while (rs.next()) {
                assignedTeam.addItem(rs.getString("name"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database connection error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
