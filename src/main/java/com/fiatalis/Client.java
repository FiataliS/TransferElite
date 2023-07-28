package com.fiatalis;

import com.fiatalis.entity.ConnectAddress;
import com.fiatalis.entity.Directory;
import com.fiatalis.entity.ServerAddress;
import com.fiatalis.modelMessage.*;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import lombok.Data;

import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Data
public class Client {
    private List<String> clientView = new ArrayList<>();
    private List<String> serverView = new ArrayList<>();
    private Path clientDir = Paths.get(Directory.getInstance().getName());
    private ObjectEncoderOutputStream oos;
    private ObjectDecoderInputStream ois;
    private ServerAddress serverAddress = new ServerAddress();
    private boolean isAuthorized = false;
    Socket socket;

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

    public void connect(ConnectAddress connectAddress) {
        try {
            socket = new Socket(ConnectAddress.getInstance().getName(), Integer.parseInt(ConnectAddress.getInstance().getPort()));
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
            System.out.println("Сервер отсутствует");
        }
    }
    public void disconnect(){
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Соединение разорвано");
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
                        System.out.println("Загружен файл: " + fm.getName());
                        break;
                    case LIST:
                        ListMessage lm = (ListMessage) msg;
                        serverView = lm.getFiles();
                        break;
                    case AUTH_SERV:
                        AuthServ authServ = (AuthServ) msg;
                        isAuthorized = authServ.isAuth();
                        if (!authServ.isAuth()) {
                            System.out.println("Неверный логин и/или пороль");
                        }
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка чтения ответа сервера.");
        }
    }

    public void updateServerViewPath() {
        try {
            oos.writeObject(new ListMessage(clientDir));
        } catch (IOException e) {
            System.out.println("Ошибка соединения");
        }
    }

    public void authentication(String name, String pass) {
        if (!isAuthorized) {
            if (name.length() > 0) {
                try {
                    oos.writeObject(new AuthServ(name, pass, isAuthorized));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Пустые строки" + "Заполни логин и/или пороль");
                System.out.println("Введите команду set user");
            }
        } else {
            isAuthorized = false;
        }
    }

}
