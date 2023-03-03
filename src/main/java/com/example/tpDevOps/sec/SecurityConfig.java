package com.example.tpDevOps.sec;

import com.example.tpDevOps.sec.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    //@Override
/*

    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/user/**").hasAuthority("USER");
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");

    }
*/


    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/index.html")
            .hasAnyRole("USER","ADMIN")
            .antMatchers("/admin/**")
            .hasRole("ADMIN")
            .antMatchers("/user/**")
            .hasAuthority("USER")
            .antMatchers("/webjars/**")
            .permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .and()
            .logout();
             http.exceptionHandling()
            .accessDeniedPage("/403");

    }

   // @Override
    protected void configures(AuthenticationManagerBuilder auth) throws Exception {
      /*  String encodedPWD = passwordEncoder.encode("1234");
        System.out.println(encodedPWD);
        auth.inMemoryAuthentication().withUser("abi").password(encodedPWD).roles("USER");
        auth.inMemoryAuthentication().withUser("adi").password(passwordEncoder.encode("12345")).roles("USER");
        auth.inMemoryAuthentication().withUser("kasse").password(passwordEncoder.encode("11111")).roles("USER", "ADMIN");
*/


       auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal, password as credentials, active from appUser where username=?")
                .authoritiesByUsernameQuery("select username as principal,role as role from appUser_role where username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder);
        auth.userDetailsService(userDetailsService);
    }


}
