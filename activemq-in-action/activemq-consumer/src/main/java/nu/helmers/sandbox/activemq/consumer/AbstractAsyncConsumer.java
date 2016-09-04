package nu.helmers.sandbox.activemq.consumer;

import javax.jms.*;
import java.util.logging.Level;

import static nu.helmers.sandbox.activemq.common.ActiveMQConstants.RECEIVE_TIMEOUT_IN_MILLIS;

/**  */
public abstract class AbstractAsyncConsumer extends AbstractConsumer implements MessageListener {

    public AbstractAsyncConsumer(String queueName) {
        super(queueName);
    }

    protected void initialize() {
        super.initialize();
        try {
            // Instead of calling consumer.receive() actively, we set a MessageListener on the consumer.
            consumer.setMessageListener(this);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    public void onMessage(Message message) {
        logMessage(message);
    }
}
