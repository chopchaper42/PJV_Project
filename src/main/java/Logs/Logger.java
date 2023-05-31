package Logs;

import java.io.*;
import java.time.LocalDateTime;

//import static Engine.Parameters.LOGS_STREAM;

public class Logger
{
    private static boolean enabled = false;
    private static PrintStream stream = System.out;

    /**
     * Logs the given text to the specified output
     * @param text a text of the log
     */
    public static void log(String text)
    {
        if (enabled) {
            LocalDateTime now = LocalDateTime.now();
            stream.println(now + ": " + text);
        }
    }

    public static void setEnabled(boolean enabled) {
        Logger.enabled = enabled;
        log("Logger enabled.");
    }
    public static void setOutput(PrintStream stream) {
        Logger.stream = stream;
    }
}
