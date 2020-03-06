package es.urjc.computadores.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	public UserRepositoryAuthProvider authenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		
		//Public pages
		http.authorizeRequests().antMatchers("/registrar").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/usuario/{id}").permitAll();
		http.authorizeRequests().antMatchers("/producto{num}").permitAll();
		
		
		
		//Private pages
		//http.authorizeRequests().antMatchers("/listausuarios").hasAnyRole("USER");
		//http.authorizeRequests().anyRequest().authenticated();
		
		
		//login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");//TODO: hay que ver a donde enviamos al usuairo cuando intenta acceder a partes privadas
		http.formLogin().failureForwardUrl("/registrar");//hay que cambiar esto para que salga mensaje de error
		
		//logout
		
		
		
		
		//http.csrf().disable();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	// User
		auth.authenticationProvider(authenticationProvider);
	
	}
}
