package com.niit.carManifacture.configuration;

import javax.sql.DataSource;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableAutoConfiguration
@Configuration
@EnableWebSecurity
public class PresistenceConfiguration extends  WebSecurityConfigurerAdapter {

	
	//Create 2 Users for Demo
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user").password("1234").roles("USER")
		.and().withUser("admin").password("1234567").roles("USER","ADMIN");
		
	}
	
	//Secure End points VIA HTTP authentication
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic()
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST,"/car/carMaker/addCarMaker/").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST,"/car/carMaker/findCarMaker/**").hasRole("USER")
			.antMatchers(HttpMethod.DELETE,"/car/carMaker/deleteCarMaker/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT,"/car/carMaker/updateCarManifacturer/").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST,"/car/carMaker/").hasRole("USER")
			.and().csrf().disable().formLogin().disable();
	}
	
	
	@Bean
	public Jdbi jdbi(DataSource dataSource) {
		TransactionAwareDataSourceProxy awareDataSourceProxy = new TransactionAwareDataSourceProxy(dataSource);
		Jdbi jdbi = Jdbi.create(awareDataSourceProxy);
		jdbi.installPlugin(new SqlObjectPlugin());
		
		return jdbi;
	}

}
