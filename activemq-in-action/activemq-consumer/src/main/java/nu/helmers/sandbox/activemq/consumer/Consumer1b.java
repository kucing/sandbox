package nu.helmers.sandbox.activemq.consumer;

import nu.helmers.sandbox.activemq.common.ActiveMQConstants;

/**  */
public class Consumer1b extends AbstractSynchronousConsumer {

    public Consumer1b() {
        super(ActiveMQConstants.QUEUE_NAME_1);
    }

}
