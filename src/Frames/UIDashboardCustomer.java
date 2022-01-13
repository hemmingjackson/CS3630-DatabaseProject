package Frames;

import Controllers.Dashboard.IDashboardCustomer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIDashboardCustomer extends JFrame {
    private JLabel txtWelcome;
    private JButton btnCreateTicket;
    private JTable tblPrevTickets;
    private JPanel mainPanel;
    private JButton logoutButton;

    private IDashboardCustomer inter;

    public UIDashboardCustomer(IDashboardCustomer inter) {
        createTable();
        initPanel();
        this.setVisible(true);
        this.setTitle("Dashboard Customer");
        setLocationRelativeTo(null);

        this.inter = inter;
        inter.loadCustomerData(
                this.txtWelcome
        );
        inter.loadTableData(
                this.tblPrevTickets
        );
        btnCreateTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inter.openCreateTicketWindow();
            }
        });

        // Logs user out of program
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inter.logout();
            }
        });
    }

    private void createTable() {
        tblPrevTickets.setModel(new DefaultTableModel(
                new Object [][] {},
                new String[] {"Issue", "Type", "Date", "Status"}
        ));
    }

    private void initPanel() {
        setContentPane(mainPanel);
        setSize(700, 300);
    }

}
