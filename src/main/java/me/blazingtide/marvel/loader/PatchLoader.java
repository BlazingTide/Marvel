package me.blazingtide.marvel.loader;

import me.blazingtide.marvel.MarvelPlugin;
import me.blazingtide.marvel.flags.Flag;
import me.blazingtide.marvel.flags.PatchFlags;
import me.blazingtide.marvel.patch.Patch;
import me.blazingtide.marvel.save.PatchSave;
import me.blazingtide.marvel.settings.PatchSetting;

import java.io.File;
import java.io.IOException;
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

    /**
     * Unloads a patch by using the URLClassLoader
     * which was responsible for loading the patch
     *
     * @param save
     * @throws IOException
     */
    public void unload(PatchSave save) throws IOException {
        save.getLoader().close();
    }

    /**
     * Run the patch loader
     * Attempts to load patch from that file
     *
     * It looks for the implementation of the Patch class
     * @see me.blazingtide.marvel.patch.Patch
     *
     * Labels that implementation as the main class
     * @param file
     * @return Patch if found
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Patch<?> load(File file) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        URLClassLoader loader = new URLClassLoader(new URL[]{file.toURI().toURL()}, this.getClass().getClassLoader());
        JarFile jar = new JarFile(file);
        Optional<Class<? extends Patch>> mainClass = findMainClass(jar, loader);

        //We do this to make sure we don't load classes in that don't have a patch interface
        if (!mainClass.isPresent()) {
            MarvelPlugin.get().getLogger().severe("Could not find main class for file: " + jar.getName());
            return null;
        }

        Enumeration<JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String className = entry.getName().replaceAll(".class", "").replace('/', '.');

            if (entry.getName().endsWith(".class")) {
                loader.loadClass(className);
            }
        }

        return registerPatch(mainClass.get(), file, loader);
    }

    private Patch<?> registerPatch(Class<? extends Patch> clazz, File file, URLClassLoader loader) throws IllegalAccessException, InstantiationException {
        Patch<?> patch = clazz.newInstance();
        PatchFlags flags = clazz.getAnnotation(PatchFlags.class);

        PatchSetting<Patch> setting = PatchSetting.of(patch, flags == null ? new Flag[0] : flags.values());
        PatchSave<?, ?> save = PatchSave.of(patch, setting, file, loader);

        MarvelPlugin.get().getLogger().info("Loaded Patch: " + patch.getName());
        MarvelPlugin.get().getPatchSaves().add(save);
        patch.onStartup();

        return patch;
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
