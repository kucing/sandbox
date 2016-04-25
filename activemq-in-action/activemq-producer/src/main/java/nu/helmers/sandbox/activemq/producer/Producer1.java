package nu.helmers.sandbox.activemq.producer;

import java.util.logging.Logger;

/**  */
public class Producer1 extends AbstractProducer {

    public static final String QUEUE_NAME = Producer1.class.getSimpleName();

    String getQueueName() {
        return QUEUE_NAME;
    }

}
