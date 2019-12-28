package me.blazingtide.marvel.target.impl;

import me.blazingtide.marvel.target.PatchTarget;

/**
 * This should only be used for patches that directly fix a bug
 * that is related to the spigot jar itself, should never be used
 * for anything else.
 *
 * @Deprecated This plugin should not be used for spigot patches
 */
@Deprecated
public class SpigotTarget implements PatchTarget {

    @Override
    public String getTargetId() {
        return "Spigot";
    }

}
