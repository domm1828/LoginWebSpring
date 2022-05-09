package net.dmoreno.webLogin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.dmoreno.webLogin.models.User;
import net.dmoreno.webLogin.service.UserService;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    
    @GetMapping({"/", "/login"})
    public String login() {
    	 return "login";
    }
    
    @GetMapping("/registration")
    public String register(Model model) {
    	 User user = new User();
    	 model.addAttribute("user", user); 
    	 return "register";
   }
    
    @PostMapping("/registration")
    public String createUser(@Valid User user, BindingResult bindingResult,Model model) {

    	  User userExists = userService.findUserByUserName(user.getUserName());
    	  if (userExists != null) {
              bindingResult
                      .rejectValue("userName", "error.user",
                              "El nombre del usaurio ya existe");
          }
    	   if (bindingResult.hasErrors()) { 
    	    	 return "register";
    		   
    	   }
    	   else {
    		   userService.saveUser(user);
    	    	 user = new User();
    	    	 model.addAttribute("user", user); 
    	    	 model.addAttribute("successMessage", "Usuario registrado"); 
    	   }
    	   
    	   return "register";
   }
    

}
