package fr.utbm.loveletter.ui;

import fr.utbm.loveletter.ui.forms.Form;
import fr.utbm.loveletter.ui.forms.FormNull;

import javax.swing.*;
import java.awt.*;

public class UIPanel extends JPanel {
    private final CardLayout layout;

    public UIPanel() {
        setOpaque(false);
        layout = new CardLayout();
        setLayout(layout);

        addForm("empty", new FormNull() {{
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
        Form card = null;
        for (Component comp : getComponents()) {
            if (comp.isVisible()) {
                card = (Form) comp;
            }
        }

        if (card != null) {
            card.reset();
        }

        layout.show(this, "empty");
    }

    public void disposeForms() {
        for (Component comp : getComponents()) {
            if (comp instanceof Form f) {
                f.dispose();
            }
        }

        removeAll();
        addForm("empty", new FormNull() {{
            setOpaque(false);
        }});
        revalidate();
        repaint();
    }
}
