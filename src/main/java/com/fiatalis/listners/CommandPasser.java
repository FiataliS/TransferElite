package com.fiatalis.listners;

import com.fiatalis.EchoServer;

import java.util.TreeMap;

public class CommandPasser  {
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("start server");
        }
    });

    public void readerCommand(String s) throws IllegalArgumentException, InterruptedException{
        switch (Commands.valueOf(s.toUpperCase())) {
            case LS:
                System.out.println("Список файлов");
                break;
            case CD:
                System.out.println("переход в папку");
                break;
            case RM:
                System.out.println("Удалить файл, папку");
                break;
            case MKDIR:
                System.out.println("Создать папку");
                break;
            case CAT:
                System.out.println("Прочитать файл");
                break;
            case START:
                if (thread.isAlive()) {
                    System.out.println("Already working");
                    break;
                }
                thread.start();
                break;
            case STOP:
                try {
                    thread.interrupt();
                    System.out.println("Сервер остановлен");
                } catch (NullPointerException e) {
                    System.out.println("Please enter start");
                }
                break;

        }
    }

}
