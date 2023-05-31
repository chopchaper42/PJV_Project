//package network.udp.server;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class ServerLogger
//{
//    private static final String LOG_FILE = "./src/main/logs/log.txt";
//    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    public static void log(String message) {
//        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
//            String timestamp = DATE_FORMAT.format(new Date());
//            writer.println(timestamp + " - " + message);
//            System.out.println(timestamp + " - " + message); // duplicate to console
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
