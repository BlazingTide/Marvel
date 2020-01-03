package me.blazingtide.marvel.settings;

import me.blazingtide.marvel.flags.Flag;
import me.blazingtide.marvel.patch.Patch;

public interface PatchSetting<T extends Patch> {

    T getPatch();

    Flag[] getFlags();

    static <T extends Patch> PatchSetting of(T patch, Flag[] flags) {
        return new PatchSetting<T>() {
            @Override
            public T getPatch() {
                return patch;
            }

            @Override
            public Flag[] getFlags() {
                return flags;
            }
        };
    }

}
