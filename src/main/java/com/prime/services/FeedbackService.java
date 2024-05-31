package com.prime.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.repositories.FeedbackRepo;
import com.prime.entities.Feedback;

@Service
public class FeedbackService {
   @Autowired
   FeedbackRepo feedbackRepo;
   
	
	
	  public void save(String name, String feedback) {
		    LocalDate currentDate = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Define your desired date format
	        String formattedDate = currentDate.format(formatter);
	        Feedback feedbackob=new Feedback();
	        feedbackob.setDate(formattedDate);
	        feedbackob.setFeedack(feedback);
	        feedbackob.setUsername(name);
		    feedbackRepo.save(feedbackob);
	  }

	
	
}
