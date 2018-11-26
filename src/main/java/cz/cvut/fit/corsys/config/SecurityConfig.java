package cz.cvut.fit.corsys.config;

import cz.cvut.fit.corsys.bl.auth.CorsysPasswordEncoderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css*", "/js*", "/index", "/perform_login").permitAll()
                .antMatchers("/patient/**").hasAuthority("PATIENT")
                .antMatchers("/receptionist/**").hasAuthority("RECEPTIONIST")
                .antMatchers("/doctor/**").hasAuthority("DOCTOR")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/welcome").hasAnyAuthority("ADMIN", "DOCTOR", "PATIENT", "RECEPTIONIST")
                .and()
                .formLogin()
                .loginProcessingUrl("/perform_login")
                .loginPage("/")
                .defaultSuccessUrl("/welcome")
                .failureUrl("/login?wrongPass=true")
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder getCorsysPasswordEncoderImpl() {
        return new CorsysPasswordEncoderImpl();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService corsysUserDetailsService) throws Exception {
        auth.userDetailsService(corsysUserDetailsService);
    }
}
