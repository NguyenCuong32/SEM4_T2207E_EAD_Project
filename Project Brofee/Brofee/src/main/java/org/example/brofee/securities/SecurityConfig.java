package org.example.brofee.securities;

import org.example.brofee.services.IAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(IAccountService iAccountService){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(iAccountService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(
                configurer->configurer
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/indexAdmin").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()   //Yeu cau dang nhap moi truy cap duoc
        )
                .formLogin(
                        form->form
                                .loginPage("/loginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .defaultSuccessUrl("/default", true)
                                .permitAll()
                )
                .logout(
                        logout->logout
                                .logoutUrl("/logout")
                                .permitAll()
                )
                .exceptionHandling(
                        configurer->configurer
                                .accessDeniedPage("/page-403")
                );
        return httpSecurity.build();
    }
}
