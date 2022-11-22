package com.iranLabs.assignment.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Author : Hanieh Moafi
 * @Date : 10/14/2022
 */
public class MessageBundler {

    private static final ResourceBundle expBundler;

    static {
        Locale.setDefault(new Locale("fa", "IR"));
        expBundler = ResourceBundle.getBundle("exception_messages");

    }

    public MessageBundler() {
        ResourceBundle expBundler = ResourceBundle.getBundle("exception_messages");
    }

    public static String getExpMessage(String key)  {
        return expBundler.getString(key);

    }

}
