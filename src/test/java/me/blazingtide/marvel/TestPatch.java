package me.blazingtide.marvel;

import me.blazingtide.marvel.flags.Flag;
import me.blazingtide.marvel.flags.PatchFlags;
import me.blazingtide.marvel.patch.Patch;
import me.blazingtide.marvel.target.impl.SpigotTarget;

@PatchFlags(values = Flag.DO_NOT_LOAD_ON_PLUGIN_STARTUP)
public class TestPatch implements Patch<SpigotTarget> {

    @Override
    public SpigotTarget getTarget() {
        return SpigotTarget.get();
    }

    @Override
    public void onStartup() {
        System.out.println("LOADED PATCH");

//        new TestThread().start();
    }

    @Override
    public void onDisable() {

    }

    @Override
    public String getName() {
        return "Test";
    }
}
