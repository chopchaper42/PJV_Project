package Logs;

import java.io.*;
import java.time.LocalDateTime;

//import static Engine.Parameters.LOGS_STREAM;

public class Logger
{
    private static final PrintStream outStream = System.err;

    public static void log(String text)
    {
            LocalDateTime now = LocalDateTime.now();
            outStream.println(now + ": " + text + "\n");
    }
}
