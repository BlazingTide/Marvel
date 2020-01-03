package me.blazingtide.marvel.thread;

import me.blazingtide.marvel.thread.action.ThreadAction;

import javax.annotation.Nullable;

/**
 * We use this so it's easier to keep track of the threads
 * that each patch creates
 */
public class ThreadFactory {

    public void create(Runnable runnable) {

    }

    public void create(Runnable runnable, @Nullable ThreadAction action) {
        //TODO: Finish
    }

}
