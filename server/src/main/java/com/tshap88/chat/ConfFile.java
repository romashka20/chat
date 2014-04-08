package com.tshap88.chat;

import java.io.*;
import java.util.*;

public class ConfFile {
    private static int PORT;
    private static String ADDRESS;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("properties.txt"));
            PORT = Integer.parseInt(properties.getProperty("PORT"));
            ADDRESS = properties.getProperty("ADDRESS");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getADDRESS() {
        return ADDRESS;
    }

    public static int getPORT() {
        return PORT;
    }
}
