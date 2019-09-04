package com.jiurong.hcxboot;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.TimeUnit;

@Slf4j
public class OnsTest {
    @SneakyThrows
    public void producer() {
        DefaultMQProducer producer = new DefaultMQProducer("GID_charge");
        producer.setNamesrvAddr("192.9.100.137:9876");
        producer.start();
        for (int i = 0; i < 100; i++) {
            String string = "Hello RocketMQ " + i;
            Message msg = new Message("jiurong_charge" /* Topic */,
                    "bus_device" /* Tag */,
                    string.getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            producer.sendOneway(msg);
            TimeUnit.MILLISECONDS.sleep(10);
        }
        producer.shutdown();
    }

    @SneakyThrows
    public void consumer() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("GID_charge");
        consumer.setNamesrvAddr("192.9.100.137:9876");
        consumer.subscribe("jiurong_charge", "bus_device");
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
//            log.info("Receive New Messages:{}", msgs.size());
            MessageExt msg = msgs.get(0);
            log.info("message:" + new String(msg.getBody()));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        log.info("Consumer Started.");
    }

    public static void main(String[] args) {
        new OnsTest().consumer();
        new OnsTest().producer();
    }
}
