package nu.helmers.sandbox.activemq.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**  */
public abstract class AbstractMessageProducer {

    public static final String BROKER_URL = "tcp://localhost:61616";

    private Connection connection;
    private Session session;
    private MessageProducer producer;

    public AbstractMessageProducer() {
        initialize();
    }

    public void send(String text) {
        try {
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
            Queue queue = session.createQueue(getQueueName());
            producer = session.createProducer(queue);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    abstract String getQueueName();

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
