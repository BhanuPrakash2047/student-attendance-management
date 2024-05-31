package com.prime.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prime.entities.A;
import com.prime.entities.B;
import com.prime.entities.Sections;
import com.prime.entities.Studentadmindetails;
import com.prime.repositories.ARepo;
import com.prime.repositories.AdminUserDetailsRepo;
import com.prime.repositories.AttendanceRepo;
import com.prime.repositories.BRepo;
import com.prime.repositories.FeedbackRepo;
import com.prime.repositories.SectionRepo;
import com.prime.services.FeedbackService;

import jakarta.transaction.Transactional;

@RestController
public class UserAdminController {
    @Autowired 
    FeedbackService feedbackService;
    @Autowired
    AdminUserDetailsRepo userRepo;
    @Autowired 
    private PasswordEncoder encoder;
    @Autowired
    SectionRepo sectionRepo;
    @Autowired
    ARepo aRepo;   
    @Autowired
    BRepo bRepo;
    @Autowired
    AttendanceRepo attendanceRepo;
    
	    @GetMapping("/feedback")
	    public ModelAndView feedback(@RequestParam("feedback") String feedback) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String name=auth.getName();
		feedbackService.save(name,feedback);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/adminmenulib.html");
		return mv;
		}
		
		@GetMapping("/profile")
		public ModelAndView profile() {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String name=auth.getName();
		ModelAndView mv=new ModelAndView();
		Studentadmindetails user=userRepo.findByUsername(name).get();
		System.out.println(user.getPassword());
		mv.addObject("userob",user);
		mv.addObject("username",name);
		mv.setViewName("/updatedetails.html");
		return mv;

		}
		@Transactional
		@GetMapping("/student/updateprofile")
		public ModelAndView updateProfile(@ModelAttribute("userob") Studentadmindetails obj) {
			ModelAndView mv=new ModelAndView();
//			String password=obj.getPassword();
//			String pas=encoder.encode(password);
//			obj.setPassword(pas);
//			System.out.println(obj.getPassword());
 			userRepo.save(obj);
			mv.setViewName("/adminmenulib.html");
			return mv;
			
		}
		@GetMapping("/addsection")
		
		public ModelAndView addSection(@RequestParam("sectionName") String section) {
			Sections sectionob=new Sections();
			System.out.println(sectionob.getSection());
			sectionob.setSection(section);
			sectionRepo.save(sectionob);
			ModelAndView mv=new ModelAndView();
			mv.setViewName("/adminmenulib.html");
			return mv;
			
		}
		@GetMapping("/responseForStudentClassSelection")
		public ResponseEntity<List<String>> getSectionData(){
			List<String> data=new ArrayList<>();
			List<Sections> sectionData=new ArrayList<>();
			sectionData=sectionRepo.findAll();
			for(Sections  item:sectionData){
				data.add(item.getSection());
			}
			System.out.println(data);
			return ResponseEntity.ok(data);
		}
		
		@GetMapping("/showsectionselection")
		public ModelAndView showSection() {
			ModelAndView mv=new ModelAndView("/ShowSection.html");
			return mv;
	}
//		lkjhgfffffffffffffffffffgfgggggggggggggggggggggggggggggggggggggggggggggggggg
		@GetMapping("/showrecords")
		public ModelAndView showRecords(@RequestParam("section") String section) {
			Optional<Sections> secob=sectionRepo.findBySection(section);
			if(secob.isEmpty()) {
				ModelAndView mv=new ModelAndView("/adminmenulib.html");
				return mv;
			}
			else {
				ModelAndView mv=new ModelAndView("/getRecords/"+section);
				return mv;
			}
		}
		@GetMapping("/getRecords")
		public ModelAndView getRecords(@RequestParam("section") String section) {
			System.out.println("Entered section"+section);
			ModelAndView m=new ModelAndView();
			  if(section.equalsIgnoreCase("a")) {
				  List<A> ob=aRepo.findAll();
				  m.addObject("ob",ob);
				  m.setViewName("/getRecords.html");
				  return m;
			  }
			  else{
				  List<B> ob=bRepo.findAll();
				  m.addObject("ob",ob);
				  m.setViewName("/getRecords.html");
				  return m;
			  }
		}
		@GetMapping("/getRecordsOfLowAttendence")
		public ModelAndView showFormForData() {
			ModelAndView mv=new ModelAndView("/lowattendance.html");
			return mv;
		}
		@GetMapping("/showRecordsOfLowAttendence")
		public ModelAndView getRecordsOfLowAttendace(@RequestParam("section")String section,@RequestParam("num") Integer num) {
			ModelAndView m=new ModelAndView();
			System.out.println("Entered low num"+num);
			  if(section.equalsIgnoreCase("a")) {
				  List<A> ob=aRepo.findByAttendace(num);
				  m.addObject("ob",ob);
				  A obb=ob.get(0);
				  System.out.println(obb.getUsername());
				  
				  m.setViewName("/getLowAttendanceRecords.html");
				  return m;
			  }
			  else{
				  List<B> ob=bRepo.findByAttendace(num);
				  m.addObject("ob",ob);
				  m.setViewName("/getLowAttendanceRecords.html");
				  return m;
			  }
		}
		
	}
	

