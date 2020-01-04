package me.blazingtide.marvel.save;

import me.blazingtide.marvel.patch.Patch;
import me.blazingtide.marvel.settings.PatchSetting;

import java.io.File;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

/**
 * Basic storage for patches so it's easier to call them for
 * future computations
 *
 * @param <K> Patch
 * @param <V> PatchSetting
 */
public interface PatchSave<K extends Patch, V extends PatchSetting> {

    K getPatch();

    V getSetting();

    File getFile();

    URLClassLoader getLoader();

    static <K extends Patch, V extends PatchSetting> PatchSave<K, V> of(K k, V v, File file, URLClassLoader loader) {
        return new PatchSave<K, V>() {
            @Override
            public K getPatch() {
                return k;
            }

            @Override
            public V getSetting() {
                return v;
            }

            @Override
            public File getFile() {
                return file;
            }

            @Override
            public URLClassLoader getLoader() {
                return loader;
            }
        };
    }

}
