package com.fiatalis.command;

import com.fiatalis.client.Client;
import com.fiatalis.entity.Language;
import com.fiatalis.modelMessage.FileMessage;
import com.fiatalis.modelMessage.FileRequest;
import com.fiatalis.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class Put extends CommandRun {
    public Put(Attribute attribute) {
        super(attribute);
    }

    boolean isDelete = false;

    private void put() {
        ResourceBundle rb = ResourceBundle.getBundle("consoleMsg", Language.getInstance().getLocate());
        if (Client.getInstance().getClientDir().resolve(attribute.getAttribute()).toFile().isDirectory() && checkFile(attribute.getAttribute())) {
            Utils.printConsole(rb.getString("failedFolder"), true);
        } else if (checkFile(attribute.getAttribute())) {
            String item = attribute.getAttribute();
            File selected = Client.getInstance().getClientDir().resolve(item).toFile();
            try {
                Client.getInstance().getOos().writeObject(new FileMessage(selected.toPath()));
            } catch (IOException e) {
                Utils.printConsole(rb.getString("failedPut") + " " + item, true);
            }
            deleteFile(selected, item);
            Client.getInstance().updateServerViewPath();
        } else {
            System.out.println("!");
        }

        if (checkFile(attribute.getAttribute())) {
            try {
                Client.getInstance().getOos().writeObject(new FileRequest(attribute.getAttribute(), isDelete));
            } catch (IOException e) {
                Utils.printConsole(rb.getString("failedUpload"), true);
            }
        } else {
            Utils.printConsole(rb.getString("failedName"), true);
        }
    }

    @Override
    public void optionsHandler() {
        if (attribute.getOptions() == OptionsEnum.DELETE) isDelete = true;
        put();
    }

    private Boolean checkFile(String fileName) {
        for (String s : Client.getInstance().updateClientViewPath()) {
            if (s.equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    private void deleteFile(File file, String name) {
        ResourceBundle rb = ResourceBundle.getBundle("consoleMsg", Language.getInstance().getLocate());
        if (isDelete) {
            if (file.delete()) {
                Utils.printConsole(rb.getString("file") + ": " + name + " " + rb.getString("delete"), true);
            }
        }
    }
}
