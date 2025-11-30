package fr.utbm.loveletter.ui.forms;

import javax.swing.*;

public class FormTest extends JFrame {
    public JPanel contentPane;
    private JButton button1;
    private JLabel label1;

    public FormTest() {
        button1.addActionListener(e -> label1.setText("Clicked!"));

        setVisible(false);
    }
}
