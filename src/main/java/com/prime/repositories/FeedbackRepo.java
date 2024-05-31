package com.prime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import com.prime.entities.Feedback;

@RestController
public interface FeedbackRepo extends JpaRepository<Feedback,Integer>{
   
	
	
}
