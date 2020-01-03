package me.blazingtide.marvel.patch;

import me.blazingtide.marvel.target.PatchTarget;

/**
 * Patch
 * @param <T>
 */
public interface Patch<T extends PatchTarget> {

    /**
     * Get the target that the patch is associated with
     * @return PatchTarget
     */
    T getTarget();

    /**
     * Will be called on start up or when the patch is reloaded
     * @see me.blazingtide.marvel.loader.PatchLoader
     */
    void onStartup();

    /**
     * Will be called pre server shut down or when the patch
     * is disabled or reloaded.
     */
    void onDisable();

    /**
     * @return Name which the patch is assigned
     */
    String getName();

}
