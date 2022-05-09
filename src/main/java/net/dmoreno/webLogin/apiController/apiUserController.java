package net.dmoreno.webLogin.apiController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.dmoreno.webLogin.models.User;
import net.dmoreno.webLogin.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class apiUserController {
    @Autowired
	UserRepository repo;
	 @GetMapping(value="/", produces = { "application/json", "application/xml" })
	    public List<User> findAllUsers() {
		 return repo.findAll();
	    }
}
