package com.prime.repositories;
import com.prime.entities.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ARepo extends JpaRepository<A,Integer>{
   
	@Query(nativeQuery=true,value="select * from a where attendance<=:num")
	List<A> findByAttendace(Integer num);
	
}
