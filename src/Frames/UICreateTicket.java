package Frames;

import Controllers.Ticket.ITicket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UICreateTicket extends JFrame {
    private JComboBox cbxType;
    private JComboBox cbxLocation;
    private JTextArea txtIssue;
    private JButton btnCancel;
    private JButton btnCreate;
    private JPanel mainPanel;

    private ITicket inter;

    public UICreateTicket(ITicket inter) {
        initPanel();
        this.setVisible(true);
        this.setTitle("Create a Ticket");
        setLocationRelativeTo(null);

        this.inter = inter;
        inter.loadInformationForCreation(cbxLocation, cbxType);

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inter.saveCreationByCustomer(cbxLocation, cbxType, txtIssue);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inter.cancel();
            }
        });
    }

    private void initPanel() {
        setContentPane(mainPanel);
        setSize(600, 400);
    }
}
