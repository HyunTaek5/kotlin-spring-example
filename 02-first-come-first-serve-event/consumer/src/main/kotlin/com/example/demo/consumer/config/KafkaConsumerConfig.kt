package com.example.demo.consumer.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.LongDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@Configuration
class KafkaConsumerConfig {

  @Bean
  fun consumerFactory(): DefaultKafkaConsumerFactory<String, Long> {
    val config: HashMap<String, Any> = HashMap()

    config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
    config[ConsumerConfig.GROUP_ID_CONFIG] = "group_1"
    config[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class
    config[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = LongDeserializer::class

    return DefaultKafkaConsumerFactory(config)
  }

  @Bean
  fun concurrentKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Long> {
    val factory = ConcurrentKafkaListenerContainerFactory<String, Long>()
    factory.consumerFactory = consumerFactory()

    return factory
  }
}
