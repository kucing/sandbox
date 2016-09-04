package nu.helmers.sandbox.activemq.producer;

import nu.helmers.sandbox.activemq.common.ActiveMQConstants;

/**  */
public class Producer2 extends AbstractProducer {

    public static final String QUEUE_NAME = ActiveMQConstants.QUEUE_NAME_2;

    public Producer2() {
        super(QUEUE_NAME);
    }

}
