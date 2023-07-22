package com.fiatalis;

import com.fiatalis.listners.CommandPasser;
import com.fiatalis.repositories.UserDao;
import com.fiatalis.repositories.UserDaoImpl;
import com.fiatalis.utils.SessionFactoryUtils;
import com.fiatalis.utils.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();
//        try {
//            UserDao userDao = new UserDaoImpl(sessionFactoryUtils);
//        } catch (Exception e){
//            e.printStackTrace();
//        } finally {
//            sessionFactoryUtils.shotdown();
//        }


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> list;
        CommandPasser commandPasser = new CommandPasser();
        while (true) {
            Utils.addPrefix();
            list = Utils.parsing(reader.readLine());
            try {
                commandPasser.readerCommand(list.get(0));
            } catch (IllegalArgumentException e) {
                System.out.println("Команда не наедена");
            }
        }
    }

}