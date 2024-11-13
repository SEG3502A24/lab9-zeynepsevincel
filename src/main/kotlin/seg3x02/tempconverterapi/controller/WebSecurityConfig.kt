package seg3x02.tempconverterapi

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeRequests { auth -> auth.anyRequest().authenticated() }
            .httpBasic()  // Enables Basic Authentication
            .and()
            .formLogin().disable()  // Disables form login; using basic auth only
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user1 = User.withUsername("user1")
            .password("{noop}pass1")  // "{noop}" allows plain text passwords
            .roles("USER")
            .build()

        val user2 = User.withUsername("user2")
            .password("{noop}pass2")
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(user1, user2)
    }
}
