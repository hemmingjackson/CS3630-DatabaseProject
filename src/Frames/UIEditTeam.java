package Frames;

import Controllers.Team.CTeams;
import Controllers.Team.ITeams;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static Tickets.Main.connection;

public class UIEditTeam extends JFrame {
    private JTextField teamName;
    private JButton cancelButton;
    private JButton confirmButton;
    private JPanel mainPanel;
    private ITeams inter;
    private String name = null;

    public UIEditTeam(String idTeam) {

        initPanel();
        this.setVisible(true);
        this.setTitle("Edit a Team");
        setLocationRelativeTo(null);


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CTeams();
                dispose();
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = teamName.getText();
                try {
                    if (JOptionPane.showConfirmDialog(null, "Are you sure to edit it?", "Edit", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
                        connection.execute("UPDATE team SET name = ? WHERE idTeam = ?", new String[]{name, idTeam}, false);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                new CTeams();
                dispose();
            }
        });
    }

    private void initPanel() {
        setContentPane(mainPanel);
        setSize(700, 300);
    }
}
