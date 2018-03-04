package it.discovery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, 
securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.authorizeRequests().anyRequest()
		.fullyAuthenticated()
		.and()
		.oauth2Login()
		.defaultSuccessUrl("/success.html");
		http.csrf().disable();
	}

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws Exception
	// {
	// auth.inMemoryAuthentication()
	// .withUser("admin")
	// .password("admin")
	// .roles("ROLE_ADMIN")
	// .and()
	// .withUser("user").password("user").roles("ROLE_USER");
	// }

}
