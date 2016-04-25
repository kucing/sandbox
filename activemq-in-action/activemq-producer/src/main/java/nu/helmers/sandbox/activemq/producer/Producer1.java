package nu.helmers.sandbox.activemq.producer;

/**  */
public class Producer1 extends AbstractProducer {

    public static final String QUEUE_NAME = Producer1.class.getSimpleName();

    String getQueueName() {
        return QUEUE_NAME;
    }

}
