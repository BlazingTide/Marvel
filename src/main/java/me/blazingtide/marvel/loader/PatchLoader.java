package me.blazingtide.marvel.loader;

import lombok.SneakyThrows;
import me.blazingtide.marvel.MarvelPlugin;
import me.blazingtide.marvel.flags.Flag;
import me.blazingtide.marvel.flags.PatchFlags;
import me.blazingtide.marvel.patch.Patch;
import me.blazingtide.marvel.save.PatchSave;
import me.blazingtide.marvel.settings.PatchSetting;
import org.bukkit.Bukkit;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Handles the loading of the jar files into run-time
 * also handles the flags & registering patches
 */
public class PatchLoader {

    @SneakyThrows
    public void run(File file) {
        URLClassLoader loader = new URLClassLoader(new URL[]{file.toURI().toURL()}, this.getClass().getClassLoader());
        JarFile jar = new JarFile(file);
        Optional<Class<? extends Patch>> mainClass = findMainClass(jar, loader);

        //We do this to make sure we don't load classes in that don't have a patch interface
        if (!mainClass.isPresent()) {
            MarvelPlugin.get().getLogger().severe("Could not find main class for file: " + jar.getName());
            return;
        }

        Enumeration<JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String className = entry.getName().replaceAll(".class", "").replace('/', '.');

            if (entry.getName().endsWith(".class")) {
                loader.loadClass(className);
            }
        }

        registerPatch(mainClass.get());
    }

    private void registerPatch(Class<? extends Patch> clazz) throws IllegalAccessException, InstantiationException {
        Patch<?> patch = clazz.newInstance();
        PatchFlags flags = clazz.getAnnotation(PatchFlags.class);

        PatchSetting<Patch> setting = PatchSetting.of(patch, flags == null ? new Flag[0] : flags.values());
        PatchSave<?, ?> save = PatchSave.of(patch, setting);

        MarvelPlugin.get().getLogger().info("Loaded Patch: " + patch.getName());
        MarvelPlugin.get().getPatchSaves().add(save);
        patch.onStartup();
    }

    private Optional<Class<? extends Patch>> findMainClass(JarFile jar, URLClassLoader loader) throws ClassNotFoundException {
        Enumeration<JarEntry> entries = jar.entries();

        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();

            if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                String className = entry.getName().replaceAll(".class", "").replace('/', '.');
                Class<?> clazz = loader.loadClass(className);

                if (Patch.class.isAssignableFrom(clazz)) {
                    return Optional.of(((Class<? extends Patch>) clazz));
                }
            }
        }

        return Optional.empty();
    }

}
