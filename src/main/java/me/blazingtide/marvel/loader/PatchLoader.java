package me.blazingtide.marvel.loader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Handles the loading of the jar files into run-time
 * also handles the flags & registering patches
 */
public class PatchLoader {

    public void run(File file) throws MalformedURLException {
        URLClassLoader loader = new URLClassLoader(new URL[]{file.toURI().toURL()}, this.getClass().getClassLoader());

        
    }

}
