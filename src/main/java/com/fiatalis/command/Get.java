package com.fiatalis.command;

import com.fiatalis.client.Client;
import com.fiatalis.entity.Language;
import com.fiatalis.modelMessage.FileRequest;
import com.fiatalis.utils.Utils;

import java.io.IOException;
import java.util.ResourceBundle;

public class Get extends CommandRun {
    boolean isDelete = false;

    public Get(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void optionsHandler() {
        if (attribute.getOptions() == OptionsEnum.DELETE) {
            isDelete = true;
        }
        get();
    }

    private void get() {
        ResourceBundle rb = ResourceBundle.getBundle("consoleMsg", Language.getInstance().getLocate());
        if (checkFile(attribute.getAttribute())) {
            try {
                Client.getInstance().getOos().writeObject(new FileRequest(attribute.getAttribute(), isDelete));
            } catch (IOException e) {
                Utils.printConsole(rb.getString("failedDownload"), true);
            }
        } else {
            Utils.printConsole(rb.getString("failedName"), true);
        }
    }

    private Boolean checkFile(String fileName) {
        return Client.getInstance().getServerView().stream().anyMatch(s -> s.equals(fileName));
    }
}
