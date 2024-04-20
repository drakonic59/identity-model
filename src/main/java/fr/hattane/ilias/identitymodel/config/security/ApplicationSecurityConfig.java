package fr.hattane.ilias.identitymodel.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {
	
	@Autowired
	BasicAuthorizationManager baiscAuth;

	@Autowired
	BearerAuthorizationManager bearerAuth;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(AbstractHttpConfigurer::disable)
	    	.httpBasic(AbstractHttpConfigurer::disable)
	    	.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	    	.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
	              authorizationManagerRequestMatcherRegistry
		                .requestMatchers("/swagger-ui/**").permitAll()
		                .requestMatchers("/definition-docs/**").permitAll()
		                .requestMatchers("/actuator/**").permitAll()
		                .requestMatchers("/bearer/**").access(bearerAuth)
              			.anyRequest().access(baiscAuth)
                  )
	    	.exceptionHandling(handle -> handle
	    			.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
			);

	    return http.build();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
	    return new InMemoryUserDetailsManager();
	}
	
}
