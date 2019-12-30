package me.blazingtide.marvel.bukkit;

import lombok.Data;
import me.blazingtide.marvel.patch.Patch;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Called when the server starts up or when all of the patches
 * are loaded at once.
 *
 * Better than calling event for every single patch at once
 */
@Data
public class LoadPatchesEvent extends Event {

    private final Patch[] patches;

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
