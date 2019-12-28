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
     */
    void onStartup();

    /**
     * Will be called pre server shut down or when the patch
     * is disabled or reloaded.
     */
    void onDisable();

}
