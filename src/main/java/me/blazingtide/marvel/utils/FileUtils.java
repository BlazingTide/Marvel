package me.blazingtide.marvel.utils;

import me.blazingtide.marvel.MarvelPlugin;
import org.apache.commons.lang.Validate;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

public class FileUtils {

    private static final String DIRECTORY_RAW_PATH = MarvelPlugin.get().getDataFolder().getAbsolutePath();

    public static File[] getJarsAsFiles() {
        File directory = new File(DIRECTORY_RAW_PATH);

        if (!directory.exists()) {
            return new File[]{};
        }

        Validate.notNull(directory.listFiles(), "Error while loading jars. Directory is null?");

        return directory.listFiles(pathname -> pathname.getName().endsWith(".jar"));
    }

    public static JarFile[] getJars(File[] files) throws IOException {
        JarFile[] jars = new JarFile[files.length];

        for (int i = 0; i < files.length; i++) {
            jars[i] = new JarFile(files[i]);
        }

        return jars;
    }

}
