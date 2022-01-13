package Frames;


import Controllers.Ticket.ITicket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UITicketDetail extends JFrame {
    private JPanel mainPanel;
    private JTable ticketinfotable;
    private JTable customerinfotable;
    private JButton goBackButton;
    private JButton changePriorityButton;
    private JTextArea textAreaDescription;

    private ITicket inter;

    public UITicketDetail(ITicket inter) {
        createTicketInfoTable();
        createCustomerInfoTable();
        initPanel();
        this.setVisible(true);
        this.setTitle("Ticket Details");
        setLocationRelativeTo(null);

        this.inter = inter;

        inter.loadTicketInfo(ticketinfotable, textAreaDescription);
        inter.loadCustomerInfo(customerinfotable);

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inter.goBack();
            }
        });
        changePriorityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inter.openUIChangePriority();
            }
        });
    }


    private void createTicketInfoTable() {
        ticketinfotable.setModel(new DefaultTableModel(
                new Object [][] {},
                new String[] {"Number", "Type", "Date", "Status", "Priority"}
        ));
    }

    private void createCustomerInfoTable() {
        customerinfotable.setModel(new DefaultTableModel(
                new Object [][] {},
                new String[] {"UserID", "Name", "Email Address"}
        ));
    }

    private void initPanel() {
        setContentPane(mainPanel);
        setSize(800, 300);
    }
}
