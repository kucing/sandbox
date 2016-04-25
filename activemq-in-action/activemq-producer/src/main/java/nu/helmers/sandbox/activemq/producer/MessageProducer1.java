package nu.helmers.sandbox.activemq.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**  */
public class MessageProducer1 extends AbstractMessageProducer {

    public static final String QUEUE_NAME = MessageProducer1.class.getSimpleName();

    String getQueueName() {
        return QUEUE_NAME;
    }

}
