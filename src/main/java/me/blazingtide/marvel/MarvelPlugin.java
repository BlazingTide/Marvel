package me.blazingtide.marvel;

import com.google.common.collect.Sets;
import lombok.Getter;
import me.blazingtide.marvel.loader.PatchLoader;
import me.blazingtide.marvel.save.PatchSave;
import me.blazingtide.marvel.thread.ThreadFactory;
import me.blazingtide.marvel.utils.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Set;

@Getter
public class MarvelPlugin extends JavaPlugin {

    private static MarvelPlugin instance;

    private final Set<PatchSave<?, ?>> patchSaves = Sets.newHashSet();
    private final PatchLoader loader = new PatchLoader();
    private final ThreadFactory threadFactory = new ThreadFactory();

    public static MarvelPlugin get() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Starting to load all patches.");


        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        loadPatches();
    }

    @Override
    public void onDisable() {
        for (PatchSave<?, ?> patchSave : patchSaves) {
            patchSave.getPatch().onDisable();
        }
        getLogger().info("Disabled all patches.");
    }

    /**
     * Loads all of the patches at once, should only be called once (On ServerStartUp)
     *
     * @see me.blazingtide.marvel.MarvelPlugin
     */
    @Deprecated
    private void loadPatches() {
        File[] files = FileUtils.getJarsAsFiles();

        for (File file : files) {
            if (file != null && file.exists()) {
                loader.run(file);
            } else {
                getLogger().severe("Found a file that's null or does not exist. (Super weird)");
            }
        }
    }

}
