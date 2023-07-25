package com.fiatalis.utils;


public class ThreadServerUtils {
    private Thread thread = null;

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    private static volatile ThreadServerUtils instance;

    public static ThreadServerUtils getInstance() {
        ThreadServerUtils localInstance = instance;
        if (localInstance == null) {
            synchronized (ThreadServerUtils.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ThreadServerUtils();
                }
            }
        }
        return localInstance;
    }
}
