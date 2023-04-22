package muha.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeHttpRequests(authorizationConfigurer -> {
            authorizationConfigurer
                    .requestMatchers("/security_controller/second_resource")
                    .authenticated();
            authorizationConfigurer.anyRequest().permitAll();

        });

        //  /Login
        http.formLogin();


        return http.build();
    }

}
