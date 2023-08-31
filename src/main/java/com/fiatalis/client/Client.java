package com.fiatalis.client;

import com.fiatalis.entity.*;
import com.fiatalis.modelMessage.*;
import com.fiatalis.utils.Utils;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import lombok.Data;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Data
public class Client {
    private Path clientDir = Paths.get(Directory.getInstance().getName());
    private List<String> clientView = new ArrayList<>();
    private List<String> serverView = new ArrayList<>();
    private ObjectEncoderOutputStream oos;
    private ObjectDecoderInputStream ois;
    private Server server = new Server();
    private boolean isAuthorized = false;
    private boolean isTransfer = false;
    Socket socket;
    private boolean skin = Skin.getInstance().getSkin();
    private ResourceBundle rb = ResourceBundle.getBundle("consoleMsg", Language.getInstance().getLocate());

    private static volatile Client instance;


    public static Client getInstance() {
        Client localInstance = instance;
        if (localInstance == null) {
            synchronized (Client.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Client();
                }
            }
        }
        return localInstance;
    }

    public void connect(Connect connect) {
        try {
            InetAddress inetAddress = InetAddress.getByName(Connect.getInstance().getName());
            if (!inetAddress.isReachable(1000)) return;
        } catch (IOException e) {
        }
        try {
            socket = new Socket(Connect.getInstance().getName(), Integer.parseInt(Connect.getInstance().getPort()));
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
            if (!skin) {
                Utils.printConsole(rb.getString("serverNotFound"), true);
            }
        }
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            if (!skin) {
                Utils.printConsole(rb.getString("serverDropped"), true);
            }
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
                        if (!skin) {
                            Utils.printConsole(rb.getString("DownloadComplete") + " " + fm.getName(), true);
                        }
                        break;
                    case LIST:
                        ListMessage lm = (ListMessage) msg;
                        serverView = lm.getFiles();
                        isTransfer = false;
                        break;
                    case AUTH_SERV:
                        AuthServ authServ = (AuthServ) msg;
                        isAuthorized = authServ.isAuth();
                        if (!authServ.isAuth()) {
                            if (!skin) {
                                Utils.printConsole(rb.getString("filedAuth"), true);
                            }
                        }
                }
            }
        } catch (Exception e) {
            if (!skin) {
                Utils.printConsole(rb.getString("failedRead"), true);
            }
        }
    }

    public void updateServerViewPath() {
        try {
            isTransfer = true;
            oos.writeObject(new ListMessage(clientDir));
        } catch (IOException e) {
            if (!skin) {
                Utils.printConsole(rb.getString("errorConnect"), true);
            }
        }
    }

    public List<String> updateClientViewPath() {
        ListMessage listMessage;
        try {
            listMessage = new ListMessage(clientDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clientView = listMessage.getFiles();
        return clientView;
    }

    public void authentication(String name, String pass) {
        if (!isAuthorized) {
            if (name.length() > 0) {
                try {
                    oos.writeObject(new AuthServ(name, pass, isAuthorized));
                } catch (NullPointerException e) {
                } catch (IOException e) {
                }
            } else {
                if (!skin) {
                    Utils.printConsole(rb.getString("errorLoginOrPasswd"), true);
                }
            }
        } else {
            isAuthorized = false;
        }
    }
}
