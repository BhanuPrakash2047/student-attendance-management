package com.prime.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import com.prime.entities.Sections;

@RestController
public interface SectionRepo extends JpaRepository<Sections,Integer>{

	@Query(nativeQuery=true,value="select * from sections where section=:section")
	Optional<Sections> findBySection(String section);
	
}
