package com.fiatalis.utils;

import com.fiatalis.entity.Language;
import com.fiatalis.entity.Skin;
import com.fiatalis.entity.User;

import java.util.ResourceBundle;

public class AuthServiceUtils {
    public static Boolean checkUser(String name, String pass) {
        User user = User.getInstance();
        int myHash = pass.hashCode();
        int dbHash = user.getPassHash();
        boolean skin = Skin.getInstance().getSkin();
        ResourceBundle rb = ResourceBundle.getBundle("consoleMsg", Language.getInstance().getLocate());
        if (user.getName().equals(name)) {
            if (myHash == dbHash) {
                if (!skin) {
                    Utils.printConsole(rb.getString("userConnect") + " " + name, true);
                }
                return true;
            }
            if (!skin) {
                Utils.printConsole(rb.getString("failedPasswd") + " " + name, true);
            }
            return false;
        }
        if (!skin) {
            Utils.printConsole(rb.getString("failedUser") + " " + name, true);
        }
        return false;
    }
}
