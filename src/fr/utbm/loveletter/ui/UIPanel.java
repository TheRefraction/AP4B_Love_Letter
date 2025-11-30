package fr.utbm.loveletter.ui;

import javax.swing.*;
import java.awt.*;

public class UIPanel extends JPanel {
    private CardLayout layout;

    public UIPanel() {
        setOpaque(false);
        layout = new CardLayout();
        setLayout(layout);

        addForm("empty", new JPanel() {{
            setOpaque(false);
        }});
    }

    public void addForm(String name, JPanel form) {
        add(form, name);
    }

    public void showForm(String name) {
        layout.show(this, name);
    }

    public void hideForm() {
        layout.show(this, "empty");
    }
}
