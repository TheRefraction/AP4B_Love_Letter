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

    public void dispose() {
        for (Component c : getComponents()) {
            if (c instanceof AbstractButton b) {
                for (var l : b.getActionListeners()) b.removeActionListener(l);
            }
            for (var l : c.getMouseListeners()) c.removeMouseListener(l);
            for (var l : c.getKeyListeners()) c.removeKeyListener(l);
            for (var l : c.getFocusListeners()) c.removeFocusListener(l);
            for (var l : c.getComponentListeners()) c.removeComponentListener(l);
        }
    }


    abstract void init();
    public abstract void reset();
}
