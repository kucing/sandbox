package nu.helmers.sandbox.activemq.producer;

import nu.helmers.sandbox.activemq.common.ActiveMQConstants;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.fest.assertions.Assertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import javax.jms.*;

/**  */
public class MessageProducerTest {

    private Producer1 producer;
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;

    @Test
    public void testSend() throws Exception {
        // Code under test
        producer = new Producer1();
        producer.send("Message sent from unit test " + MessageProducerTest.class.getSimpleName());

        // Access JMS API directly, to check whether message has arrived on the queue
        createConsumer();

        Message message = consumer.receive(100);
        Assert.assertNotNull("No message received...", message);
        TextMessage textMessage = (TextMessage) message;
        Assertions.assertThat(textMessage.getText()).startsWith("Message ");
//        Assert.fail("Received message is not a TextMessage? Message = " + message);
    }

    private void createConsumer() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConstants.BROKER_URL);
        connection = connectionFactory.createConnection();

        // A JSM connection must be started before consumers can receive messages through it!
        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(Producer1.QUEUE_NAME);
        consumer = session.createConsumer(queue);
    }

    @After
    public void tearDown() throws JMSException {
        producer.close();

        consumer.close();
        session.close();
        connection.close();
    }

}
