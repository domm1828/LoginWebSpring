package net.dmoreno.webLogin.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import net.dmoreno.webLogin.service.DataUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration  extends WebSecurityConfigurerAdapter {
	 @Autowired
	 private BCryptPasswordEncoder bCryptPasswordEncoder;
	 @Autowired
	 private DataUserDetailsService userDetailsService;
	 @Autowired
	 AuthenticationSuccessHandler successHandler;
		
	   @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	                auth
	                    .userDetailsService(userDetailsService)
	                    .passwordEncoder(bCryptPasswordEncoder);
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

	        String loginPage = "/login";
	        String logoutPage = "/logout";

	        http.
	                authorizeRequests()
	                .antMatchers("/").permitAll()
	                .antMatchers(loginPage).permitAll()
	                .antMatchers("/api/**").permitAll()
	                .antMatchers("/registration").permitAll()
	                .antMatchers("/admin/**").hasAuthority("ADMIN")
	                .antMatchers("/users/**").hasAuthority("USERS")
	                .anyRequest()
	                .authenticated()
	                .and().csrf().disable()
	                .formLogin()
	                .loginPage(loginPage)
	                .loginPage("/")
	                .successHandler(successHandler)
	                .failureUrl("/login?error=true") 
	                .usernameParameter("user_name")
	                .passwordParameter("password")
	                .and().logout()
	                .logoutRequestMatcher(new AntPathRequestMatcher(logoutPage))
	                .logoutSuccessUrl(loginPage).and().exceptionHandling();
	    }

	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web
	                .ignoring()
	                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	    }
}
