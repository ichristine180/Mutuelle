package com.example.demo.config;

import java.security.SecureRandom;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.UserSecurityService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private UserSecurityService userSecurityService;

	private static final String SALT = "salt";

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
	}

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
		return new MyUrlAuthenticationSuccessHandler();
	}

	private static final String[] PUBLIC_MATCHERS = {"/dist/**", "/plugins/**", "/js/**",
			"build/**", "imgs/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll()
				.antMatchers("/confirm**", "/forgot-password**", "/reset-password**", "/home")
				.permitAll().antMatchers("/forRSSB/**").hasAnyRole("RSSB").antMatchers("/admin/**")
				.hasRole("ADMIN").antMatchers("/health").hasRole("HEALTH").anyRequest().authenticated();

		http.csrf().disable().cors().disable().formLogin().failureUrl("/login?error")
				.successHandler(myAuthenticationSuccessHandler()).loginPage("/login").permitAll().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
				.deleteCookies("remember-me").permitAll().and().rememberMe().and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// This is in-memory authentication
		auth.inMemoryAuthentication().withUser("rssb@mutuelle.com").password("pass").roles("RSSB");
		auth.inMemoryAuthentication().withUser("health@mutuelle.com").password("pass").roles("HEALTH");
		auth.inMemoryAuthentication().withUser("admin@mutuelle.com").password("pass").roles("ADMIN");
		// Database login
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}
}