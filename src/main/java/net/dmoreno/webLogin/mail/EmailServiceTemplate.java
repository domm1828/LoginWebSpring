package net.dmoreno.webLogin.mail;

import javax.mail.MessagingException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import net.dmoreno.webLogin.models.User;
@Service
public class EmailServiceTemplate {
	    private final TemplateEngine templateEngine;
	    private final JavaMailSender javaMailSender;
	    
	    public EmailServiceTemplate(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
	        this.templateEngine = templateEngine;
	        this.javaMailSender = javaMailSender;
	    }
	    public String sendMail(User user) throws MessagingException {
	        Context context = new Context();
	        context.setVariable("user", user);
	        String process = templateEngine.process("Email", context);
	        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
	        helper.setSubject("Bienvenidos " + user.getName());
	        helper.setText(process, true);
	        helper.setTo(user.getEmail());
	        javaMailSender.send(mimeMessage);
	        return "Sent";
	    }
}
