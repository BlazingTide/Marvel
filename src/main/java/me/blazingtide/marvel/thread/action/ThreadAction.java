package me.blazingtide.marvel.thread.action;

public abstract class ThreadAction {

    protected final Runnable runnable;

    protected ThreadAction(Runnable runnable) {
        this.runnable = runnable;
    }

    public abstract void run();

}
