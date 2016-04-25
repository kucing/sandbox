package nu.helmers.sandbox.activemq.consumer;

import nu.helmers.sandbox.activemq.common.ActiveMQConstants;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static nu.helmers.sandbox.activemq.common.ActiveMQConstants.BROKER_URL;
import static nu.helmers.sandbox.activemq.common.ActiveMQConstants.RECEIVE_TIMEOUT_IN_MILLIS;

/**  */
public abstract class AbstractSynchronousConsumer extends AbstractConsumer {

    public AbstractSynchronousConsumer(String queueName) {
        super(queueName);
    }

    public void receive() {
        Message message;
        try {
            message = consumer.receive(RECEIVE_TIMEOUT_IN_MILLIS);
        } catch (JMSException e) {
            logger.log(Level.SEVERE, "Error while receiving message.", e);
            return;
        }

        if (message == null) {
            logger.info("Message is null; probably timeout on queue " + this.queueName);
            return;
        }

        logMessage(message);
    }
}
