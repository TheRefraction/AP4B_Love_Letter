package fr.utbm.loveletter.system;

import fr.utbm.loveletter.objects.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameObjectManager {
    private final List<GameObject> objects = new ArrayList<>();

    public void add(GameObject obj) {
        objects.add(obj);
    }

    public void remove(GameObject obj) {
        objects.remove(obj);
    }

    public void clear() {
        objects.clear();
    }

    public void update() {
        for (GameObject obj : objects) {
            obj.update();
        }
    }

    public void render(Graphics2D g) {
        // Z-Depth sorting
        // The bigger z is, the closer it is to the camera (no perspective)
        objects.sort(Comparator.comparingInt(GameObject::getZ));

        for (GameObject obj : objects) {
            obj.render(g);
        }
    }
}
