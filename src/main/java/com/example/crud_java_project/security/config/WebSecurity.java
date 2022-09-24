package com.example.crud_java_project.security.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.crud_java_project.appuser.AppUserService;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurity  {

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    
    /*  We want to secure the endpoints depending on the roles and leave an anonymous 
            entry point only for login 
        Restrict any DELETE request to an admin role
         
    */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
        http.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.DELETE)
            .hasRole("ADMIN")
            .antMatchers("/admin/**")
            .hasAnyRole("ADMIN")
            .antMatchers("/user/**")
            .hasAnyRole("USER", "ADMIN")
            .antMatchers("/api/v*/registration/**")
            .anonymous()
            .anyRequest()
            // .authenticated()
            .permitAll()
            .and()
            .httpBasic()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    /*
        DaoAuthenticationProvider is an AuthenticationProvider implementation that leverages a UserDetails
        Service and Password Encoder to authenticate a username and password  
    */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {

        /*
            DaoAuthenticationProvider looks the UserDetails from the UserDetailService  
        */
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }

    



    
}
