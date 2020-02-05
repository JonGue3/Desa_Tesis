package com.tesis.v1.config;
import com.tesis.v1.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



//Indica que esta clase es de configuracion y necesita ser cargada durante el inicio del server
@Configuration

//Indica que esta clase sobreescribira la implmentacion de seguridad web
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**","/Parsley.js-2.8.0/**","/images/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(resources).permitAll()
                .antMatchers("/","/index").permitAll()
                .antMatchers("/forgotPassword").permitAll()
                .antMatchers("/dashboard_2").permitAll()
                .antMatchers("/registerUser").permitAll()
                .antMatchers("/saveUser").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .permitAll()
                .defaultSuccessUrl("/getMenu")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .csrf().disable()
                .logout()
                .logoutSuccessUrl("/login?logout").
                and()
                .sessionManagement().
                maximumSessions(1).
                expiredUrl("/login");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/font-awesome/**", "/static/**", "/error");
    }

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //Especificar el encargado del login y encriptacion del password
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}