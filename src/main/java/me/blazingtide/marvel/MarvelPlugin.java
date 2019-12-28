package me.blazingtide.marvel;

import com.google.common.collect.Sets;
import lombok.Getter;
import me.blazingtide.marvel.loader.PatchLoader;
import me.blazingtide.marvel.save.PatchSave;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

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
        Bukkit.getLogger().info("Starting to load all patches.");
    }

    private final void loadPlugins() {

    }

}
