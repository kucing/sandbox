package nu.helmers.sandbox.activemq.producer;

/**  */
public class Producer2 extends AbstractProducer {

    public static final String QUEUE_NAME = Producer2.class.getSimpleName();

    String getQueueName() {
        return QUEUE_NAME;
    }
}
