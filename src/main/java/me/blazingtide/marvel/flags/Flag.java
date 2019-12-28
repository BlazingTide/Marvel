package me.blazingtide.marvel.flags;

public enum Flag {

    /**
     * This flag will make the patch disable if
     * it's target is a Plugin and if the plugin is not
     * loaded on the server.
     *
     * Should always been included when using a PluinTarget
     */
    DISABLE_ON_UNKNOWN_PLUGIN,
    /**
     *
     */
    DO_NOT_LOAD_ON_PLUGIN_STARTUP,

}
