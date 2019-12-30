package me.blazingtide.marvel;

import com.google.common.collect.Sets;
import lombok.Getter;
import me.blazingtide.marvel.loader.PatchLoader;
import me.blazingtide.marvel.patch.Patch;
import me.blazingtide.marvel.save.PatchSave;
import me.blazingtide.marvel.utils.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Set;
import java.util.jar.JarFile;

@Getter
public class MarvelPlugin extends JavaPlugin {

    private static MarvelPlugin instance;

    private final Set<PatchSave<?, ?>> patchSaves = Sets.newHashSet();
    private final PatchLoader loader = new PatchLoader();

    public static MarvelPlugin get() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getLogger().info("Starting to load all patches.");
    }

    /**
     * Loads all of the patches at once, should only be called once (On ServerStartUp)
     *
     * @see me.blazingtide.marvel.MarvelPlugin
     */
    @Deprecated
    private void loadPatches() throws IOException {
        JarFile[] jars = FileUtils.getJars(FileUtils.getJarsAsFiles());
        Patch[] patches = new Patch[jars.length];

        for (int i = 0; i < jars.length; i++) {
            JarFile jar = jars[i];

            if (jar != null) {
                //TODO: Finish loading
            } else {
                Bukkit.getLogger().severe("Found a jar file that's null. (Super weird)");
            }
        }
    }

}
