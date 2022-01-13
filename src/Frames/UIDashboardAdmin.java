package Frames;

import Controllers.Dashboard.IDashboardAdmin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIDashboardAdmin extends JFrame {
    private JPanel mainPanel;
    private JTable tblAdminToDo;
    private JButton teamsButton;
    private JTable tblAdminInProg;
    private JTable tblAdminComplete;
    private JButton assignTicket;
    private JButton deleteToDoTicket;
    private JButton viewEditButton;
    private JButton markAsDoneButton;
    private JButton viewEditButton1;
    private JButton logoutButton;

    private IDashboardAdmin inter;

    public UIDashboardAdmin(IDashboardAdmin inter) {
        createTables();
        initPanel();
        this.setVisible(true);
        this.setTitle("Dashboard Admin");
        setLocationRelativeTo(null);

        this.inter = inter;
        inter.loadTableData(tblAdminToDo, tblAdminInProg, tblAdminComplete);

        teamsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                inter.openTeamWindow();
            }
        });

        assignTicket.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UIAssignTicketToTeam(String.valueOf(tblAdminToDo.getValueAt(0,0)));
                //inter.assignTicket(String.valueOf(tblAdminToDo.getSelectedRow()));
            }
        }));
        deleteToDoTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inter.deleteToDoTicket(tblAdminToDo);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inter.logout();
            }
        });
        viewEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inter.viewTicket(tblAdminToDo, 0);
            }
        });
        viewEditButton1.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              inter.viewTicket(tblAdminInProg, 1);
          }
        });
        markAsDoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inter.markAsDone(String.valueOf(tblAdminInProg.getSelectedRow()));
            }
        });
    }
        // Create tables
        private void createTables() {
            tblAdminToDo.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Issue", "Created by", "Created at", "Priority"}));
            tblAdminInProg.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Issue", "Created by", "Created at", "Priority"}));
            tblAdminComplete.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Issue", "Created by", "Created at", "Priority"}));
        }


        // Method that starts or create the main panel
        private void initPanel() {
            setContentPane(mainPanel);
            setSize(900, 700);
        }
    }
