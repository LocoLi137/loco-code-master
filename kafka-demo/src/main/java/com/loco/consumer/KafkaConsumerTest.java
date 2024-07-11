package com.loco.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ShortDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

/**
 * @auther LociLi
 */
public class KafkaConsumerTest {
    public static void main(String[] args) {
        //配置对象
        HashMap<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        // 创建消费者对象
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configMap)) {
            //订阅主题
            consumer.subscribe(Collections.singletonList("test"));
            // 从kafka拉取数据
            while (true) {
                for (ConsumerRecord<String, String> data : consumer.poll(Duration.ZERO)) {
                    System.out.println(data);
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 关闭生产者对象
//        consumer.close();
    }
}
