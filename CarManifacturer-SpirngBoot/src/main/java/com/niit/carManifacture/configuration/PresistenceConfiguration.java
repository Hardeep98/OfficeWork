package com.niit.carManifacture.configuration;

import javax.sql.DataSource;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

@EnableAutoConfiguration
@Configuration
public class PresistenceConfiguration {

	@Bean
	public Jdbi jdbi(DataSource dataSource) {
		TransactionAwareDataSourceProxy awareDataSourceProxy = new TransactionAwareDataSourceProxy(dataSource);
		Jdbi jdbi = Jdbi.create(awareDataSourceProxy);
		jdbi.installPlugin(new SqlObjectPlugin());
		
		return jdbi;
	}

}
