package wang.bannong.gk5.test.mybatisx.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import wang.bannong.gk5.test.mybatisx.kafka.serializer.OrderSerializer;

/**
 * Created by bn. on 2019/8/28 10:25 AM
 */
public class ProducterBootstrap {

    public static void main(String... args) {


        new ProducterBootstrap().sendOrder();
    }

    public void sendQuick() {
        // 1. 创建生产者
        Map<String, Object> configs = new HashMap<>();
        configs.put("bootstrap.servers", "localhost:9092");
        configs.put("acks", "all");
        configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer(configs);

        // 2. 构造消息体
        ProducerRecord<String, String> record = new ProducerRecord<>("chenzhou", "吃了吗？");

        // 3. 发送消息
        producer.send(record);

        // 4. 处理剩余资源
        producer.close();
    }

    public void sendTransaction() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "my-transactional-id");
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer(configs);
        producer.initTransactions();
        try {
            producer.beginTransaction();
            for (int i = 0; i < 40; i++) {
                if (i == 20) {
                    throw new KafkaException("manual exception");
                }
                producer.send(new ProducerRecord<>("chenzhou", "SR-" + i));
            }
            System.out.println("finish");
            Thread.sleep(10000);
            producer.commitTransaction();
        } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
            producer.close();
        } catch (KafkaException e) {
            System.out.println("manual exception");
            producer.abortTransaction();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        producer.close();
    }

    public void sendSynch() {
        Map<String, Object> configs = new HashMap<>();
        configs.put("bootstrap.servers", "localhost:9092");
        configs.put("acks", "all");
        configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer(configs);

        ProducerRecord<String, String> record = new ProducerRecord<>("chenzhou", "HelloWorld");

        Future<RecordMetadata> recordMetadataFuture = producer.send(record);
        try {
            RecordMetadata recordMetadata = recordMetadataFuture.get();
            System.out.println("topic:" + recordMetadata.topic() + "; partition:" + recordMetadata.partition()
                    + "; offset:" + recordMetadata.offset());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("finish");

        producer.close();
    }

    public void sendAsynch() {
        Map<String, Object> configs = new HashMap<>();
        configs.put("bootstrap.servers", "localhost:9092");
        configs.put("acks", "all");
        configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer(configs);

        ProducerRecord<String, String> record = new ProducerRecord<>("chenzhou", "HelloWorld");

        producer.send(record, (recordMetadata, exception) -> {
            if (exception != null) {
                exception.printStackTrace();
            } else {
                System.out.println("topic:" + recordMetadata.topic() + "; partition:" + recordMetadata.partition()
                        + "; offset:" + recordMetadata.offset());
            }
        });
        System.out.println("finish");

        producer.close();
    }


    public void sendOrder() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ProducerConfig.ACKS_CONFIG, "all");
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, OrderSerializer.class.getName());
        Producer<String, Order> producer = new KafkaProducer(configs);

        Order order = new Order();
        order.setOrderId(78L);
        order.setOrderNum("BNHONG中TS");
        order.setCreateTime(new Date());

        ProducerRecord<String, Order> record = new ProducerRecord<>("chenzhou", "GHK", order);

        producer.send(record);

        producer.close();
    }


    public void sendPartition() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ProducerConfig.ACKS_CONFIG, "all");
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        Producer<String, String> producer = new KafkaProducer(configs);

        ProducerRecord<String, String> record3 = new ProducerRecord<>("sun", 3, "Test", "Topic-Partition3");
        ProducerRecord<String, String> record2 = new ProducerRecord<>("sun", 2, "Test", "Topic-Partition2");
        ProducerRecord<String, String> record = new ProducerRecord<>("sun", "Test", "Topic-Partition2");

        producer.send(record3);
        producer.send(record2);
        producer.send(record);

        producer.close();
    }

}
