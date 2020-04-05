package com.nhlstenden.bmdb.rcaller;

import com.github.rcaller.rstuff.RCaller;
import com.github.rcaller.rstuff.RCode;

import javafx.scene.image.Image;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class RManager {

    /**
     * Called when class is called for the first time
     * Initializes libraries needed for the R scripts
     */
    static {
        try {
            executeRScript("init");
            new File("./plots/").mkdir();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    //Constructor
    private RManager() {}

    /**
     * Get the Image object generated from the given file name.
     * @param file_name The 'file_name' of the question for which to generate the plot.
     * @return An Image object of the generated plot.
     */
    public static Image getRPlot(String file_name) {
        try {
            return tryGetRPlot(file_name);
        } catch(IOException e) {
            //Catch and print exception
            Logger.getGlobal().log(Level.SEVERE, e.toString());
            return null;
        }
    }

    /**
     * Attempt to generate the plot associated with the given file name.
     * @param file_name The 'file_name' of the question for which to generate a plot.
     * @return An Image object of the generated plot.
     * @throws IOException Thrown when the R Script associated with 'file_name' could not be read.
     */
    private static Image tryGetRPlot(String file_name) throws IOException {
        executeRScript(file_name);
        return new Image("file:./plots/" + file_name + ".png");
    }

    /**
     * Execute the r script through the RCaller library
     * @param file_name The 'file_name' of the question for which to execute.
     * @throws IOException Thrown when the R Script associated with 'file_name' could not be executed.
     */
    private static void executeRScript(String file_name) throws IOException {
        //Create RCaller object
        RCaller caller = RCaller.create();
        //Set loaded script in caller
        caller.setRCode(loadRScript(file_name));
        //Run RCode
        caller.runOnly();
        return;
    }

    /**
     * Load the r script from resources in to the RCode object.
     * @param file_name
     * @return RCode object filled with r file lines
     * @throws IOException Thrown when the R Script associated with 'file_name' could not read
     */
    private static RCode loadRScript(String file_name) throws IOException {
        //Create RCode object
        RCode code = RCode.create();

        //Try to read file in resources directory
        try (InputStream stream = RManager.class.getResourceAsStream("/r/" + file_name + ".R");
             //Create reader
             InputStreamReader streamReader = new InputStreamReader(stream);
             BufferedReader reader = new BufferedReader(streamReader)) {
            //Read resource file per line
            for (String line; (line = reader.readLine()) != null; )
                //Add the line to RCode
                code.addRCode(line);
        } catch (Exception e) {
            //Catch and print exception
            throw new IOException("Failed to load R Script: " + file_name + ".R", e);
        }
        //Return te RCode object
        return code;
    }
}
