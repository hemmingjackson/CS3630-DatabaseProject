package Frames;

import Controllers.Dashboard.CDashboardAdmin;
import Controllers.Team.ITeams;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UITeams extends JFrame {
    private JTable teamsTable;
    private JButton createTeamButton;
    private JPanel mainPanel;
    private JButton deleteButton;
    private JButton editButton;
    private JButton assignMemberButton;
    private JButton goBackButton;

    private ITeams inter;

    public UITeams(ITeams inter) {
        createTable();
        initPanel();
        this.setVisible(true);
        this.setTitle("Teams List");
        setLocationRelativeTo(null);

        this.inter = inter;
        inter.loadTeams(this.teamsTable);

        createTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UICreateTeam(inter);
                dispose();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inter.edit(teamsTable);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inter.delete(teamsTable);
            }
        });
        assignMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UIAssignEmployeeToTeam(inter);
                dispose();
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CDashboardAdmin();
                dispose();
            }
        });
    }

    private void createTable() {
        teamsTable.setModel(new DefaultTableModel(
                new Object [][] {},
                new String[] {"ID", "Name"}
        ));
        teamsTable.setEditingColumn(1);
    }

    private void initPanel() {
        setContentPane(mainPanel);
        setSize(600, 400);
    }


}
