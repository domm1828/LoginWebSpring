package net.dmoreno.webLogin.service; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.dmoreno.webLogin.models.Role;
import net.dmoreno.webLogin.models.User;
import net.dmoreno.webLogin.repository.RoleRepository;
import net.dmoreno.webLogin.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.springframework.data.domain.Sort;
@Service
public class UserService {
	 	private UserRepository userRepository;
	    private RoleRepository roleRepository;
	    private BCryptPasswordEncoder bCryptPasswordEncoder;

	    @Autowired
	    public UserService(UserRepository userRepository,
	                       RoleRepository roleRepository,
	                       BCryptPasswordEncoder bCryptPasswordEncoder) {
	        this.userRepository = userRepository;
	        this.roleRepository = roleRepository;
	        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	    }

	    public User findUserByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }

	    public User findUserByUserName(String userName) {
	        return userRepository.findByUserName(userName);
	    }

	    public User saveUser(User user) {
	        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        user.setActive(true);
	        Role userRole = roleRepository.findByRole("USERS");
	        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	        return userRepository.save(user);
	    }
	    
	    public List<User> listAll() {
	        return userRepository.findAll(Sort.by("email").ascending());
	    }
}
