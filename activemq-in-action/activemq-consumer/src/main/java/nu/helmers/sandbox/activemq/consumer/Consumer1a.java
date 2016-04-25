package nu.helmers.sandbox.activemq.consumer;

import nu.helmers.sandbox.activemq.common.ActiveMQConstants;

/**  */
public class Consumer1a extends AbstractSynchronousConsumer {

    public Consumer1a() {
        super(ActiveMQConstants.QUEUE_NAME_1);
    }

}
