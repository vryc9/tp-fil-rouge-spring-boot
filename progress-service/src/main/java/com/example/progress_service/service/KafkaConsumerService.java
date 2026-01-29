package com.example.progress_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

//	@Autowired
//	private ProgressRepository repository;

	@KafkaListener(topics = "course-created", groupId = "progress-group")
	public void consume(String message) {
		System.out.println("Consumed Message");
	}

}
