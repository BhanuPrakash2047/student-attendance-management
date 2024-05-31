package com.prime.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prime.entities.Studentadmindetails;
import com.prime.repositories.AdminUserDetailsRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AdminUserSecurity implements UserDetailsService{

	@Autowired
	HttpServletResponse res;
	@Autowired
	HttpServletRequest req;

	@Autowired
	AdminUserDetailsRepo repo;
	@Override
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Studentadmindetails> user=repo.findByUsername(username);
		
		if (user.isPresent() ) {
			var userob = user.get();
			System.out.println(userob.getUsername());
			return User.builder()
					.username(userob.getUsername())
					.password(userob.getPassword())
					.roles(getRoles(userob))
					.build();
        
		}
		else {
			System.out.println("Nothing to print");

			try {
				res.sendRedirect(req.getContextPath()+"/"+"login1.html");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block	
				System.out.println("Nothing to print");

				e.printStackTrace();
				System.out.println("Nothing to print");

			}
			return null;

		}
	}


	private String getRoles(Studentadmindetails userob) {
		String role="";
	    if(userob.getRole().equals("USER")) {
			role="USER";
		} else if(userob.getRole().equals("ADMIN")) {
			role="ADMIN";
		}
	    	return role;
	}






}
