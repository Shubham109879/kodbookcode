package com.kodbook.controllers;

import java.io.IOException;
import java.util.List;
//import java.util.Properties;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kodbook.entities.Post;
import com.kodbook.entities.User;
import com.kodbook.services.PostService;
import com.kodbook.services.UserService;

//import jakarta.mail.Authenticator;
//import jakarta.mail.Message;
//import jakarta.mail.MessagingException;
//import jakarta.mail.PasswordAuthentication;
//import jakarta.mail.Session;
//import jakarta.mail.Transport;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;


@Controller
public class UserController 
{
	
  String otp="";
  
  String userEmail="";
  
  @Autowired
  UserService service;

  @Autowired
  PostService postService;
  
  @PostMapping("/signUp")
  public String addUser(@ModelAttribute User user)
  {
	  String username = user.getUsername();
	  String email = user.getEmail();
	  boolean status = service.userExists(username, email);
	  if(status == false) {
			service.addUser(user);
	   }
	  
		return "index";
  }
  
  
  @PostMapping("/login")
  public String login(@RequestParam String username,@RequestParam String password,
		  Model model,HttpSession session)
  {
	  boolean status = service.validateUser(username,password);
		if(status == true) 
		{
			List<Post> allPosts = postService.fetchAllPosts();
			
			session.setAttribute("username", username);
			model.addAttribute("session", session);
			
			model.addAttribute("allPosts", allPosts);
			
			return "home";
		}
		else 
		{
			return "index";
		}
  }
  
  @PostMapping("/updateProfile")
	public String updateProfile(@RequestParam String dob, @RequestParam String gender,
			@RequestParam String city, @RequestParam String bio,
			@RequestParam String college, @RequestParam String linkedIn,
			@RequestParam String gitHub, @RequestParam MultipartFile profilePic
			, HttpSession session,
			Model model) {
		
		String username = (String) session.getAttribute("username");
		
		User user = service.getUser(username);
		
		user.setDob(dob);
		user.setGender(gender);
		user.setCity(city);
		user.setBio(bio);
		user.setCollege(college);
		user.setLinkedIn(linkedIn);
		user.setGitHub(gitHub);
		try {						
			user.setProfilePic(profilePic.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.updateUser(user);
		model.addAttribute("user", user);
		return "myProfile";
	}
  
    @PostMapping("/openSearchUser")
	public String searchUser(HttpSession session,@RequestParam String username,Model model)
	{
	   boolean status=service.userNameExists(username);
	   
	   if(status==true)
	   {
//		   User user=service.getUser(username);
//		   model.addAttribute("user",user);
//		   List<Post> myPosts=user.getPosts();
//		   model.addAttribute("myPosts",myPosts); 
//		   
//		   return "showUserProfile";   
		   
		   List<User> userList=service.searchUser(username);
		   
		   model.addAttribute("userList",userList);
		   
		   return "searchList"; 
		   
	   }
	   
	   List<Post> allPosts = postService.fetchAllPosts();
		
		session.setAttribute("username", username);
		model.addAttribute("session", session);
		
		model.addAttribute("allPosts", allPosts);
	   
	   
	   
	   return "home";
	}
    
    @PostMapping("/checkEmailSendOTP")
    public String checkEmailSendOTP(@RequestParam String email)
    {
     
      boolean status=service.userEmailExists(email); 	
    
      if(status==false)
      {
    	return "resetPasswordEmail";  
      }
    	
     	
      Random rand=new Random();
      
      int tempOtp=100000+rand.nextInt(900000);
      
      otp=Integer.toString(tempOtp);
      
      userEmail=email;
      
      System.out.println(otp);
   
//      String host = "smtp.gmail.com"; // Replace with your SMTP server
//      // Use environment variables for security
//      String from = "shubhamsali1411@gmail.com"; // Your email address
//      String password = "Shubham@123gm"; // Your email password or App Password
//      
//   // Check if the environment variables are set
//      if (from == null || password == null) {
//          return "Error: Email credentials are not set in the environment variables.";
//      }
//      
//      final String to=email;
//    	
//      Properties properties = new Properties();
//      properties.put("mail.smtp.auth", "true");
//      properties.put("mail.smtp.starttls.enable", "true");
//      properties.put("mail.smtp.host", host);
//      properties.put("mail.smtp.port", "587"); 
//      
//     Session session1=Session.getInstance(properties, 
//    		   new Authenticator() {
//					protected PasswordAuthentication getPasswordAuthentication() {
//						return new PasswordAuthentication(from, password);
//					}
//     });
//     
//     Message message=new MimeMessage(session1);
//     
//     try {
//		message.setFrom(new InternetAddress(from));
//	    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//	    message.setSubject("Your OTP Code");
//	    message.setText("Your OTP code is: " + otp);
//	 // Send message
//        Transport.send(message);
//        System.out.println("OTP sent successfully!");
//        
//	}  
//     
//    catch (MessagingException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
     
     return "enterOTP";	
    }
    
    
    @GetMapping("/validateOTP")
    public String validateOTP(@RequestParam String enteredOTP)
    {
     if(otp.equals(enteredOTP))
     {
       return "changePassword";	 
     }
     
     return "enterOTP";
    }
    
    
    
    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String newPassword,@RequestParam String confirmPassword)
    {	
      if(newPassword.equals(confirmPassword))
      {
    	User user=service.getUserByEmail(userEmail);
    	
    	user.setPassword(newPassword);
    	
    	service.updateUser(user);
    	   
        return "index";	  
      }
      
      return "changePassword";
    }
    
    
    
    
    
    
      
}
