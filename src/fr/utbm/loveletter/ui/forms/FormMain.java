package fr.utbm.loveletter.ui.forms;

import javax.swing.*;

public class FormMain extends Form {
    private JButton button1;
    private JLabel label1;

    @Override
    void init() {
        label1 = new JLabel("test");

        button1 = new JButton("hello");
        button1.addActionListener(e -> label1.setText("Clicked!"));

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(label1)
                .addComponent(button1)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(label1)
                .addComponent(button1)
        );
    }

    public void reset() {
        label1.setText("test");
        button1.setText("Has been reset");
    }
}
