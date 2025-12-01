package fr.utbm.loveletter.system;

import fr.utbm.loveletter.sprites.Sprite;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class AssetManager {
    private static final Logger logger = Logger.getLogger(AssetManager.class.getName());

    private final Map<String, Sprite> sprites = new HashMap<>();
    private final Map<String, Clip> sounds = new HashMap<>();
    private final Map<String, Font> fonts = new HashMap<>();
    private final Map<String, String> texts = new HashMap<>();

    private final Class<?> loaderClass;

    public AssetManager(Class<?> loaderClass) {
        this.loaderClass = loaderClass;
    }

    private InputStream getStream(String path) {
        return loaderClass.getResourceAsStream(path);
    }

    public Sprite loadSprite(String path, int originX, int originY, int imageNumber) {
        if (sprites.containsKey(path)) {
            return sprites.get(path);
        }

        try (InputStream in = getStream(path)) {
            BufferedImage image = ImageIO.read(in);

            Sprite sprite = new Sprite(image, originX, originY, imageNumber);
            sprites.put(path, sprite);

            return sprite;
        } catch (Exception e) {
            throw new RuntimeException("Could not load sprite: " + path, e);
        }
    }

    public Clip loadSound(String path) {
        if (sounds.containsKey(path)) {
            return sounds.get(path);
        }

        try (InputStream in = getStream(path)) {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(in));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            sounds.put(path, clip);

            return clip;
        } catch (Exception e) {
            throw new RuntimeException("Could not load sound: " + path, e);
        }
    }

    public Font loadFont(String path, float size) {
        String key = path + "#" + size;

        if (fonts.containsKey(key)) {
            return fonts.get(key);
        }

        try (InputStream in = getStream(path)) {
            Font font = Font.createFont(Font.TRUETYPE_FONT, in).deriveFont(size);
            fonts.put(key, font);

            return font;
        } catch (Exception e) {
            throw new RuntimeException("Could not load font: " + path, e);
        }
    }

    public String loadText(String path) {
        if (texts.containsKey(path))
            return texts.get(path);

        try (InputStream in = getStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            texts.put(path, sb.toString());
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Could not load text file: " + path, e);
        }
    }

    public void dispose() {
        for (Clip clip : sounds.values()) {
            if (clip.isOpen()) clip.close();
        }

        sounds.clear();
        sprites.clear();
        fonts.clear();
        texts.clear();
    }
}
