package com.example.demo;

//Java Program to Illustrate kafka Consumer Class 


//Importing required classes 
import org.springframework.kafka.annotation.KafkaListener; 
import org.springframework.stereotype.Component; 

//Annotation 
@Component

//Class 
public class KafkaConsumer { 

	@KafkaListener(topics = "NewTopic2", 
				groupId = "group_id", 
				containerFactory = "bookListener") 

	// Method 
	public void
	consume(Book book) 
	{ 
		// Print statement 
		System.out.println("message = " + book.toString()); 
	} 
}
