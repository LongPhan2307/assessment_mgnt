package uit.thesis.assessment_mgnt.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uit.thesis.assessment_mgnt.security.jwt.JwtAuthorizationFilter;
import uit.thesis.assessment_mgnt.utils.domain.Domain;
import uit.thesis.assessment_mgnt.utils.role.RoleName;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private JwtAuthorizationFilter jwtAuthorizationFilter;
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // enable cors
        http.cors();
        // disable csrf for development environment
        http.csrf().disable();
        // configure authentication for apis
        http.antMatcher("/api/**").authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
//                .antMatchers("/api/**").permitAll()
                .antMatchers(Domain.API + Domain.API_ADMIN + Domain.ALL).hasAuthority(RoleName.ADMIN.toString())
                .antMatchers(Domain.API + Domain.API_DIRECTOR + Domain.ALL).hasAuthority(RoleName.DIRECTOR.toString())
                .antMatchers(Domain.API + Domain.API_MANAGER + Domain.ALL).hasAuthority(RoleName.MANAGER.toString())
                .antMatchers(Domain.API + Domain.API_ACCOUNTANT + Domain.ALL).hasAuthority(RoleName.ACCOUNTANT.toString())
                .antMatchers(Domain.API + Domain.API_INSPECTOR + Domain.ALL).hasAuthority(RoleName.INSPECTOR.toString())
                .antMatchers("/api/mockup/**").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();
//            .anyRequest().permitAll();

        // make server stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // add jwt filter
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // TODO Auto-generated method stub
        return super.authenticationManagerBean();
    }
}
