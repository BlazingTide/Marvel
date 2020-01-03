package me.blazingtide.marvel;

import org.bukkit.Bukkit;

public class TestThread extends Thread {

    public TestThread() {
        super("Test thread");
    }

    @Override
    public void run() {
        while (true) {
            Bukkit.broadcastMessage("THIS IS WORKING");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
