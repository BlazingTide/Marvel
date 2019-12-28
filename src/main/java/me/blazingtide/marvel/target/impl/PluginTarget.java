package me.blazingtide.marvel.target.impl;

import me.blazingtide.marvel.target.PatchTarget;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Used to better filter between patch associated with each plugin.
 *
 * @param <T> Plugin that is associated with the patch.
 */
public interface PluginTarget<T extends JavaPlugin> extends PatchTarget {

    T getPlugin();

    default String getTargetId() {
        return getPlugin().getName();
    }

}
