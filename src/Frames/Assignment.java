package Frames;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Assignment extends JFrame {
    private JTextField textField1;
    private JButton addButton;
    private JTable table1;
    private JButton goBackButton;
    private JPanel mainPanel;

    public Assignment() {
        createTable();
        initPanel();
        this.setVisible(true);
        this.setTitle("Add Assignment");
        setLocationRelativeTo(null);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add assignment to ticket
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to previous window
            }
        });
    }

    private void createTable() {
        table1.setModel(new DefaultTableModel(
                new Object [][] {},
                new String[] {"Description"}
        ));
    }

    private void initPanel() {
        setContentPane(mainPanel);
        setSize(700, 300);
    }

}
