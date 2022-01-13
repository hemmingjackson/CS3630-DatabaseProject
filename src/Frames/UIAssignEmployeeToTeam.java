package Frames;

import Controllers.Team.CTeams;
import Controllers.Team.ITeams;
import Controllers.Ticket.ITicket;
import Models.Team;
import Models.Ticket;
import Models.TicketType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Tickets.Main.connection;

public class UIAssignEmployeeToTeam extends JFrame {
    private JComboBox getTeam;
    private JComboBox assignEmployee;
    private JButton addToTeamButton;
    private JButton cancelButton;
    private JPanel mainPanel;

    private ITeams inter;

    public UIAssignEmployeeToTeam(ITeams inter) {
        initPanel();
        updateTeamComboBox();
        //createTable();
        updateEmployeeComboBox();
        this.setVisible(true);
        this.setTitle("Assign Employee to Team");
        setLocationRelativeTo(null);

        this.inter = inter;
        //inter.loadTeams(this.getTeam);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CTeams();
            }
        });
        addToTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inter.assign(getTeam, assignEmployee);
            }
        });
    }

    private void updateTeamComboBox()
    {
        try {
            ResultSet rs = connection.execute("SELECT name FROM team", new String[] {}, true);
            while (rs.next()) {
                getTeam.addItem(rs.getString("name"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database connection error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateEmployeeComboBox()
    {
        try {
            ResultSet rs = connection.execute("SELECT name FROM user", new String[] {}, true);
            while (rs.next()) {
                assignEmployee.addItem(rs.getString("name"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database connection error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*private void createTable() {
        getTeam.setModel(new DefaultTableModel(
                new Object [][] {},
                new String[] {"ID", "Name"}
        ));
        getTeam.setEditingColumn(1);
    }*/

    private void initPanel() {
        setContentPane(mainPanel);
        setSize(700, 300);
    }
}
