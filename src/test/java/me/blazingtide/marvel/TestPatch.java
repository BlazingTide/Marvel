package me.blazingtide.marvel;

import me.blazingtide.marvel.patch.Patch;
import me.blazingtide.marvel.target.impl.SpigotTarget;

public class TestPatch implements Patch<SpigotTarget> {

    @Override
    public SpigotTarget getTarget() {
        return SpigotTarget.get();
    }

    @Override
    public void onStartup() {
        System.out.println("LOADED PATCH");
        System.out.println("LOADED PATCH");
        System.out.println("LOADED PATCH");
        System.out.println("LOADED PATCH");
        System.out.println("LOADED PATCH");
        System.out.println("LOADED PATCH");
        System.out.println("LOADED PATCH");
    }

    @Override
    public void onDisable() {

    }

    @Override
    public String getName() {
        return "Test Patch";
    }
}
