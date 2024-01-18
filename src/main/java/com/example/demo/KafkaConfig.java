// Java Program to Illustrate Configuration Class 
package com.example.demo;

// Importing required classes 

import java.util.HashMap; 
import java.util.Map; 
import org.apache.kafka.clients.consumer.ConsumerConfig; 
import org.apache.kafka.common.serialization.StringDeserializer; 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.kafka.annotation.EnableKafka; 
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory; 
import org.springframework.kafka.core.ConsumerFactory; 
import org.springframework.kafka.core.DefaultKafkaConsumerFactory; 
import org.springframework.kafka.support.serializer.JsonDeserializer; 

// Annotation 
@EnableKafka
@Configuration

// Class 
public class KafkaConfig { 

	@Bean
	public ConsumerFactory<String, Book> consumerFactory() 
	{ 

		// Creating a map of string-object type 
		Map<String, Object> config = new HashMap<>(); 

		// Adding the Configuration 
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
				"127.0.0.1:9094"); 
		config.put(ConsumerConfig.GROUP_ID_CONFIG, 
				"group_id"); 
		config.put( 
			ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
			StringDeserializer.class); 
		config.put( 
			ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
			JsonDeserializer.class); 

		// Returning message in JSON format 
		return new DefaultKafkaConsumerFactory<>( 
			config, new StringDeserializer(), 
			new JsonDeserializer<>(Book.class)); 
	} 

	// Creating a Listener 
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, 
												Book> 
	bookListener() 
	{ 
		ConcurrentKafkaListenerContainerFactory< 
			String, Book> factory 
			= new ConcurrentKafkaListenerContainerFactory<>(); 
		factory.setConsumerFactory(consumerFactory()); 
													
		return factory; 
	} 
}
