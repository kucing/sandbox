package nu.helmers.sandbox.activemq.consumer;

import nu.helmers.sandbox.activemq.common.LoggingUtils;

import java.util.Timer;
import java.util.TimerTask;

/**  */
public class Application {

    private static final int INTERVAL_IN_MILLIS_CONSUMER_1 = 3 * 1000;
    private static final int INTERVAL_IN_MILLIS_CONSUMER_2 = 11 * 1000;
    private static final int DELAY_IN_MILLIS = 1000;

    public static void main(String[] args) {
        LoggingUtils.initializeLogFormat();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new ConsumerRunner(new Consumer1a()), DELAY_IN_MILLIS, INTERVAL_IN_MILLIS_CONSUMER_1);
        timer.scheduleAtFixedRate(new ConsumerRunner(new Consumer1b()), DELAY_IN_MILLIS, INTERVAL_IN_MILLIS_CONSUMER_2);

        // No need to do anything with this consumer: It is async and contains a listener...
        Consumer2Async asyncConsumerQueue2 = new Consumer2Async();
    }

    private static class ConsumerRunner extends TimerTask {
        long counter = 0;
        private final AbstractSynchronousConsumer consumer;

        ConsumerRunner(AbstractSynchronousConsumer consumer) {
            this.consumer = consumer;
        }

        @Override
        public void run() {
            consumer.receive();
        }
    };
}
