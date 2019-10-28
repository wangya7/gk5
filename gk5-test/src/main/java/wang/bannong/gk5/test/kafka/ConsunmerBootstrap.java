package wang.bannong.gk5.test.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import wang.bannong.gk5.test.kafka.serializer.OrderDeserializer;

/**
 * Created by bn. on 2019/8/28 10:25 AM
 */
public class ConsunmerBootstrap {

    private final static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String... args) {
        new ConsunmerBootstrap().consumOrder();
//        new Thread(() -> new ConsunmerBootstrap().consumPartition3()).start();
//        new Thread(() -> new ConsunmerBootstrap().consumPartition2()).start();
    }

    public void consum() {
        // 1. 创建消费者实例
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configs);

        // 2. 订阅主题
        consumer.subscribe(Arrays.asList("sun"));

        // 3. 拉去消息消费
        while (flag.get()) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : consumerRecords) {
                System.out.println("topic:" + record.topic() + ", partition:" + record.partition() +
                        ", offset:" + record.offset() + ", key:" + record.key() + ", value:" + record.value());
            }
        }

        // 4. 关闭资源
        consumer.close();
    }

    public void consumOrder() {
        // 1. 创建消费者实例
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "test-order");
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, OrderDeserializer.class.getName());
        KafkaConsumer<String, Order> consumer = new KafkaConsumer<>(configs);

        // 2. 订阅主题
        consumer.subscribe(Arrays.asList("chenzhou"));

        // 3. 拉去消息消费
        while (flag.get()) {
            ConsumerRecords<String, Order> consumerRecords = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, Order> record : consumerRecords) {
                System.out.println("topic:" + record.topic() + ", partition:" + record.partition() +
                        ", offset:" + record.offset() + ", key:" + record.key() + ", value:" + record.value());
            }
        }

        // 4. 关闭资源
        consumer.close();
    }

    public void consumPartition3() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "test-order");
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configs);

        TopicPartition partition3 = new TopicPartition("sun", 3);
        consumer.assign(Arrays.asList(partition3));

        // 3. 拉去消息消费
        while (flag.get()) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
            Set<TopicPartition> topicPartitions = consumerRecords.partitions();

            for (ConsumerRecord<String, String> record : consumerRecords) {
                System.out.println("record:" + record);
            }

            for (TopicPartition topicPartition : topicPartitions) {
                System.out.println("topicPartition:" + topicPartition);
            }

        }
        consumer.close();
    }

    public void consumPartition2() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "test-order");
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configs);

        TopicPartition partition2 = new TopicPartition("sun", 2);
        consumer.assign(Arrays.asList(partition2));

        // 3. 拉去消息消费
        while (flag.get()) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
            Set<TopicPartition> topicPartitions = consumerRecords.partitions();

            for (ConsumerRecord<String, String> record : consumerRecords) {
                System.out.println("record:" + record);
            }

            for (TopicPartition topicPartition : topicPartitions) {
                System.out.println("topicPartition:" + topicPartition);
            }

        }
        consumer.close();
    }

}
