package com.loco.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;

/**
 * @auther LociLi
 */
public class KafkaProducerTest {
    public static void main(String[] args) {
        //配置对象
        HashMap<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(configMap);

        for (int i = 0; i < 100; i++) {
            // 创建数据
            ProducerRecord<String, String> record = new ProducerRecord<>("test", "key"+i, "value"+i);
            // 通过生产者对象将数据发送到kafka
            producer.send(record);
            System.out.println("发送了第" + i + "条");
        }

        // 关闭生产者对象
        producer.close();
    }
}