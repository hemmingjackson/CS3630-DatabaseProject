package Controllers.Team;

import Controllers.Dashboard.CDashboardAdmin;
import Controllers.Dashboard.CDashboardCustomer;
import Frames.UIEditTeam;
import Frames.UITeams;
import Models.EmployeesTeams;
import Models.Team;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static Tickets.Main.connection;

public class CTeams implements  ITeams {

    private UITeams window;
    private ArrayList<Team> teams;

    public CTeams() {
        //switch (action) {
            //case 1: // Show main teams view
                //break;
            //case 2: // Edit team
                //break;
            //case 3: // Delete team
                //break;
        //}
        teams = Team.getTeams();
        window = new UITeams(this);
    }

    @Override
    public void loadTeams(JTable teamsTable) {
        DefaultTableModel model = (DefaultTableModel) teamsTable.getModel();
        model.setRowCount(0);
        for (int i = 0; i < teams.size(); i++) {
            model.addRow(new Object[] {
                teams.get(i).getIdTeam(),
                teams.get(i).getName(),
            });
        }
    }

    @Override
    public void edit(JTable teamsTable) {
        int i = teamsTable.getSelectedRow();
        if (i != -1) {
            try {
                Team team = teams.get(i);
                window.dispose();
                new UIEditTeam(team.getIdTeam());
            } catch (ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Nothing to edit", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please, select a team", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void delete(JTable teamsTable) {
        int i = teamsTable.getSelectedRow();
        if (i != -1) {
            try {
                Team team = teams.get(i);
                if (JOptionPane.showConfirmDialog(null, "Are you sure to delete it?", "Delete", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
                    connection.execute("DELETE FROM team WHERE idTeam = ?", new String[] {team.getIdTeam()}, false);
                    new CTeams();
                    window.dispose();
                }
            } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Nothing to delete", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please, select a team", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void add(String name) {
        try {
            if (JOptionPane.showConfirmDialog(null, "Are you sure to create it?", "Create", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
                connection.execute("INSERT INTO team VALUES (DEFAULT, ?, DEFAULT)",
                        new String[]{
                                name
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
    public void assign(JComboBox teamTable, JComboBox employee) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        EmployeesTeams employeesTeams = new EmployeesTeams();
        employeesTeams.setIdTeam(String.valueOf(teamTable.getSelectedIndex()));
        employeesTeams.setIdEmployee(String.valueOf(employee.getSelectedIndex()));
        employeesTeams.setEnrollDate(dtf.format(LocalDateTime.now()));

        boolean inserted = employeesTeams.saveInDatabase();

        if (inserted) {
            JOptionPane.showMessageDialog(null, "Employee assigned successfully.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            new CDashboardAdmin();
            window.dispose();
        }
    }
}