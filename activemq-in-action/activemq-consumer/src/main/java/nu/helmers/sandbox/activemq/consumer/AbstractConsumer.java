package nu.helmers.sandbox.activemq.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static nu.helmers.sandbox.activemq.common.ActiveMQConstants.BROKER_URL;

/**  */
public class AbstractConsumer {

    protected MessageConsumer consumer;
    protected String queueName;
    protected Logger logger;
    protected Connection connection;
    protected Session session;

    public AbstractConsumer(String queueName) {
        this.queueName = queueName;
        initialize();
    }

    protected void initialize() {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(queueName);
            consumer = session.createConsumer(queue);

            // No messages can be received until connection has 'started'.
            connection.start();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }

        logger = Logger.getLogger(this.getClass().getSimpleName());
    }

    public void close() {
        try {
            this.consumer.close();
            this.session.close();
            this.connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    protected void logMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            logger.info(String.format("Received message: '%s'", textMessage.getText()));
        } catch (JMSException e) {
            logger.log(Level.SEVERE, "Error while receiving message.", e);
        }
    }
}
