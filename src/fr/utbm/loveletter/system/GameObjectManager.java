package fr.utbm.loveletter.system;

import fr.utbm.loveletter.objects.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class manages all objects registered in a scene
 * Each object has a unique index
 */
public class GameObjectManager {
    private final List<GameObject> objects = new ArrayList<>();

    public void add(GameObject obj) {
        if (!objects.contains(obj)) {
            objects.add(obj);
        }
    }

    public void remove(GameObject obj) {
        objects.remove(obj);
    }

    public void remove(int index) {
        if (index >= 0 && index < objects.size()) {
            objects.remove(index);
        }
    }

    public boolean contains(GameObject obj) {
        return objects.contains(obj);
    }

    public void clear() {
        objects.clear();
    }

    public void update(InputManager input) {
        for (GameObject obj : objects) {
            obj.update(input);
        }
    }

    public void render(Graphics2D g2d, AssetManager assets) {
        // Z-Depth sorting
        // The bigger z is, the closer it is to the camera (no perspective)
        objects.sort(Comparator.comparingInt(GameObject::getZ));

        for (GameObject obj : objects) {
            obj.render(g2d, assets);
        }
    }
}
