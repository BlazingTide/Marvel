package me.blazingtide.marvel;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.SneakyThrows;
import me.blazingtide.library.command.base.CloverCommandHandler;
import me.blazingtide.marvel.command.PatchCommand;
import me.blazingtide.marvel.loader.PatchLoader;
import me.blazingtide.marvel.patch.Patch;
import me.blazingtide.marvel.save.PatchSave;
import me.blazingtide.marvel.utils.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Set;

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
        getLogger().info("Starting to load all patches.");


        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        loadPatches();

        CloverCommandHandler.register(new PatchCommand());
    }

    @SneakyThrows
    @Override
    public void onDisable() {
        for (PatchSave<?, ?> patchSave : patchSaves) {
            patchSave.getPatch().onDisable();
            loader.unload(patchSave);
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
        Patch[] patches = new Patch[files.length];

        for (File file : files) {
            if (file != null && file.exists()) {
                try {
                    loader.load(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                getLogger().severe("Found a file that's null or does not exist. (Super weird)");
            }
        }
    }

}
