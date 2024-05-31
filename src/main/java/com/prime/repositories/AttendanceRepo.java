package com.prime.repositories;
import com.prime.entities.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface AttendanceRepo extends JpaRepository<Attendance,Integer>{
   
	
}