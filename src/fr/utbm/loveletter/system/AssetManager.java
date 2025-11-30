package fr.utbm.loveletter.system;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AssetManager {
    private final Map<String, BufferedImage> sprites = new HashMap<>();
    private final Map<String, Clip> sounds = new HashMap<>();
    private final Map<String, Font> fonts = new HashMap<>();
    private final Map<String, String> texts = new HashMap<>();

    private Class<?> loaderClass;

    public AssetManager(Class<?> loaderClass) {
        this.loaderClass = loaderClass;
    }

    private InputStream getStream(String path) {
        return loaderClass.getResourceAsStream(path);
    }

    public BufferedImage loadSprite(String path) {
        if (sprites.containsKey(path)) {
            return sprites.get(path);
        }

        try (InputStream in = getStream(path)) {
            BufferedImage sprite = ImageIO.read(in);
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
}
