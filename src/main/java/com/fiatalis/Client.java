package com.fiatalis;

import com.fiatalis.entity.ServerAddress;
import com.fiatalis.modelMessage.*;
import com.fiatalis.utils.ThreadServerUtils;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import lombok.Data;

import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Data
public class Client {
    public List<String> clientView;
    public List<String> serverView;
    private Path clientDir = Paths.get("clientDir");
    private ObjectEncoderOutputStream oos;
    private ObjectDecoderInputStream ois;
    private ServerAddress serverAddress = new ServerAddress();
    private boolean isAuthorized = false;

    private static volatile Client instance;

    public static Client getInstance() {
        Client localInstance = instance;
        if (localInstance == null) {
            synchronized (ThreadServerUtils.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Client();
                }
            }
        }
        return localInstance;
    }

    public void connect(String address, int port) {
        try {
            Socket socket = new Socket(address, port);
            Thread readThread = new Thread(this::read);
            if (isAuthorized == false) {
                oos = new ObjectEncoderOutputStream(socket.getOutputStream());
                ois = new ObjectDecoderInputStream(socket.getInputStream());
                readThread.setDaemon(true);
                readThread.start();
            } else {
                socket.close();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void read() {
        try {
            while (true) {
                CloudMessage msg = (CloudMessage) ois.readObject();
                switch (msg.getMessageType()) {
                    case FILE:
                        FileMessage fm = (FileMessage) msg;
                        Files.write(clientDir.resolve(fm.getName()), fm.getBytes());
                        break;
                    case LIST:
                        ListMessage lm = (ListMessage) msg;
                        serverView = lm.getFiles();

                        break;
                    case AUTH_SERV:
                        AuthServ authServ = (AuthServ) msg;
                        isAuthorized = authServ.getAuth();
                        if (!authServ.getAuth()) {
                            System.out.println("Неверный логин и/или пороль");
                        }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateServerViewPath() {
        try {
            oos.writeObject(new ListMessage(clientDir));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
