package nu.helmers.sandbox.activemq.producer;

import java.util.Timer;
import java.util.TimerTask;

/**  */
public class Application {

    private static final int intervalInMillis_Producer1 = 20000;
    private static final int intervalInMillis_Producer2 = 35000;


    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Producer1Runner(), 1000, intervalInMillis_Producer1);
        timer.scheduleAtFixedRate(new Producer2Runner(), 1000, intervalInMillis_Producer2);
    }

    private static class Producer1Runner extends TimerTask {
        MessageProducer1 producer1 = new MessageProducer1();
        long counter = 0;

        @Override
        public void run() {
            producer1.send(String.format("Message %d from producer1", counter++));
        }
    };

    private static class Producer2Runner extends TimerTask {
        MessageProducer2 producer2 = new MessageProducer2();
        long counter = 0;

        @Override
        public void run() {
            producer2.send(String.format("Message %d from producer2", counter++));
        }
    };

}
