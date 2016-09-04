package nu.helmers.sandbox.activemq.producer;

import nu.helmers.sandbox.activemq.common.ActiveMQConstants;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.logging.*;

import static nu.helmers.sandbox.activemq.common.ActiveMQConstants.BROKER_URL;

/**  */
public abstract class AbstractProducer {

    private Logger logger;

    private String queueName;
    private Connection connection;
    private Session session;
    private MessageProducer producer;

    public AbstractProducer(String queueName) {
        this.queueName = queueName;
        initialize();
    }

    public void send(String text) {
        try {
            logger.info(String.format("Sending message: '%s'", text));
            Message message = session.createTextMessage(text);
            producer.send(message);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    protected void initialize() {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(queueName);
            producer = session.createProducer(queue);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }

        logger = Logger.getLogger(this.getClass().getSimpleName());
    }

    public void close() {
        try {
            this.producer.close();
            this.session.close();
            this.connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
