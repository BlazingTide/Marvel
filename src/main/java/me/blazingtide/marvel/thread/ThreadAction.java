package me.blazingtide.marvel.thread;

public class ThreadAction {

    public static ThreadAction RUN_TASK_TIMER = new ThreadAction();

    public ThreadAction of(long time) {
        return this;
    }

}
