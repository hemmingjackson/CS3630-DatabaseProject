package Frames;

import Controllers.Login.CLogin;
import Controllers.Team.CTeams;
import Controllers.Team.ITeams;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UICreateTeam extends JFrame{
    private JTextField teamName;
    private JButton createTeamButton;
    private JButton cancelButton;
    private JPanel mainPanel;
    private String name = null;
    private ITeams inter;

    public UICreateTeam(ITeams inter) {
        initPanel();
        this.setVisible(true);
        this.setTitle("Create a Team");
        setLocationRelativeTo(null);

        this.inter = inter;

        createTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = teamName.getText();
                inter.add(name);
                dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CTeams();
            }
        });
    }

    private void initPanel() {
        setContentPane(mainPanel);
        setSize(700, 300);
    }
}
