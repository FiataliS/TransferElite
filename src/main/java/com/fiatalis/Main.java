package com.fiatalis;

import com.fiatalis.client.Client;
import com.fiatalis.client.CommandHandler;
import com.fiatalis.entity.*;
import com.fiatalis.entity.Skin;
import com.fiatalis.server.EchoServer;
import com.fiatalis.utils.ConfigUtils;
import com.fiatalis.utils.Utils;
import com.fiatalis.windows.Controller;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        readInstance();
        if (Skin.getInstance().getSkin()) {
           window();
        } else {
           console();
        }
    }

    private static void window(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        javax.swing.SwingUtilities.invokeLater(() -> Controller.getInstance().startWindows());
    }


    private static void console() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CommandHandler commandHandler;
        while (true) {
            Utils.addPrefix();
            commandHandler = new CommandHandler(reader.readLine());
            commandHandler.commandDefinition();
        }
    }

    private static void readInstance(){
        ConfigUtils.getInstance();
        Skin.getInstance();
        User.getInstance();
        Directory.getInstance();
        Server.getInstance();
        Connect.getInstance();
        Language.getInstance();
        Client.getInstance();
        EchoServer.getInstance();
    }

}