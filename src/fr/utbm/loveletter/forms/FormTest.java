package fr.utbm.loveletter.forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormTest extends JFrame {
    private JPanel contentPane;
    private JButton button1;
    private JLabel label1;

    public FormTest() {
        setTitle("Book Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        pack();

        // Set the frame location to the center of the screen
        setLocationRelativeTo(null);

        // Save button event listener
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label1.setText("Clicked!");
            }
        });

        setVisible(true);
    }
}
