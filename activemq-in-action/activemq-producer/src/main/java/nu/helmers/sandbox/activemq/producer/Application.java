package nu.helmers.sandbox.activemq.producer;

import java.util.Timer;
import java.util.TimerTask;

/**  */
public class Application {

    private static final int INTERVAL_IN_MILLIS_PRODUCER_1 = 20000;
    private static final int INTERVAL_IN_MILLIS_PRODUCER_2 = 35000;
    private static final int DELAY_IN_MILLIS = 1000;


    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new ProducerRunner(new Producer1()), DELAY_IN_MILLIS, INTERVAL_IN_MILLIS_PRODUCER_1);
        timer.scheduleAtFixedRate(new ProducerRunner(new Producer2()), DELAY_IN_MILLIS, INTERVAL_IN_MILLIS_PRODUCER_2);
    }

    private static class ProducerRunner extends TimerTask {
        long counter = 0;
        private final AbstractProducer producer;

        ProducerRunner(AbstractProducer producer) {
            this.producer = producer;
        }

        @Override
        public void run() {
            producer.send(String.format("Message %d from %s", counter++, producer.getClass().getSimpleName()));
        }
    };
}
