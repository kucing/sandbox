package nu.helmers.sandbox.activemq.common;

/**  */
public class LoggingUtils {

    /** See https://docs.oracle.com/javase/7/docs/api/java/util/logging/SimpleFormatter.html */
    public static void initializeLogFormat() {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$.6s [%3$s] %5$s%6$s%n");
    }


}
