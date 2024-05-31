package com.prime.services;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component

public class AuthenticationSuccessHandlr extends SavedRequestAwareAuthenticationSuccessHandler{
     @Override
     public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response,Authentication auth) throws ServletException,IOException{
 		boolean isAdmin=auth.getAuthorities().stream().anyMatch(GrantedAuthority->GrantedAuthority.getAuthority().toString().equals("ROLE_ADMIN"));
 		System.out.println(isAdmin);

		if (isAdmin) {
			response.sendRedirect(request.getContextPath()+"/"+"adminmenulib.html");
			return;
		}
		else {
			response.sendRedirect(request.getContextPath()+"/"+"Usermenuwe.html");			return;}

	}
     
     }

