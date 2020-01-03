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

    static <K extends Patch, V extends PatchSetting> PatchSave<K, V> of(K k, V v) {
        return new PatchSave<K, V>() {
            @Override
            public K getPatch() {
                return k;
            }

            @Override
            public V getSetting() {
                return v;
            }
        };
    }

}
