package net.dmoreno.webLogin.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.dmoreno.webLogin.models.User;
import net.dmoreno.webLogin.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/home")
    public String home(Model model) {
    	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
          User user = userService.findUserByUserName(auth.getName());
          model.addAttribute("userName", "Bienvenido " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ") ");
    	return "admin/index";
    }
}
