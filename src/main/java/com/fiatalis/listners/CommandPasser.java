package com.fiatalis.listners;

public class CommandPasser {
    public void readerCommand(String s) throws IllegalArgumentException{
        switch (Commands.valueOf(s.toUpperCase())){
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
        }
    }

}
