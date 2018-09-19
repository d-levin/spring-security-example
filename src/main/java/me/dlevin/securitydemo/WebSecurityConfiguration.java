package me.dlevin.securitydemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Configuration
  @Order(1)
  public static class H2SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
      http
              .httpBasic()
              .and()
              .authorizeRequests()
              .antMatchers("/h2-console/**").hasRole("ADMIN")
              .and()
              .csrf().disable()
              .headers().frameOptions().sameOrigin();
    }

  }

  @Configuration
  @Order(2)
  public static class DefaultSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
      http
              .httpBasic()
              .and()
              .authorizeRequests()
              .anyRequest().authenticated();
    }

  }

}
