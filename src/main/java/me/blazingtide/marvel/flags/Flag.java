package me.blazingtide.marvel.flags;

public enum Flag {

    /**
     * This flag will make the patch disable if
     * it's target is a Plugin and if the plugin is not
     * loaded on the server.
     *
     * Should always been included when using a PatchTarget
     * @see me.blazingtide.marvel.target.PatchTarget
     */
    DISABLE_ON_UNKNOWN_PLUGIN,

    /**
     * Makes the patch not load when the server is initally started.
     *
     * @see me.blazingtide.marvel.MarvelPlugin
     */
    DO_NOT_LOAD_ON_PLUGIN_STARTUP,

}
