package com.fiatalis.command;

import com.fiatalis.entity.*;
import com.fiatalis.entity.Connect;
import com.fiatalis.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class Set extends CommandRun {
    public Set(Attribute attribute) {
        super(attribute);
    }

    ResourceBundle rb;

    @Override
    public void optionsHandler() {
        rb = ResourceBundle.getBundle("consoleMsg", Language.getInstance().getLocate());
        if (attribute.getOptions() == null) {
            Utils.printConsole(rb.getString("failedOptions"), true);
            return;
        }
        switch (attribute.getOptions()) {
            case CONNECT:
                connect();
                break;
            case DIR:
                dir();
                break;
            case USER:
                user();
                break;
            case SERVER:
                server();
                break;
            case SHELL:
                shell();
                break;
            case LANG:
                lang();
        }
        new Save(new Attribute("save opt")).optionsHandler();
    }

    private void lang() {
        if (Language.getInstance().getLanguage().toUpperCase().equals("RU")) {
            Language.getInstance().setLanguage("ENG");
        } else {
            Language.getInstance().setLanguage("RU");
        }
    }

    private void shell() {
        if (Skin.getInstance().getSkin()) {
            Skin.getInstance().setSkin(false);
            Utils.printConsole(rb.getString("shellOff"), true);
        } else {
            Skin.getInstance().setSkin(true);
            Utils.printConsole(rb.getString("shellOn"), true);
        }
    }

    private void user() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Utils.printConsole(rb.getString("nameUser"), false);
            User.getInstance().setName(reader.readLine().trim());
            Utils.printConsole(rb.getString("passwd"), false);
            User.getInstance().setPassword(reader.readLine().trim());
        } catch (IOException e) {
        }
    }

    private void server() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Utils.printConsole(rb.getString("addServer"), false);
            Server.getInstance().setName(reader.readLine());
            Utils.printConsole(rb.getString("addPort"), false);
            Server.getInstance().setPort(reader.readLine());
        } catch (IOException e) {
        }
    }

    private void dir() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name;
        try {
            Utils.printConsole(rb.getString("editDir"), false);
            name = reader.readLine();
            if (name.length() < 1) {
                name = null;
            }
            Directory.getInstance().setName(name);
        } catch (IOException e) {
        }
    }


    private void connect() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = null, port = null;
        try {
            Utils.printConsole(rb.getString("addServer"), false);
            name = reader.readLine();
            if (name.length() < 1) name = null;
            Utils.printConsole(rb.getString("addPort"), false);
            port = reader.readLine();
            if (port.length() < 1) port = null;
        } catch (IOException e) {
        }
        Connect.getInstance().setName(name);
        Connect.getInstance().setPort(port);
    }
}
