package com.Entreprise.GestionEntreprise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

   
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/admin/").hasRole("ADMIN")
            .requestMatchers("/employe/").hasRole("EMPLOYE")
            .requestMatchers("/admin/Afficher").hasRole("ADMIN")
            .requestMatchers("/admin/Modifier").hasRole("ADMIN")
            .requestMatchers("/admin/Delete").hasRole("ADMIN")
            .requestMatchers("/admin/Save").hasRole("ADMIN")
            .requestMatchers("/admin/afficherAjouter").hasRole("ADMIN")
            .requestMatchers("/admin/Ajouter").hasRole("ADMIN")
            .requestMatchers("/admin/fichesAfficher").hasRole("ADMIN")
            .requestMatchers("/admin/fichesAjouter").hasRole("ADMIN")
            .requestMatchers("/admin/AffichagePassword").hasRole("ADMIN")
            .requestMatchers("/admin/ModifierFiche").hasRole("ADMIN")
            .requestMatchers("/employe/changerPassword").hasRole("EMPLOYE")
            .requestMatchers("/assets/**", "/static/**", "/images/**", "/css/**", "/js/**", "/vendors/**").permitAll() 
            .anyRequest().authenticated()
            .and()
            .formLogin()
                
                .loginPage("/login")
                .failureUrl("/login?error=true") // Vérifiez que cette URL correspond
                .defaultSuccessUrl("/default", true)
                .permitAll()

            .and()
            
            .logout()
            .logoutUrl("/logout") // L'URL à utiliser pour le logout
            .logoutSuccessUrl("/login?logout=true") // Redirige après le logout
            .invalidateHttpSession(true) // Invalide la session HTTP
            .deleteCookies("JSESSIONID") // Supprime les cookies de session
            .permitAll();


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}