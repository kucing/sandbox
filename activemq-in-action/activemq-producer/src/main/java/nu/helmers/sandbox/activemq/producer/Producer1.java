package nu.helmers.sandbox.activemq.producer;

import nu.helmers.sandbox.activemq.common.ActiveMQConstants;

/**  */
public class Producer1 extends AbstractProducer {

    public static final String QUEUE_NAME = ActiveMQConstants.QUEUE_NAME_1;

    public Producer1() {
        super(QUEUE_NAME);
    }

}
