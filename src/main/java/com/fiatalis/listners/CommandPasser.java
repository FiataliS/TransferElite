package com.fiatalis.listners;

import com.fiatalis.EchoServer;

public class CommandPasser {
    Thread thread = null;

    public void readerCommand(String s) throws IllegalArgumentException {
        if (s.equals("")) {return;}
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
                if (thread == null) {
                    thread = new Thread(new EchoServer(), "server");
                }
                if (thread.isAlive()) {
                    System.out.println("Already working");
                    break;
                }
                thread.start();
                break;
            case STOP:
                try {
                    thread.interrupt();
                    thread = null;
                } catch (NullPointerException e) {
                    System.out.println("Please enter start");
                }
                break;

        }
    }

}
