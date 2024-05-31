package com.prime.repositories;
import com.prime.entities.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface BRepo extends JpaRepository<B,Integer>{
   
	@Query(nativeQuery=true,value="select * from b where attendance<=:num")
	List<B> findByAttendace(Integer num);
}