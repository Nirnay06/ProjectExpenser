package com.expenser.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

import com.expenser.security.BaseUrlFilter;
import com.expenser.security.JWTAuthenticationFilter;
import com.expenser.security.oauth2.CustomOAuth2UserService;
import com.expenser.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.expenser.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.expenser.security.oauth2.OAuth2AuthenticationSuccessHandler;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests((requests) -> requests.antMatchers("login").authenticated().antMatchers("/advance").denyAll().antMatchers("trivial").permitAll());
//		http.formLogin();
//		http.httpBasic();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.cors()
//		.configurationSource(new CorsConfigurationSource() {
//			
//			@Override
//			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//				CorsConfiguration corsConfiguration = new CorsConfiguration();
//				corsConfiguration.setMaxAge(3600l);
//				corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("/**"));
//				corsConfiguration.setAllowCredentials(true);
//				corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
//				corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST"));
//				corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:8080","https://accounts.google.com","http://localhost:3000","*"));
//				return corsConfiguration;
//			}
//		})
		.and()
		.csrf().disable()
		.authorizeRequests()
        //...
        .antMatchers(
          HttpMethod.GET,
          "/",
          "/favicon.ico",
          "/**/*.png",
          "/**/*.gif",
          "/**/*.svg",
          "/**/*.jpg",
          "/**/*.html",
          "/**/*.css",
          "/**/*.webmanifext",
          "/**/*.js")
          .permitAll()
          .antMatchers("/auth/**","/oauth2/**","/login/**").permitAll()
        .anyRequest().authenticated().and()
        .oauth2Login()
        .authorizationEndpoint()
            .baseUri("/oauth2/authorize")
            .authorizationRequestRepository(cookieAuthorizationRequestRepository())
            .and()
        .redirectionEndpoint()
            .baseUri("/oauth2/callback/*")
            .and()
        .userInfoEndpoint()
            .userService(customOAuth2UserService)
            .and()
        .successHandler(oAuth2AuthenticationSuccessHandler)
        .failureHandler(oAuth2AuthenticationFailureHandler);
            ;
        http.addFilterBefore(new BaseUrlFilter(), WebAsyncManagerIntegrationFilter.class);
		http.addFilterBefore(new JWTAuthenticationFilter(),BasicAuthenticationFilter.class);
	}
	 @Bean 
	 public UserDetailsService userDetailsService() {
		 return new JdbcUserDetailsManager(dataSource);
	 }
	  
	  @Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
	  
	  @Bean(BeanIds.AUTHENTICATION_MANAGER)
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	  
	  @Bean
	    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
	        return new HttpCookieOAuth2AuthorizationRequestRepository();
	    }
}
