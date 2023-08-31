package com.fiatalis.windows;

import lombok.Getter;

@Getter
public class Controller {

    private static volatile Controller instance;

    private MainWindows windows;
    private OptionsWindows optionsWindows;

    public static Controller getInstance() {
        Controller localInstance = instance;
        if (localInstance == null) {
            synchronized (Controller.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Controller();
                }
            }
        }
        return localInstance;
    }


    public void startWindows() {
        windows = new MainWindows();
        windows.setVisible(true);
    }

    public void startOptions() {
        optionsWindows = new OptionsWindows();
        optionsWindows.setVisible(true);
    }
}
