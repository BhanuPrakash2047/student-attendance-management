package com.prime.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prime.entities.Studentadmindetails;

@Repository
public interface AdminUserDetailsRepo extends JpaRepository<Studentadmindetails,Integer>{
      @Query(nativeQuery=true,value="select * from Studentadmindetails where username=:username")
      Optional<Studentadmindetails> findByUsername(String username);
      
	
}
