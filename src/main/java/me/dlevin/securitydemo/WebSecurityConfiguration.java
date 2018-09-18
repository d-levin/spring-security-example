package me.dlevin.securitydemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This class is only needed to allow access to h2-console
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers("/h2-console/**").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .csrf().disable()
            .headers().frameOptions().disable();
  }

}
