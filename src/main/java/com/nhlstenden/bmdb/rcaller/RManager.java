package com.nhlstenden.bmdb.rcaller;

import com.github.rcaller.rstuff.RCaller;
import com.github.rcaller.rstuff.RCode;
import examples.Main;
import javafx.scene.image.Image;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public final class RManager {

    public static RManager _instance = null;
    private static RCode code;
    private static RCaller caller;

    static{
        try {
            caller = RCaller.create();
            code = RCode.create();

            System.out.println("succeeded to init RCaller and/or RCode");
        } catch(Exception e) {
            e.getMessage();
        }
    }

    private RManager(){}

    public static Image getRPlot(String file_name) {
        LinesToRCode(file_name);
        caller.setRCode(code);
        caller.runOnly();

        code = RCode.create();

        return new Image("file:./plots/" + file_name + ".png");
    }

    private static void LinesToRCode (String file_name) {
        InputStream stream = RManager.class.getResourceAsStream("/r/" + file_name + ".R");
        InputStreamReader streamReader = new InputStreamReader(stream);
        try (BufferedReader reader = new BufferedReader(streamReader)) {
            for (String line; (line = reader.readLine()) != null;) {
                System.out.println(line);
                code.addRCode(line);
            }
        } catch (Exception e) {

        }
    }
}
