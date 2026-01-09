package fr.utbm.loveletter.system;

import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public class InputManager implements KeyListener, MouseListener, MouseMotionListener {
    private final Set<Integer> keysDown = new HashSet<>();
    private final Set<Integer> keysPressed = new HashSet<>();
    private final Set<Integer> keysReleased = new HashSet<>();

    private int mouseX, mouseY;
    private boolean mouseDown = false;

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (!keysDown.contains(keyCode)) {
            keysPressed.add(keyCode);
        }
        keysDown.add(keyCode);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        keysDown.remove(keyCode);
        keysReleased.add(keyCode);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseDown = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public boolean isKeyDown(int keyCode) {
        return keysDown.contains(keyCode);
    }

    public boolean isKeyPressed(int keyCode) {
        return keysPressed.contains(keyCode);
    }

    public boolean isKeyReleased(int keyCode) {
        return keysReleased.contains(keyCode);
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isMouseDown() {
        return mouseDown;
    }

    public void endFrame() {
        keysPressed.clear();
        keysReleased.clear();
        mouseDown = false;
    }
}
