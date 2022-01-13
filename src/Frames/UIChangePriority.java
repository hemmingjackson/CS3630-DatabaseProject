package Frames;

import Controllers.Team.CTeams;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static Tickets.Main.connection;

public class UIChangePriority extends JFrame {
    private JPanel mainPanel;
    private JComboBox cbxPriority;
    private JButton cancelButton;
    private JButton confirmButton;
    private String priority = "0";

    public UIChangePriority(String idTicket) {
        loadData();
        initPanel();
        this.setVisible(true);
        this.setTitle("Change Priority");
        setLocationRelativeTo(null);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    priority = String.valueOf(cbxPriority.getSelectedIndex());
                    if (JOptionPane.showConfirmDialog(null, "Are you sure to edit it?", "Edit", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
                        connection.execute("UPDATE ticket SET priority = ? WHERE idTicket = ?", new String[]{priority, idTicket}, false);
                        dispose();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void loadData() {
        cbxPriority.addItem("Low");
        cbxPriority.addItem("Medium");
        cbxPriority.addItem("High");
    }

    private void initPanel() {
        setContentPane(mainPanel);
        setSize(500, 300);
    }
}
