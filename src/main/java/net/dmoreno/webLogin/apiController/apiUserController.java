package net.dmoreno.webLogin.apiController;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.dmoreno.webLogin.mail.EmailBody;
import net.dmoreno.webLogin.mail.EmailInterface;
import net.dmoreno.webLogin.mail.EmailServiceTemplate;
import net.dmoreno.webLogin.models.User;
import net.dmoreno.webLogin.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class apiUserController {
	 @Autowired
	EmailServiceTemplate serviceEmail;
    @Autowired
    EmailInterface email;
    @Autowired
	UserRepository repo;
	 @GetMapping(value="/", produces = { "application/json", "application/xml" })
	    public List<User> findAllUsers() {
		 return repo.findAll();
	    }
	 
	 @GetMapping("/email")
	    public  boolean SendEmailInfo() {
		 EmailBody emailBody = new EmailBody();
		 emailBody.setEmail("academiacurso@gmail.com");
		 emailBody.setContent("Prueba de Envio de Email");
		 emailBody.setSubject("Prueba..");
		 return email.sendEmail(emailBody); 
	    }
	 
	 @GetMapping("/emailTwo")
	    public  String SendEmailInfoTemplate() throws MessagingException {
		 User user=new User();
		 user.setName("Duglas");
		 user.setEmail("academiacurso@gmail.com");
		  return serviceEmail.sendMail(user);
	    }
	 

}
