package me.blazingtide.marvel.save;

import me.blazingtide.marvel.patch.Patch;
import me.blazingtide.marvel.settings.PatchSetting;

/**
 * Basic storage for patches so it's easier to call them for
 * future computations
 *
 * @param <K> Patch
 * @param <V> PatchSetting
 */
public interface PatchSave<K extends Patch, V extends PatchSetting> {

    K getPatch();

    V getSetting();

}
