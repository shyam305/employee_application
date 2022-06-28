package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter{
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Zack")
                .password("aayush")
                .roles("USER")
                .and()
                .withUser("GFG")
                .password("Helloword")
                .roles("student");
    }
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.
	                httpBasic()
	                .and()
	                .authorizeRequests()
	                .antMatchers("/users").hasRole("USER")
	                .antMatchers("/details").hasAnyRole("admin_role","student")
	                .and()
	                .formLogin().and().csrf().disable();
	    }
	 @Bean
	    public PasswordEncoder getPasswordEncoder(){
	        return NoOpPasswordEncoder.getInstance();
	    }
}
