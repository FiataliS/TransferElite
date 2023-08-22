package com.fiatalis.windows;

public class Controller {
    private Windows windows;

    public void start() {
        windows = new Windows();
        windows.getFrame().setVisible(true);
    }
}
