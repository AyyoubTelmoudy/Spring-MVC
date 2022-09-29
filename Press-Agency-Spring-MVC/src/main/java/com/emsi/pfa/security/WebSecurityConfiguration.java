package com.emsi.pfa.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/journalistes/**",
                                   "/journalistes"
                ).hasAnyRole("JOURNALISTE")

                .antMatchers(
                        "/chefs/**",
                                  "/chefs"
                ).hasAnyRole("CHEF")

                .antMatchers(
                        "/lecteurs/**",
                                   "/lecteurs"
                ).hasAnyRole("LECTEUR")

                .antMatchers(
                        "/admins/**",
                                   "/admins"
                ).hasAnyRole("ADMINISTRATEUR")
                .antMatchers("/","/sign-up","/article-details/**","/static/**","/upload/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").usernameParameter("email").passwordParameter("password")
                .permitAll().successHandler(this.loginSuccessHandler)
                .and()
                .logout().logoutSuccessHandler(this.logoutSuccessHandler).permitAll();
    }

}
