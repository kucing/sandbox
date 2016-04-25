package nu.helmers.sandbox.activemq.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**  */
public class MessageProducer2 extends AbstractMessageProducer {

    public static final String QUEUE_NAME = MessageProducer2.class.getSimpleName();

    String getQueueName() {
        return QUEUE_NAME;
    }
}
