package fr.utbm.loveletter.ui.forms;

import javax.swing.*;
import java.awt.*;

public abstract class Form extends JPanel {
    protected GroupLayout layout;

    public Form() {
        super();

        layout = new GroupLayout(this);

        init();

        Component[] comps = getComponents();
        for (Component comp : comps) {
            comp.setFocusable(false);
        }

        setLayout(layout);
        setFocusable(false);
        setOpaque(false);
        setVisible(true);
    }

    abstract void init();
    public abstract void reset();
}
