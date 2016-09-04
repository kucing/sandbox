package nu.helmers.sandbox.activemq.consumer;

import nu.helmers.sandbox.activemq.common.ActiveMQConstants;

/**  */
public class Consumer2Async extends AbstractAsyncConsumer {

    public Consumer2Async() {
        super(ActiveMQConstants.QUEUE_NAME_2);
    }

}
